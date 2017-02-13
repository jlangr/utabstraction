using Pipantry.Domain;
using Pipantry.Util;
using NUnit.Framework;

namespace Test.Pipantry.Domain
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
            string responseText = JsonUtil.ToJson(new ItemBuilder("Wheaties").create());
            // TODO
            //when(httpClient.RetrieveText(matches("http://.*/json/.*/" + wheatiesUpc)))
            //        .thenReturn(responseText);

            var item = itemRetriever.retrieve(wheatiesUpc);

            Assert.That(item.Name, Is.EqualTo(("Wheaties")));
        }
    }
}
