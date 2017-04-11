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
        public void Create()
        {
            calculator = new ExpirationDataCalculator(data);
        }

        [Test]
        public void ReturnsItemExpirationDateWhenSet()
        {
            var item = new Item("") { ExpirationDate = Today };

            var expiration = calculator.ExpirationDate(item);

            Assert.That(expiration, Is.EqualTo(Today));
        }

        [Test]
        public void ReturnsIndefiniteWhenNeitherExpirationNorCategorySet()
        {
            Assert.That(calculator.ExpirationDate(new Item()), Is.EqualTo(DateTime.MaxValue));
        }

        [Test]
        public void CalculatesUsingShelfLifeAndSellByDate()
        {
            data.add("smelt", new ShelfLife { Refrigerated = 10 });
            var item = new Item("") { Category = "smelt", SellByDate = Today };

            var expiration = calculator.ExpirationDate(item);

            Assert.That(expiration, Is.EqualTo(Today.AddDays(10)));
        }
    }
}
