using Pipantry.Util;
using System;
using System.Collections.Generic;

namespace Pipantry.Domain
{
    public class ItemFactory
    {
        private IDictionary<string, string> numberToLocalNameMappings = new Dictionary<string, string>();
        private ShelfLifeRepository shelfLifeData = new ShelfLifeRepository();

        public ItemFactory()
        {
            DateTimeProvider = new DateTimeWrapper();
        }

        public IDateTime DateTimeProvider { get; set; }

        public Item Create(string json)
        {
            var item = JsonUtil.Parse<Item>(json);
            item.SourceName = item.Name;
            item.ExpirationDate = DateTime.MaxValue;
            item.SellByDate = DateTimeProvider.Now;
            ChangeNameIfLocalMappingExists(item);
            ChangeCategoryIfRecognized(item);
            return item;
        }

        private void ChangeCategoryIfRecognized(Item item)
        {
            if (item.Name != null && shelfLifeData.ContainsKey(item.Name))
                item.Category = item.Name;
        }

        private void ChangeNameIfLocalMappingExists(Item item)
        {
            if (item.Number != null && numberToLocalNameMappings.ContainsKey(item.Number))
                item.Name = numberToLocalNameMappings[item.Number];
        }

        public void setNumberToLocalNameMappings(IDictionary<string, string> numberToLocalNameMappings)
        {
            this.numberToLocalNameMappings = numberToLocalNameMappings;
        }
    }
}
