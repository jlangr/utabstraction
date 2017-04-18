using System;
using System.Collections.Generic;
using NUnit.Framework;
using Pipantry.Domain;
using System.Linq;
using Moq;
using Pipantry.Util;
using csharp_pi_pantry_test.DomainTests;

namespace Test.Pipantry.Domain
{
    [TestFixture]
    /**
     * // Tests for Pantry class
     *
     * author: I'll never tell
     */
    public class PantryTest
    {
        private Pantry pantry;
        private readonly DateTime Now = DateTime.Now;

        [SetUp]
        public void Create()
        {
            pantry = new Pantry();
        }

        [Test]
        public void Contains1()
        {
            try
            {
                var pantry = new Pantry();
                var i1 = new Item("cheerios");
                pantry.Purchase(i1);
                Assert.That(pantry.Contains("cheerios"), Is.EqualTo(true));
            }
            catch (ArgumentException e)
            {
                Assert.Fail("purchase Failed:" + e.Message);
            }
        }

        [Test]
        public void Contains2()
        {
            try
            {
                var pantry = new Pantry();
                var cheerios = new Item("cheerios");
                cheerios.Description = "cereal";
                pantry.Purchase(cheerios);
                Assert.That(pantry.Contains("sugar"), Is.EqualTo(false));
            }
            catch (ArgumentException e)
            {
                Assert.Fail("purchase Failed:" + e.Message);
            }
        }

        /**
         * count2
         *
         * count of items by name
         */
        [Test]
        public void Count()
        {
            try
            {
                var pantry = new Pantry();
                //Assert.That(p.Count("sugar"), Is.Zero);

                pantry.Purchase(new Item("sugar"));

                pantry.Purchase(new Item("cheerios"));

                pantry.Purchase(new Item("cheerios"));
                Assert.That(pantry.Count("cheerios"), Is.EqualTo(2), "pantry count not correct for cheerios");

            }
            catch (ArgumentException e)
            {
                Assert.Fail("purchase Failed:" + e.Message);
            }
        }

        /**
         * count
         *
         * count of all items
         */
        [Test]
        public void Count2()
        {
            var pantry = new Pantry();
            pantry.Purchase(new Item("sugar"));
            pantry.Purchase(new Item("sugar"));
            pantry.Purchase(new Item("cheerios"));
            pantry.Purchase(new Item("cheerios"));
            Assert.That(pantry.Count(), Is.EqualTo(4), "pantry count not 4");
        }

        /**
         * dt
         *
         * ensure purchase date set to today
         */
        [Test]
        public void Get0()
        {
            var pantry = new Pantry();
            var sugar = new Item("sugar");
            sugar.Description = "refined sweetener";
            pantry.Purchase(sugar);
            var retrieved = pantry.ItemNamed("sugar");
            Assert.That(retrieved, Is.Not.Null, "item should not be null"); // null?
            Assert.That(retrieved.Description, Is.EqualTo("refined sweetener"));
        }

        /**
         * dt
         *
         * ensure purchase date set to today
         * uses the the clock class as a basis for the fake
         */
        [Test]
        public void Dt()
        {
            DateTime now = DateTime.Now;
            DateTime todayAtNoon = new DateTime(now.Year, now.Month, now.Day, now.Hour, now.Minute, now.Second);
            var pantry = new Pantry();
            var mock = new Mock<IDateTime>();
            mock.Setup(p => p.Now).Returns(todayAtNoon);

            // inject fake clock
            pantry.DateTimeProvider = mock.Object;

            var eggs = new Item("eggs");
            eggs.Description = "oval ovum";
            pantry.Purchase(eggs);
            var retrieved = pantry.ItemNamed("eggs");
            Assert.That(retrieved, Is.Not.Null);
            Assert.That(retrieved.PurchaseDate, Is.EqualTo(todayAtNoon));
        }

        [Test]
        public void Get3()
        {
            var pantry = new Pantry();
            Assert.That(pantry.ItemNamed("did not purchase"), Is.Null);
        }

        [Test]
        public void PurchaseNull()
        {
            try
            {
                var pantry = new Pantry();
                pantry.Purchase(null);
                Assert.Fail("should have thrown ArgumentException");
            }
            catch (ArgumentException e)
            {
                // ok
            }
        }

        [Test]
        public void Get2()
        {
            var pantry = new Pantry();
            var smallCoffee = new Item("coffee");
            smallCoffee.Description = "small";
            var largeCoffee = new Item("coffee");
            largeCoffee.Description = "large";
            pantry.Purchase(smallCoffee);
            pantry.Purchase(largeCoffee);
            var coffees = pantry.ItemsNamed("coffee");
            Assert.That(coffees.Select(item => item.Description),
                    Is.EquivalentTo(new string[] { "small", "large" }));
            var items = pantry.ItemsNamed("pizza");
            Assert.That(items, Is.Empty);
        }

        [Test]
        public void ExpieringToday()
        {
            var pantry = new Pantry();
            var now = DateTime.Now;

            var mock = new Mock<IDateTime>();
            mock.Setup(p => p.Now).Returns(now);

            // inject fake clock
            pantry.DateTimeProvider = mock.Object;

            var item1 = new Item("milk");
            item1.Category = "milk";
            item1.ExpirationDate = now;
            pantry.Purchase(item1);
            // add another item
            var item2 = new Item("blood orange juice 24oz");
            item2.ExpirationDate = now.AddDays(1);
            item2.Category = "orange juice";
            pantry.Purchase(item2);
            // retrieve
            var items = pantry.ItemsExpiringToday();
            var actualItemNames = new List<string>();
            foreach (var i in items)
            {
                actualItemNames.Add(i.Name);
            }
            Console.WriteLine("actual item names:" + actualItemNames);
            var expectedItemNames = new List<string>();
            expectedItemNames.Add("milk");
            Assert.That(actualItemNames,
                    Is.EqualTo(expectedItemNames));
        }

        [Test]
        public void AllExpiredItemsIncludesItemsExpiringTodayOrEarlier()
        {
            SetPantryClockTo(Now);
            pantry.Purchase(new ItemBuilder("milk").WithExpirationDate(Now).Create());
            pantry.Purchase(new ItemBuilder("juice").WithExpirationDate(Now.AddDays(1)).Create());
            pantry.Purchase(new ItemBuilder("curdled milk").WithExpirationDate(Now.AddDays(-1)).Create());

            var items = pantry.AllExpiredItems();

            Assert.That(items.Select(item => item.Name),
                Is.EquivalentTo(new string[] { "milk", "curdled milk" }));
        }

        private void SetPantryClockTo(DateTime dateTime)
        {
            var mock = new Mock<IDateTime>();
            mock.Setup(p => p.Now).Returns(dateTime);
            pantry.DateTimeProvider = mock.Object;
        }

        [Test]
        public void TooMany()
        {
            var pantry = new Pantry();
            for (var i = 0; i < 512; i++)
            {
                var item = new Item("");
                pantry.Purchase(item);
            }
            try
            {
                var item = new Item("");
                pantry.Purchase(item);
                Assert.Fail("expected exception");
            }
            catch (Exception e)
            {
                // ok
            }
        }
    }
}
