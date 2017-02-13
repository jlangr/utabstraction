using Newtonsoft.Json;
using System;

namespace Pipantry.Domain
{
    public class Item
    {
        public Item(string name) { Name = name; }
        public Item() { }

        // see http://stackoverflow.com/questions/10519265/jackson-overcoming-underscores-in-favor-of-camel-case for alternatives
        //@JsonProperty("valid") private boolean isValid;
        //@JsonProperty("itemname") private String name;
        //private string alias;
        //private string description;
        //@JsonProperty("avg_price") private string averagePrice;
        //@JsonProperty("rate_up") private int rateUp;
        //@JsonProperty("rate_down") private int rateDown;
        //private string number;

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
