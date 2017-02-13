using System;

namespace Pipantry.Domain
{
    public class Item
    {
        // see http://stackoverflow.com/questions/10519265/jackson-overcoming-underscores-in-favor-of-camel-case for alternatives
        //@JsonProperty("valid") private boolean isValid;
        //@JsonProperty("itemname") private String name;
        //private string alias;
        //private string description;
        //@JsonProperty("avg_price") private string averagePrice;
        //@JsonProperty("rate_up") private int rateUp;
        //@JsonProperty("rate_down") private int rateDown;
        //private string number;

        public string Number { get; set; }
        public string Alias { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public bool isValid { get; set; }
        public string AveragePrice { get; set; }
        public int RateUp { get; set; }
        public int RateDown { get; set; }

        // custom
        public DateTime PurchaseDate { get; set; }
        public string SourceName { get; set; }
        public DateTime ExpirationDate { get; set; }
        public string Category { get; set; }
        public DateTime SellByDate { get; set; }
    }
}
