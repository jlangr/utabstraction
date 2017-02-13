using NUnit.Framework;
using Pipantry.Domain;
using Pipantry.Util;

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
            var retriever = new ItemRetriever(new Http());
            var item = retriever.Retrieve("0016000275652");
            Assert.That(item.Name, Does.StartWith("Wheaties"));
        }
    }
}
