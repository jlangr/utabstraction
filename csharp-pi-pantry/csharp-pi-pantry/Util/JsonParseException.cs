using System;

namespace Pipantry.Domain {
    public class JsonParseException : Exception {
        public JsonParseException(Exception e)
            : base("", e)
        {
        }
    }
}
