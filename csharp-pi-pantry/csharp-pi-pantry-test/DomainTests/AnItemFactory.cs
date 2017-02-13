using Pipantry.Domain;
using NUnit.Framework;
using System.Collections.Generic;
using System;
using Pipantry.Util;

namespace Test.Pipantry.Domain
{
    [TestFixture]
    public class AnItemFactory
    {
        private ItemFactory factory = new ItemFactory();

        [Test]
        public void PopulatesItemFromJson()
        {
            var responseBody = "{\"valid\":\"true\","
                                  + "\"number\":\"0016000275645\","
                                  + "\"itemname\":\"Cheerios\","
                                  + "\"alias\":\"\","
                                  + "\"description\":\"Da Big Box\","
                                  + "\"avg_price\":\"\","
                                  + "\"rate_up\":0,"
                                  + "\"rate_down\":0}";

            var item = factory.Create(responseBody);

            Assert.That(item.Name, Is.EqualTo("Cheerios"));
            Assert.That(item.Description, Is.EqualTo("Da Big Box"));
        }

        [Test]
        public void AssignsNameToSourceName()
        {
            string serverItemJson = JsonUtil.ToJson(new ItemBuilder("Cheerios").withSourceName("xxx").Create());

            var item = factory.Create(serverItemJson);

            Assert.That(item.SourceName, Is.EqualTo("Cheerios"));
        }

        [Test]
        public void ReplacesNameUsingLookupTable()
        {
            string serverItemJson = JsonUtil.ToJson(new ItemBuilder("Wheaties 40oz").withNumber("999").Create());
            var numbersToLocalNames = new Dictionary<string, string>();
            numbersToLocalNames["999"] = "Wheaties";
            factory.setNumberToLocalNameMappings(numbersToLocalNames);

            var item = factory.Create(serverItemJson);

            Assert.That(item.Name, Is.EqualTo("Wheaties"));
            Assert.That(item.SourceName, Is.EqualTo("Wheaties 40oz"));
        }

        [Test]
        public void ThrowsARuntimeExceptionOnParseFailure()
        {
            Assert.Throws<JsonParseException>(() => factory.Create("BAD BAD JSON!"));
        }

        [Test]
        public void DefaultsSellByDateToPurchaseDate()
        {
            //var now = DateTime.now();
            //factory.setClock(TestClock.fixedTo(now));

            //var parsedItem = factory.create(toJson(new Item()));

            //Assert.That(parsedItem.getSellByDate(), equalTo(now));
        }

        [Test]
        public void DefaultsCategoryToNameIfRecognized()
        {
            Assert.That(new ShelfLifeRepository().ContainsKey("milk"), Is.True);
            var itemJson = JsonUtil.ToJson(new ItemBuilder("milk").Create());

            var parsedItem = factory.Create(itemJson);

            Assert.That(parsedItem.Category, Is.EqualTo("milk"));
        }

        [Test]
        public void DefaultsCategoryToNullWhenNotRecognized()
        {
            var itemJson = JsonUtil.ToJson(new ItemBuilder("nonexistent category").Create());

            var parsedItem = factory.Create(itemJson);

            Assert.That(parsedItem.Category, Is.Null);
        }

        [Test]
        public void DefaultsExpirationDateToIndefinite()
        {
            var emptyItemJson = JsonUtil.ToJson(new Item());

            var parsedItem = factory.Create(emptyItemJson);

            Assert.That(parsedItem.ExpirationDate, Is.EqualTo(DateTime.MaxValue));
        }
    }
}
