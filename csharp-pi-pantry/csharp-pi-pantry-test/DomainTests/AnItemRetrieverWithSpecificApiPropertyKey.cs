using NUnit.Framework;
using Pipantry.Domain;

namespace Test.Pipantry.Domain
{
    [TestFixture]
    public class AnItemRetrieverWithSpecificApiPropertyKey {
        string currentApiKey;

        [SetUp]
        public void SaveCurrentApiKey() {
            //currentApiKey = System.getProperty(ItemRetriever.UPC_API_KEY_PROPERTY_NAME);
        }

        [TearDown]
        public void ResetCurrentApiKeyToSaved() {
            if (currentApiKey != null)
                //System.setProperty(ItemRetriever.UPC_API_KEY_PROPERTY_NAME, currentApiKey)
                ;
        }

        [Test]
        public void ConstructsUrlUsingSystemPropertyForApiKey() {
            //System.setProperty(ItemRetriever.UPC_API_KEY_PROPERTY_NAME, "SOME_API_KEY");
            var retriever = new ItemRetriever(null);

            string url = retriever.Url("123");

            Assert.That(url, Does.EndWith("SOME_API_KEY/123"));
        }
    }
}
