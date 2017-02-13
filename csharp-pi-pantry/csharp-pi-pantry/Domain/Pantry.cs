using Pipantry.Util;
using System;
using System.Collections.Generic;
using System.Linq;

namespace Pipantry.Domain {
    public class Pantry {
        private IDictionary<string, IList<Item>> items = new Dictionary<string, IList<Item>>();
        private const int CAPACITY = 512;

        public Pantry()
        {
            DateTimeProvider = new DateTimeWrapper();
        }

        public IDateTime DateTimeProvider { get; set; }

        public void Purchase(Item item) {
            if (item == null) throw new ArgumentException("item");
            if (IsAtCapacity()) throw new Exception("too many items");

            item.PurchaseDate = DateTimeProvider.Now;

            Add(item);
        }

        private void Add(Item item) {
            var existingItems = ItemsNamed(item.Name).ToList();
            if (!existingItems.Any())
                items[item.Name] = existingItems;
            existingItems.Add(item);
        }

        private bool IsAtCapacity() {
            return Count() == CAPACITY;
        }

        public bool Contains(string name) {
            return items.ContainsKey(name);
        }

        public IEnumerable<Item> ItemsNamed(string name) {
            if (!Contains(name))
                return new List<Item>();
            return items[name];
        }

        public int Count(string name) {
            return ItemsNamed(name).Count();
        }

        public int Count() {
            return items.Values.SelectMany(i => i).Count();
        }

        public Item ItemNamed(string name) {
            if (!Contains(name)) return null;
            return ItemsNamed(name).FirstOrDefault(); // TODO Item?
        }

        public IEnumerable<Item> getItemsExpiringToday() {
            return items.Values.SelectMany(i => i).Where(item => item.ExpirationDate == DateTimeProvider.Now);
        }

        public IEnumerable<Item> getAllExpiredItems() {
            return items.Values.SelectMany(i => i).Where(item => DateTimeProvider.Now >= item.ExpirationDate);
        }
    }
}
