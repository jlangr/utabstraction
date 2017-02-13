using Pipantry.Domain;
using NUnit.Framework;

namespace Pipantry.Domain.Tests
{
    [TestFixture]
    public class AnItemRetriever
    {
        ItemRetriever itemRetriever;
        HttpClient httpClient;

        [Test]
        public void parsesResponseJsonToItem()
        {
            string wheatiesUpc = "0016000275652";
            string responseText = toJson(new ItemBuilder("Wheaties").create());
            when(httpClient.retrieveText(matches("http://.*/json/.*/" + wheatiesUpc)))
                    .thenReturn(responseText);

            var item = itemRetriever.retrieve(wheatiesUpc);

            Assert.That(item.getName(), Is.EqualTo(("Wheaties")));
        }
    }
}
