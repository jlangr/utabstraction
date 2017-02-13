using Pipantry.Util;

namespace Pipantry.Domain
{
    public class ItemRetriever
    {
        private const string API_SERVER = "http://api.upcdatabase.org";
        public const string UPC_API_KEY_PROPERTY_NAME = "upcdatabase_api_key";
        private string upcApiKey =
            // TODO
            //System.getProperty(UPC_API_KEY_PROPERTY_NAME);
"a6f99f4c683845042ae27bbf3e36aeee";
        private Http httpClient;
        private ItemFactory itemFactory = new ItemFactory();

        public ItemRetriever(Http client)
        {
            httpClient = client;
        }

        public string Url(string upcNumber)
        {
            return $"{API_SERVER}/json/{upcApiKey}/{upcNumber}";
        }

        public Item Retrieve(string upcNumber)
        {
            string json = httpClient.RetrieveText(Url(upcNumber));
            return itemFactory.Create(json);
        }
    }
}
