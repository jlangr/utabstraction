using NUnit.Framework;
using Pipantry.Util;
using System;

namespace Test.Pipantry.Util
{
    [TestFixture, Category("slow")]
    public class AnHttpClient
    {
        const int TeapotStatus = 418;
        private Http client;

        [SetUp]
        public void Create()
        {
            client = new Http();
        }

        [Test]
        public void CanRetrieveTextResponseFromUrl()
        {
            var text = client.RetrieveText("http://httpbin.org/ip");

            Assert.That(text.ToLower(), Does.Contain("\"origin\""));
        }

        [Test]
        public void ThrowsExceptionOnFailure()
        {
            Assert.Throws<ApplicationException>(() => client.RetrieveText("http://httpbin.org/status/{teapotStatus}"));
        }

        [Test]
        public void AccommodatesCustomErrorHandler()
        {
            var text = client.RetrieveText($"http://httpbin.org/status/{TeapotStatus}", status => $"status: {status}");

            Assert.That(text, Is.EqualTo("status: 418"));
        }
    }
}
