using NUnit.Framework;
using Pipantry.Domain;

namespace Pipantry.Domain.Tests
{
    [TestFixture]
    public class AShelfLifeRepository
    {
        ShelfLifeRepository repo = new ShelfLifeRepository();

        [Test]
        public void containsExistingValuesForKnownCategories()
        {
            Assert.That(repo.contains("water"), Is.True);
        }

        [Test]
        public void allowsStoringShelfLifeInfoForNewCategories()
        {
            int refrigeratedDaysOfLife = 42;
            repo.add("goop", new ShelfLife(refrigeratedDaysOfLife, 0));

            Assert.That(repo.get("goop").getRefrigerated(), Is.EqualTo(refrigeratedDaysOfLife));
        }
    }
}
