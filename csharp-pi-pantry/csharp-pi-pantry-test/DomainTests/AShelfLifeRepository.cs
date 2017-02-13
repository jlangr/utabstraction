using NUnit.Framework;
using Pipantry.Domain;

namespace Test.Pipantry.Domain
{
    [TestFixture]
    public class AShelfLifeRepository
    {
        ShelfLifeRepository repo = new ShelfLifeRepository();

        [Test]
        public void ContainsExistingValuesForKnownCategories()
        {
            Assert.That(repo.ContainsKey("water"), Is.True);
        }

        [Test]
        public void AllowsStoringShelfLifeInfoForNewCategories()
        {
            repo.add("goop", new ShelfLife { Refrigerated = 42 });

            Assert.That(repo["goop"].Refrigerated, Is.EqualTo(42));
        }
    }
}
