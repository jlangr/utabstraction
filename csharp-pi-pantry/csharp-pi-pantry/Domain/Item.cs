using Newtonsoft.Json;
using System;

namespace Pipantry.Domain
{
    public class Item
    {
        public Item(string name) { Name = name; }
        public Item() { }

        [JsonProperty("number")]
        public string Number { get; set; }
        [JsonProperty("alias")]
        public string Alias { get; set; }
        [JsonProperty("itemname")]
        public string Name { get; set; }
        [JsonProperty("description")]
        public string Description { get; set; }
        [JsonProperty("valid")]
        public bool IsValid { get; set; }
        [JsonProperty("avg_price")]
        public string AveragePrice { get; set; }
        [JsonProperty("rate_up")]
        public int RateUp { get; set; }
        [JsonProperty("rate_down")]
        public int RateDown { get; set; }

        // custom
        public DateTime PurchaseDate { get; set; }
        public string SourceName { get; set; }
        public DateTime ExpirationDate { get; set; }
        public string Category { get; set; }
        public DateTime SellByDate { get; set; }
    }
}
