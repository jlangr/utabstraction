using NUnit.Framework;
using Pipantry.Domain;
using Pipantry.Util;

namespace Test.Pipantry.Util
{
    public class JSonUtilToJson
    {
        [Test]
        public void convertsObjectToString()
        {
            object obj = null;
            //new object() { public int x = 42; });
            Assert.That(JsonUtil.ToJson(obj),
                Is.EqualTo("{\"x\":42}"));
        }

        [Test]
        public void throwsRuntimeExceptionOnJsonProcessingException()
        {
            Assert.Throws<JsonParseException>(() => JsonUtil.ToJson(new object()));
        }
    }
}
