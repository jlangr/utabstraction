using NUnit.Framework;
using Pipantry.Domain;

namespace Pipantry.Domain.Tests {
    [TestFixture]
    public class AnItemRetrieverWithLiveApi {
    [Ignore("cannot run without valid API key set in property")]
    //@Category(Slow.class)
    [Test]
        public void retrievesItemInformationViaLiveHttpGet() {
            var retriever = new ItemRetriever(new HttpClient());
            var item = retriever.retrieve("0016000275652");
            Assert.That(item.getName(), StartsWith("Wheaties"));
        }
    }
}
