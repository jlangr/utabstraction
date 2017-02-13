using System;
using NUnit.Framework;
using Pipantry.Domain;

namespace Test.Pipantry.Domain
{
    [TestFixture]
    public class AnExpirationDateCalculator
    {
        readonly DateTime Today = DateTime.Now;
        ExpirationDataCalculator calculator;
        ShelfLifeRepository data = new ShelfLifeRepository();

        [SetUp]
        public void create()
        {
            calculator = new ExpirationDataCalculator(data);
        }

        [Test]
        public void returnsItemExpirationDateWhenSet()
        {
            var item = new ItemBuilder("").withExpirationDate(Today).create();

            var expiration = calculator.ExpirationDate(item);

            Assert.That(expiration, Is.EqualTo(Today));
        }

        [Test]
        public void returnsIndefiniteWhenNeitherExpirationNorCategorySet()
        {
            Assert.That(calculator.ExpirationDate(new Item()), Is.EqualTo(DateTime.MaxValue));
        }

        [Test]
        public void calculatesUsingShelfLifeAndSellByDate()
        {
            data.add("smelt", new ShelfLife { Refrigerated = 10 });
            var item = new ItemBuilder("").withCategory("smelt").withSellByDate(Today).create();

            var expiration = calculator.ExpirationDate(item);

            Assert.That(expiration, Is.EqualTo(Today.AddDays(10)));
        }
    }
}
