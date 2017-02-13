using Pipantry.Domain;
using NUnit.Framework;
using System.Collections.Generic;
using System;

namespace Pipantry.Domain.Tests
{
    [TestFixture]
    public class AnItemFactory
    {
        private ItemFactory factory = new ItemFactory();

        [Test]
        public void populatesItemFromJson()
        {
            var responseBody = "{\"valid\":\"true\","
                                  + "\"number\":\"0016000275645\","
                                  + "\"itemname\":\"Cheerios\","
                                  + "\"alias\":\"\","
                                  + "\"description\":\"Da Big Box\","
                                  + "\"avg_price\":\"\","
                                  + "\"rate_up\":0,"
                                  + "\"rate_down\":0}";

            var item = factory.create(responseBody);

            Assert.That(item.Name, Is.EqualTo("Cheerios"));
            Assert.That(item.Description, Is.EqualTo("Da Big Box"));
        }

        [Test]
        public void assignsNameToSourceName()
        {
            string serverItemJson = toJson(new ItemBuilder("Cheerios").withSourceName("xxx").create());

            var item = factory.create(serverItemJson);

            Assert.That(item.SourceName, Is.EqualTo("Cheerios"));
        }

        [Test]
        public void replacesNameUsingLookupTable()
        {
            string serverItemJson = toJson(new ItemBuilder("Wheaties 40oz").withNumber("999").create());
            var numbersToLocalNames = new Dictionary<string, string>();
            numbersToLocalNames["999"] = "Wheaties";
            factory.setNumberToLocalNameMappings(numbersToLocalNames);

            var item = factory.create(serverItemJson);

            Assert.That(item.Name, Is.EqualTo("Wheaties"));
            Assert.That(item.SourceName, Is.EqualTo("Wheaties 40oz"));
        }

        [Test]
        public void throwsARuntimeExceptionOnParseFailure()
        {
            // (expected = JsonParseException.class)
            factory.create("BAD BAD JSON!");
        }

        [Test]
        public void defaultsSellByDateToPurchaseDate()
        {
            var now = DateTime.now();
            factory.setClock(TestClock.fixedTo(now));

            var parsedItem = factory.create(toJson(new Item()));

            Assert.That(parsedItem.getSellByDate(), equalTo(now));
        }

        [Test]
        public void defaultsCategoryToNameIfRecognized()
        {
            Assert.That(new ShelfLifeRepository().contains("milk"), Is.True);
            var itemJson = toJson(new ItemBuilder("milk").create());

            var parsedItem = factory.create(itemJson);

            Assert.That(parsedItem.getCategory(), Is.EqualTo("milk"));
        }

        [Test]
        public void defaultsCategoryToNullWhenNotRecognized()
        {
            var itemJson = toJson(new ItemBuilder("nonexistent category").create());

            var parsedItem = factory.create(itemJson);

            Assert.That(parsedItem.getCategory(), Is.Null);
        }

        [Test]
        public void defaultsExpirationDateToIndefinite()
        {
            var emptyItemJson = toJson(new Item());

            var parsedItem = factory.create(emptyItemJson);

            Assert.That(parsedItem.getExpirationDate(), Is.EqualTo(DateTime.MaxValue));
        }
    }
}
