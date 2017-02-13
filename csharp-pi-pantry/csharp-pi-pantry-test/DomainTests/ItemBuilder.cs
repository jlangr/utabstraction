using NUnit.Framework;
using Pipantry.Domain;
using System;

// TODO is a builder at all useful w/ C#?
namespace Pipantry.Domain.Tests
{

    public class ItemBuilder
    {
        private string name;
        private string description;
        private string sourceName;
        private string number;
        private string category;
        private DateTime expirationDate;
        private DateTime sellByDate;

        public ItemBuilder(string name)
        {
            this.name = name;
        }

        public Item create()
        {
            var item = new Item(name);
            item.setDescription(description);
            item.setSourceName(sourceName);
            item.setNumber(number);
            item.setExpirationDate(expirationDate);
            item.setSellByDate(sellByDate);
            item.setCategory(category);
            return item;
        }

        public ItemBuilder withDescription(string description)
        {
            this.description = description;
            return this;
        }

        public ItemBuilder withSourceName(string sourceName)
        {
            this.sourceName = sourceName;
            return this;
        }

        public ItemBuilder withNumber(string number)
        {
            this.number = number;
            return this;
        }

        public ItemBuilder withExpirationDate(DateTime date)
        {
            expirationDate = date;
            return this;
        }

        public ItemBuilder withCategory(string category)
        {
            this.category = category;
            return this;
        }

        public ItemBuilder withSellByDate(DateTime date)
        {
            sellByDate = date;
            return this;
        }
    }
}
