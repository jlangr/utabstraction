using System;

namespace Pipantry.Domain {
    public class JsonParseException : ApplicationException {
        public JsonParseException(ApplicationException e)
            : base("", e)
        {
        }
    }
}
