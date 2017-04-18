using System;
using NUnit.Framework;
using Pipantry.Domain;
using System.Linq;
using Moq;
using Pipantry.Util;
using csharp_pi_pantry_test.DomainTests;

namespace Test.Pipantry.Domain
{
    [TestFixture]
    public class APantry
    {
        Pantry pantry;
        readonly DateTime Now = DateTime.Now;

        [SetUp]
        public void Create()
        {
            pantry = new Pantry();
        }

        class AtAnyTime : APantry
        {
            [Test]
            public void ContainsPurchasedItem()
            {
                pantry.Purchase(new Item("cheerios"));

                var containsItem = pantry.Contains("cheerios");

                Assert.That(containsItem, Is.True);
            }

            [Test]
            public void DoesNotContainUnpurchasedItem()
            {
                Assert.That(pantry.Contains("sugar"), Is.False);
            }

            [Test]
            public void HasCountZeroForItemsNotPurchased()
            {
                Assert.That(pantry.Count("sugar"), Is.Zero);
            }

            [Test]
            public void AnswersCountOfItemsByName()
            {
                pantry.Purchase(new Item("sugar"));
                pantry.Purchase(new Item("cheerios"));
                pantry.Purchase(new Item("cheerios"));

                var count = pantry.Count("cheerios");

                Assert.That(count, Is.EqualTo(2));
            }

            [Test]
            public void AnswersCountOfAllItems()
            {
                pantry.Purchase(new Item("sugar"));
                pantry.Purchase(new Item("sugar"));
                pantry.Purchase(new Item("cheerios"));
                pantry.Purchase(new Item("cheerios"));

                var count = pantry.Count();

                Assert.That(count, Is.EqualTo(4));
            }

            [Test]
            public void StoresAllInformationForPurchasedItems()
            {
                pantry.Purchase(new ItemBuilder("sugar").WithDescription("refined sweetener").Create());

                var retrieved = pantry.ItemNamed("sugar");

                Assert.That(retrieved.Description, Is.EqualTo("refined sweetener"));
            }

            [Test]
            public void ThrowsWhenPurchasingAboveCapacity()
            {
                for (var i = 0; i < Pantry.CAPACITY; i++)
                    pantry.Purchase(new Item(""));

                Assert.Throws<Exception>(() => pantry.Purchase(new Item("")));
            }

            [Test]
            public void AnswersNullForUnpurchasedItem()
            {
                Assert.That(pantry.ItemNamed("did not purchase"), Is.Null);
            }

            [Test]
            public void ThrowsOnPurchaseWithNullName()
            {
                Assert.Throws<ArgumentException>(() => pantry.Purchase(null));
            }

            [Test]
            public void RetrievesItemsByName()
            {
                pantry.Purchase(new ItemBuilder("coffee").WithDescription("small").Create());
                pantry.Purchase(new ItemBuilder("coffee").WithDescription("large").Create());

                var coffees = pantry.ItemsNamed("coffee");

                Assert.That(coffees.Select(item => item.Description),
                        Is.EquivalentTo(new string[] { "small", "large" }));
            }

            [Test]
            public void ReturnsEmptyListWhenNoItemsPurchasedWithName()
            {
                Assert.That(pantry.ItemsNamed("pizza"), Is.Empty);
            }
        }

        class RightNow: APantry
        {
            [SetUp]
            public void SetPantryClockToNow()
            {
                var mock = new Mock<IDateTime>();
                mock.Setup(dateTime => dateTime.Now).Returns(Now);
                pantry.DateTimeProvider = mock.Object;
            }

            [Test]
            public void StoresCurrentTimestampForPurchasedItems()
            {
                pantry.Purchase(new Item("eggs"));

                var retrieved = pantry.ItemNamed("eggs");

                Assert.That(retrieved.PurchaseDate, Is.EqualTo(Now));
            }

            [Test]
            public void AnswersItemsExpiringToday()
            {
                pantry.Purchase(new ItemBuilder("milk").WithExpirationDate(Now).Create());
                pantry.Purchase(new ItemBuilder("juice").WithExpirationDate(Now.AddDays(1)).Create());

                var items = pantry.ItemsExpiringToday();

                Assert.That(items.Select(item => item.Name),
                        Is.EqualTo(new string[] { "milk" }));
            }

            [Test]
            public void AnswersAllExpiredItems()
            {
                pantry.Purchase(new ItemBuilder("milk").WithExpirationDate(Now).Create());
                pantry.Purchase(new ItemBuilder("juice").WithExpirationDate(Now.AddDays(1)).Create());
                pantry.Purchase(new ItemBuilder("curdled milk").WithExpirationDate(Now.AddDays(-1)).Create());

                var items = pantry.AllExpiredItems();

                Assert.That(items.Select(item => item.Name),
                    Is.EquivalentTo(new string[] { "milk", "curdled milk" }));
            }
        }
    }
}
