using Pipantry.Domain;
using NUnit.Framework;
using System.Collections.Generic;
using System;
using Pipantry.Util;
using Moq;

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
            var serverItemJson = JsonUtil.ToJson(new Item("Cheerios") { SourceName = "xxx" });

            var item = factory.Create(serverItemJson);

            Assert.That(item.SourceName, Is.EqualTo("Cheerios"));
        }

        [Test]
        public void ReplacesNameUsingLookupTable()
        {
            var serverItemJson = JsonUtil.ToJson(new Item("Wheaties 40oz") { Number = "999" });
            factory.setNumberToLocalNameMappings(
                new Dictionary<string, string>() { { "999", "Wheaties" } });

            var item = factory.Create(serverItemJson);

            Assert.That(item.Name, Is.EqualTo("Wheaties"));
            Assert.That(item.SourceName, Is.EqualTo("Wheaties 40oz"));
        }

        [Test]
        public void DoesNotReplaceNameWhenItemNumberIsNull()
        {
            var serverItemJson = JsonUtil.ToJson(new Item("Wheaties") { Number = null });

            var item = factory.Create(serverItemJson);

            Assert.That(item.Name, Is.EqualTo("Wheaties"));
        }

        [Test]
        public void ThrowsARuntimeExceptionOnParseFailure()
        {
            Assert.Throws<JsonParseException>(() => factory.Create("BAD BAD JSON!"));
        }

        [Test]
        public void DefaultsSellByDateToPurchaseDate()
        {
            var now = new DateTime(2018, 3, 17);
            factory.DateTimeProvider = IDateTimeFixedTo(now);

            var parsedItem = factory.Create(JsonUtil.ToJson(new Item()));

            Assert.That(parsedItem.SellByDate, Is.EqualTo(now));
        }

        private static IDateTime IDateTimeFixedTo(DateTime dateTime)
        {
            var mock = new Mock<IDateTime>();
            mock.Setup(p => p.Now).Returns(dateTime);
            return mock.Object;
        }

        [Test]
        public void DefaultsCategoryToNameIfRecognized()
        {
            Assert.That(new ShelfLifeRepository().ContainsKey("milk"), Is.True);
            var itemJson = JsonUtil.ToJson(new Item("milk"));

            var parsedItem = factory.Create(itemJson);

            Assert.That(parsedItem.Category, Is.EqualTo("milk"));
        }

        [Test]
        public void DefaultsCategoryToNullWhenNotRecognized()
        {
            var itemJson = JsonUtil.ToJson(new Item("nonexistent category"));

            var parsedItem = factory.Create(itemJson);

            Assert.That(parsedItem.Category, Is.Null);
        }

        [Test]
        public void DefaultsCategoryToNullWhenNameNull()
        {
            var itemJson = JsonUtil.ToJson(new Item(null));

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
