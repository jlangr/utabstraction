using System;
using System.Collections.Generic;
using NUnit.Framework;
using Pipantry.Domain;
using System.Linq;

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
        public void contains1()
        {
            try
            {
                var p = new Pantry();
                var i1 = new Item("cheerios");
                i1.Description = "cereal";
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
        public void contains2()
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
        public void count()
        {
            try
            {
                var p = new Pantry();
                Assert.That(p.Count("sugar"), Is.Zero);

                p.Purchase(new Item("sugar"));

                p.Purchase(new Item("cheerios"));

                p.Purchase(new Item("cheerios"));
                Assert.That(p.Count("cheerios"), Is.EqualTo(2));

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
        public void count2()
        {
            try
            {
                var pantry = new Pantry();
                pantry.Purchase(new Item("sugar"));
                pantry.Purchase(new Item("sugar"));
                pantry.Purchase(new Item("cheerios"));
                pantry.Purchase(new Item("cheerios"));
                Assert.That(pantry.Count(), Is.EqualTo(4));
            }
            catch (ArgumentException e)
            {
                Assert.Fail("purchase Failed:" + e.Message);
            }
        }

        /**
         * dt
         *
         * ensure purchase date set to today
         */
        [Test]
        public void get0()
        {
            try
            {
                var pantry = new Pantry();
                var sugar = new Item("sugar");
                sugar.Description = "refined sweetener";
                pantry.Purchase(sugar);
                var retrieved = pantry.ItemNamed("sugar");
                Assert.That(retrieved, Is.Not.Null); // null?
                Assert.That(retrieved.Description, Is.EqualTo("refined sweetener"));
            }
            catch (ArgumentException e)
            {
                Assert.Fail("purchase Failed:" + e.Message);
            }
        }

        /**
         * dt
         *
         * ensure purchase date set to today
         * uses the java clock class as a basis for the fake
         */
        [Test]
        public void dt()
        {
            try
            {
                // TODO
                //DateTimeTime todayAtNoon = DateTime.now().atTime(12, 0, 0);
                //Instant todayNoonUTC = todayAtNoon.toInstant(ZoneOffset.UTC);
                //Clock clock = Clock.fixed (todayNoonUTC, ZoneOffset.UTC);
                var pantry = new Pantry();
                // inject fake clock
//pantry.setClock(clock);
                var eggs = new Item("eggs");
                eggs.Description = "oval ovum";
                pantry.Purchase(eggs);
                var retrieved = pantry.ItemNamed("eggs");
                Assert.That(retrieved, Is.Not.Null);
                Assert.That(retrieved.PurchaseDate, Is.EqualTo(DateTime.Now));
            }
            catch (ArgumentException e)
            {
                Assert.Fail("purchase Failed:" + e.Message);
            }
        }

        [Test]
        public void get3()
        {
            var pant = new Pantry();
            Assert.That(pant.ItemNamed("did not purchase"), Is.Null);
        }

        [Test]
        public void purchaseNull()
        {
            try
            {
                var pantry = new Pantry();
                pantry.Purchase(null);
                Assert.Fail("should have thrown ArgumentException");
            }
            catch (ArgumentException e)
            {
                Console.WriteLine(e.Message);
                // ok
            }
        }

        [Test]
        public void get2()
        {
            try
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
            catch (ArgumentException e)
            {
                Assert.Fail("purchase Failed:" + e.Message);
            }
        }

        [Test]
        public void expieringToday()
        {
            try
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
            catch (ArgumentException e)
            {
                Assert.Fail("purchase Failed:" + e.Message);
            }
        }

        [Test]
        public void allExpiredItems()
        {
            try
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
            catch (Exception e)
            {
                Assert.Fail(e.Message);
            }
        }

        [Test]
        public void tooMany()
        {
            var pantry = new Pantry();
            for (int i = 0; i < 512; i++)
            {
                var item = new Item();
                pantry.Purchase(item);
            }
            try
            {
                var item = new Item();
                pantry.Purchase(item);
                Assert.Fail("expected exception");
            }
            catch (Exception e)
            {
                // ok
                Console.WriteLine(e.Message + " exception.");
            }
        }
    }
}
