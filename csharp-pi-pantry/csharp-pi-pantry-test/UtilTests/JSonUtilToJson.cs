using NUnit.Framework;
using Pipantry.Domain;
using Pipantry.Util;
using System;

namespace Test.Pipantry.Util
{
    public class JSonUtilToJson
    {
        class SomeClass
        {
            public SomeClass() { X = 42; }
            public int X { get; }
        }

        [Test]
        public void ConvertsObjectToString()
        {
            Assert.That(JsonUtil.ToJson(new SomeClass()),
                Is.EqualTo("{\"X\":42}"));
        }

        class CannotSerialize
        {
            public int X { get { throw new ApplicationException(); } }
        }

        [Test]
        public void ThrowsRuntimeExceptionOnJsonProcessingException()
        {
            Assert.Throws<JsonParseException>(() => JsonUtil.ToJson(new CannotSerialize()));
        }
    }
}
