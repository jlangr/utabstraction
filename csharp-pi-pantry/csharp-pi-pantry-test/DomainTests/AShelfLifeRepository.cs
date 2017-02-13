using NUnit.Framework;
using Pipantry.Domain;

namespace Test.Pipantry.Domain
{
    [TestFixture]
    public class AShelfLifeRepository
    {
        ShelfLifeRepository repo = new ShelfLifeRepository();

        [Test]
        public void containsExistingValuesForKnownCategories()
        {
            Assert.That(repo.ContainsKey("water"), Is.True);
        }

        [Test]
        public void allowsStoringShelfLifeInfoForNewCategories()
        {
            repo.add("goop", new ShelfLife { Refrigerated = 42 });

            Assert.That(repo.Get("goop").Refrigerated, Is.EqualTo(42));
        }
    }
}
