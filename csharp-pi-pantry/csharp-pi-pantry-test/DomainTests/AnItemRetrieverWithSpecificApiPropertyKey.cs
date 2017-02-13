using NUnit.Framework;
using Pipantry.Domain;

namespace Pipantry.Domain.Tests {
    [TestFixture]
    public class AnItemRetrieverWithSpecificApiPropertyKey {
        string currentApiKey;

        [SetUp]
        public void saveCurrentApiKey() {
            //currentApiKey = System.getProperty(ItemRetriever.UPC_API_KEY_PROPERTY_NAME);
        }

        [TearDown]
        public void resetCurrentApiKeyToSaved() {
            if (currentApiKey != null)
                //System.setProperty(ItemRetriever.UPC_API_KEY_PROPERTY_NAME, currentApiKey)
                ;
        }

        [Test]
        public void constructsUrlUsingSystemPropertyForApiKey() {
            //System.setProperty(ItemRetriever.UPC_API_KEY_PROPERTY_NAME, "SOME_API_KEY");
            var retriever = new ItemRetriever(null);

            string url = retriever.url("123");

            Assert.That(url, EndsWith("SOME_API_KEY/123"));
        }
    }
}
