using NUnit.Framework;
using Pipantry.Domain;
using Pipantry.Util;
using System;

namespace Test.Pipantry.Domain
{
    [TestFixture, Category("slow")]
    public class AnItemRetrieverWithLiveApi
    {
        //[Ignore("cannot run with valid API key set")]
        //[Test]
        //public void RetrievesItemInformationViaLiveHttpGet()
        //{
        //    Environment.SetEnvironmentVariable(ItemRetriever.UPC_API_KEY_PROPERTY_NAME, "???");
        //    var retriever = new ItemRetriever(new Http());
        //    var item = retriever.Retrieve("0016000275652");
        //    Assert.That(item.Name, Does.StartWith("Wheaties"));
        //}
    }
}
