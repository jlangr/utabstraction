using Newtonsoft.Json;
using Pipantry.Domain;
using System;

namespace Pipantry.Util
{
    public class JsonUtil
    {
        public static string ToJson<T>(T obj)
        {
            try
            {
                return JsonConvert.SerializeObject(obj);
            }
            catch (JsonException e)
            {
                throw new JsonParseException(e);
            }
        }

        public static T Parse<T>(string json, Type type)
        {
            try
            {
                return JsonConvert.DeserializeObject<T>(json);
            }
            catch (JsonException e)
            {
                throw new JsonParseException(e);
            }

        }
    }
}
