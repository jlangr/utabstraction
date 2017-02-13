
using NUnit.Framework;
using Pipantry.Domain;
using System;
using System.Collections.Generic;

namespace Pipantry.Domain.Tests
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
                i1.setDescription("cereal");
                p.purchase(i1);
                Assert.That(p.contains("cheerios"), Is.EqualTo(true));
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
                cheerios.setDescription("cereal");
                pantry.purchase(cheerios);
                Assert.That(pantry.contains("sugar"), Is.EqualTo(false));
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
                Assert.That(p.count("sugar"), Is.EqualTo(0)));

                p.purchase(new Item("sugar"));

                p.purchase(new Item("cheerios"));

                p.purchase(new Item("cheerios"));
                Assert.That(p.count("cheerios"), Is.EqualTo(2));

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
                pantry.purchase(new Item("sugar"));
                pantry.purchase(new Item("sugar"));
                pantry.purchase(new Item("cheerios"));
                pantry.purchase(new Item("cheerios"));
                Assert.That(pantry.count(), Is.EqualTo(4)));
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
                sugar.setDescription("refined sweetener");
                pantry.purchase(sugar);
                var retrieved = pantry.getItemNamed("sugar");
                Assert.That(retrieved, Is.Not.Null); // null?
                Assert.That(retrieved.getDescription(), Is.EqualTo("refined sweetener")));
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
                pantry.setClock(clock);
                var eggs = new Item("eggs");
                eggs.setDescription("oval ovum");
                pantry.purchase(eggs);
                var retrieved = pantry.getItemNamed("eggs");
                Assert.That(retrieved, Is.Not.Null);
                Assert.That(retrieved.getPurchaseDate(), Is.EqualTo(DateTime.now())));
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
            Assert.That(pant.getItemNamed("did not purchase"), Is.Null);
        }

        [Test]
        public void purchaseNull()
        {
            try
            {
                var pantry = new Pantry();
                pantry.purchase(null);
                Assert.Fail("should have thrown ArgumentException");
            }
            catch (ArgumentException e)
            {
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
                smallCoffee.setDescription("small");
                var largeCoffee = new Item("coffee");
                largeCoffee.setDescription("large");
                pantry.purchase(smallCoffee);
                pantry.purchase(largeCoffee);
                var coffees = pantry.ItemsNamed("coffee");
                Assert.That(coffees.stream().map(Item::getDescription).collect(toList()),
                        Is.EqualTo(asList("small", "large")));
                var items = pantry.getItemsNamed("pizza");
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
                var d = DateTime.now();
                pantry.setClock(TestClock.fixedTo(d));
                var item1 = new Item("milk");
                item1.setCategory("milk");
                item1.setExpirationDate(d);
                pantry.purchase(item1);
                // add another item
                var item2 = new Item("blood orange juice 24oz");
                item2.setExpirationDate(d.plusDays(1));
                item2.setCategory("orange juice");
                pantry.purchase(item2);
                // retrieve
                var items = pantry.getItemsExpiringToday();
                var actualItemNames = new ArrayList<>();
                for (Item i: items)
                {
                    actualItemNames.add(i.getName());
                }
                Console.WriteLine("actual item names:" + actualItemNames);
                var expectedItemNames = new List<string>();
                expectedItemNames.add("milk");
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
                var d = DateTime.now();
                pantry.setClock(TestClock.fixedTo(d));
                var item1 = new Item("milk");
                item1.setCategory("milk");
                item1.setExpirationDate(d);
                pantry.purchase(item1);
                // add another item
                var item2 = new Item("blood orange juice 24oz");
                item2.setExpirationDate(d.plusDays(1));
                item2.setCategory("orange juice");
                pantry.purchase(item2);
                String name = "curdled milk";
                var item3 = new Item(name);
                item3.setExpirationDate(d.AddDays(-1));
                item1.setCategory("milk");
                pantry.purchase(item3);
                // retrieve
                var items = pantry.getAllExpiredItems();
                var actualItemNames = new ArrayList<>();
                for (var i : items)
                {
                    actualItemNames.add(i.Name);
                }
                var expectedItemNames = new List<string>();
                expectedItemNames.add(name);
                expectedItemNames.add("milk");
                // TODO ? is equivalent
                Assert.That(actualItemNames, ContainsInAnyOrder("milk", "curdled milk"));
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
                pantry.purchase(item);
            }
            try
            {
                var item = new Item();
                pantry.purchase(item);
                Assert.Fail("expected exception");
            }
            catch (Exception e)
            {
                // ok
            }
        }
    }
}
