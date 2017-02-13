using NUnit.Framework;
using Pipantry.Util;

namespace Test.Pipantry.Util
{
    [TestFixture]
    public class AnHttpClient
    {
    //@Category(Slow.class)
        [Test]
        public void canRetrieveTextResponseFromUrl()
        {
            var client = new HttpClient();
            string teapotStatus = "418";
            string text = client.RetrieveText("http://httpbin.org/status/" + teapotStatus);

            Assert.That(text.ToLower(), Does.Contain("teapot"));
        }
    }
}
