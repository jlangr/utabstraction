using Newtonsoft.Json;
using NUnit.Framework;
using Pipantry.Domain;
using Pipantry.Util;

namespace Test.Pipantry.Util
{
    public class JSonUtilParse
    {
        public class X
        {
            public int Y { get; set; }
        }

        [Test]
        public void CreatesInstanceOfClassFromJson()
        {
            X x = JsonUtil.Parse<X>("{\"Y\": 42}");

            Assert.That(x.Y, Is.EqualTo(42));
        }

        [Test]
        public void ThrowsRuntimeExceptionOnJsonParseException()
        {
            Assert.Throws<JsonParseException>(() => JsonUtil.Parse<X>("{\"Y\": \"not an int\"}"));
        }
    }
}
