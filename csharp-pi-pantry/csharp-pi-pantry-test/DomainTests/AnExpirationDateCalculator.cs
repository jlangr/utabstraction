using System;
using NUnit.Framework;
using Pipantry.Domain;

namespace Pipantry.Domain.Tests
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

            DateTime expiration = calculator.getExpirationDate(item);

            Assert.That(expiration, Is.EqualTo(Today));
        }

        [Test]
        public void returnsIndefiniteWhenNeitherExpirationNorCategorySet()
        {
            Assert.That(calculator.getExpirationDate(new Item()), Is.EqualTo(DateTime.MaxValue));
        }

        [Test]
        public void calculatesUsingShelfLifeAndSellByDate()
        {
            int refrigerationLife = 10;
            data.add("smelt", new ShelfLife(refrigerationLife, 0));
            var item = new ItemBuilder("").withCategory("smelt").withSellByDate(Today).create();

            var expiration = calculator.getExpirationDate(item);

            Assert.That(expiration, Is.EqualTo(Today.AddDays(refrigerationLife)));
        }
    }
}
}
