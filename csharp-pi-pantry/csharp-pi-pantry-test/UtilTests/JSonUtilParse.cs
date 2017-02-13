using NUnit.Framework;
using Pipantry.Domain;
using Pipantry.Util;

namespace Test.Pipantry.Util
{
    public class JSonUtilParse
    {
        class X
        {
            public int y;
        }

        [Test]
        public void createsInstanceOfClassFromJson()
        {
            X x = JsonUtil.Parse<X>("{\"y\": 42}", typeof(X));

            Assert.That(x.y, Is.EqualTo(42));
        }

        [Test]
        public void throwsRuntimeExceptionOnJsonParseException()
        {
            Assert.Throws<JsonParseException>(() => JsonUtil.Parse<X>("?", typeof(X)));
        }
    }
}
