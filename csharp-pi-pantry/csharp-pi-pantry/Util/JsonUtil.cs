using System;

namespace Pipantry.Util
{
    public class JsonUtil
    {
        public static string ToJson<T>(T obj)
        {
            //try
            //{
            //    return new ObjectMapper().writeValueAsString(obj);
            //}
            //catch (JsonProcessingException e)
            //{
            //    throw new JsonParseException(e);
            //}
            return null;
        }

        public static T Parse<T>(string json, Type type)
        {
            //try
            //{
            //    return new ObjectMapper().readValue(json, type);
            //}
            //catch (IOException e)
            //{
            //    throw new JsonParseException(e);
            //}
            return (T)new object();
        }
    }
}
