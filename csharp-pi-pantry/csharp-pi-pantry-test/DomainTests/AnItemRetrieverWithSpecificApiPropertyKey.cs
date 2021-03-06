using NUnit.Framework;
using Pipantry.Domain;
using System;

namespace Test.Pipantry.Domain
{
    [TestFixture]
    public class AnItemRetrieverWithSpecificApiPropertyKey
    {
        string currentApiKey;

        [SetUp]
        public void SaveCurrentApiKey()
        {
            currentApiKey = Environment.GetEnvironmentVariable(ItemRetriever.UPC_API_KEY_PROPERTY_NAME);
        }

        [TearDown]
        public void ResetCurrentApiKeyToSaved()
        {
            if (currentApiKey != null)
                Environment.SetEnvironmentVariable(ItemRetriever.UPC_API_KEY_PROPERTY_NAME, currentApiKey);
        }

        [Test]
        public void ConstructsUrlUsingSystemPropertyForApiKey()
        {
            Environment.SetEnvironmentVariable(ItemRetriever.UPC_API_KEY_PROPERTY_NAME, "SOME_API_KEY");
            var retriever = new ItemRetriever(null);

            var url = retriever.Url("123");

            Assert.That(url, Does.EndWith("SOME_API_KEY/123"));
        }
    }
}
