using Newtonsoft.Json;
using Pipantry.Domain;

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

        public static T Parse<T>(string json)
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
