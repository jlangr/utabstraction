using Pipantry.Util;
using System;

namespace Pipantry.Domain
{
    public class ItemRetriever
    {
        private const string API_SERVER = "http://api.upcdatabase.org";
        public const string UPC_API_KEY_PROPERTY_NAME = "upcdatabase_api_key";
        private Http httpClient;
        private ItemFactory itemFactory = new ItemFactory();

        public ItemRetriever(Http client)
        {
            httpClient = client;
            UpcApiKey = Environment.GetEnvironmentVariable(UPC_API_KEY_PROPERTY_NAME);
        }

        public string Url(string upcNumber)
        {
            return $"{API_SERVER}/json/{UpcApiKey}/{upcNumber}";
        }

        public string UpcApiKey { get; set; }

        public Item Retrieve(string upcNumber)
        {
            string json = httpClient.RetrieveText(Url(upcNumber));
            return itemFactory.Create(json);
        }
    }
}
