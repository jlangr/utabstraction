using System;
using System.Collections.Generic;
using NUnit.Framework;
using Pipantry.Domain;
using System.Linq;
using Moq;
using Pipantry.Util;

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
        [Test]
        public void Contains1()
        {
            try
            {
                var p = new Pantry();
                var i1 = new Item("cheerios");
                p.Purchase(i1);
                Assert.That(p.Contains("cheerios"), Is.EqualTo(true));
            }
            catch (ArgumentException e)
            {
                Assert.Fail("purchase Failed:" + e.Message);
            }
        }

        /**
         * contains2
         */
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
                var p = new Pantry();
                //Assert.That(p.Count("sugar"), Is.Zero);

                p.Purchase(new Item("sugar"));

                p.Purchase(new Item("cheerios"));

                p.Purchase(new Item("cheerios"));
                Assert.That(p.Count("cheerios"), Is.EqualTo(2), "pantry count not correct for cheerios");

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
            var pant = new Pantry();
            Assert.That(pant.ItemNamed("did not purchase"), Is.Null);
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
        public void AllExpiredItems()
        {
            var pantry = new Pantry();
            var d = DateTime.Now;
            //pantry.setClock(TestClock.fixedTo(d));
            var item1 = new Item("milk");
            item1.Category = "milk";
            item1.ExpirationDate = d;
            pantry.Purchase(item1);
            // add another item
            var item2 = new Item("blood orange juice 24oz");
            item2.ExpirationDate = d.AddDays(1);
            item2.Category = "orange juice";
            pantry.Purchase(item2);
            string name = "curdled milk";
            var item3 = new Item(name);
            item3.ExpirationDate = d.AddDays(-1);
            item1.Category = "milk";
            pantry.Purchase(item3);
            // retrieve
            var items = pantry.getAllExpiredItems();
            var actualItemNames = new List<string>();
            foreach (var i in items)
            {
                actualItemNames.Add(i.Name);
            }
            var expectedItemNames = new List<string>();
            expectedItemNames.Add(name);
            expectedItemNames.Add("milk");
            Assert.That(actualItemNames, Is.EquivalentTo(new string[] { "milk", "curdled milk" }));
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
