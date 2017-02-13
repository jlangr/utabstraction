using Pipantry.Util;

namespace Pipantry.Domain
{
    public class ItemRetriever
    {
        private const string API_SERVER = "http://api.upcdatabase.org";
        public const string UPC_API_KEY_PROPERTY_NAME = "upcdatabase_api_key";
        private string upcApiKey = null;
            //System.getProperty(UPC_API_KEY_PROPERTY_NAME);
        private HttpClient httpClient;
        private ItemFactory itemFactory = new ItemFactory();

        public ItemRetriever(HttpClient client)
        {
            httpClient = client;
        }

        public string Url(string upcNumber)
        {
            return $"{API_SERVER}/json/{upcApiKey}/{upcNumber}";
        }

        public Item retrieve(string upcNumber)
        {
            string json = httpClient.RetrieveText(Url(upcNumber));
            return itemFactory.Create(json);
        }
    }
}
