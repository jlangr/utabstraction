using NUnit.Framework;
using Pipantry.Domain;
using Pipantry.Util;
using System;

namespace Test.Pipantry.Domain
{
    [TestFixture]
    public class AnItemRetrieverWithLiveApi
    {
        //[Ignore("cannot run without valid API key set in property")]
        //@Category(Slow.class)
        [Test]
        public void RetrievesItemInformationViaLiveHttpGet()
        {
            Environment.SetEnvironmentVariable(
                ItemRetriever.UPC_API_KEY_PROPERTY_NAME, "a6f99f4c683845042ae27bbf3e36aeee");
            var retriever = new ItemRetriever(new Http());
            var item = retriever.Retrieve("0016000275652");
            Assert.That(item.Name, Does.StartWith("Wheaties"));
        }
    }
}
