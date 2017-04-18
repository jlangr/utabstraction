using Pipantry.Domain;
using System;

namespace csharp_pi_pantry_test.DomainTests
{
    public class ItemBuilder
    {
        private string name = "";
        private string description = "";
        private string sourceName = "";
        private string number = "";
        private string category = "";
        private DateTime expirationDate = DateTime.Now.AddDays(1);
        private DateTime sellByDate = DateTime.Now;

        public ItemBuilder(string name)
        {
            this.name = name;
        }

        public Item Create()
        {
            return new Item(name)
            {
                Description = description,
                SourceName = sourceName,
                Number = number,
                ExpirationDate = expirationDate,
                SellByDate = sellByDate,
                Category = category
            };
        }

        public ItemBuilder WithDescription(string description)
        {
            this.description = description;
            return this;
        }

        public ItemBuilder WithSourceName(string sourceName)
        {
            this.sourceName = sourceName;
            return this;
        }

        public ItemBuilder WithNumber(string number)
        {
            this.number = number;
            return this;
        }

        public ItemBuilder WithExpirationDate(DateTime date)
        {
            expirationDate = date;
            return this;
        }

        public ItemBuilder WithCategory(string category)
        {
            this.category = category;
            return this;
        }

        public ItemBuilder WithSellByDate(DateTime date)
        {
            sellByDate = date;
            return this;
        }
    }
}
