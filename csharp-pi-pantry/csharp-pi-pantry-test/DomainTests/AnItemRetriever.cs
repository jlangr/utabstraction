using Pipantry.Domain;
using Pipantry.Util;
using NUnit.Framework;
using Moq;

namespace Test.Pipantry.Domain
{
    [TestFixture]
    public class AnItemRetriever
    {
        ItemRetriever itemRetriever;
        Mock<Http> httpClient;

        [SetUp]
        public void create()
        {
            httpClient = new Mock<Http>();
            itemRetriever = new ItemRetriever(httpClient.Object);
        }

        [Test]
        public void ParsesResponseJsonToItem()
        {
            string wheatiesUpc = "0016000275652";
            string responseText = JsonUtil.ToJson(new ItemBuilder("Wheaties").Create());

            httpClient.Setup(client => client.RetrieveText(It.IsRegex("http://.*/json/.*/" + wheatiesUpc)))
                .Returns(responseText);

            var item = itemRetriever.Retrieve(wheatiesUpc);

            Assert.That(item.Name, Is.EqualTo(("Wheaties")));
        }
    }
}
