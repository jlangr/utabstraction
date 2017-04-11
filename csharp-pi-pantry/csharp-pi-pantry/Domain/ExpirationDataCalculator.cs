using System;

namespace Pipantry.Domain {
    public class ExpirationDataCalculator {
        private ShelfLifeRepository shelfLifeData;

        public ExpirationDataCalculator(ShelfLifeRepository shelfLifeData) {
            this.shelfLifeData = shelfLifeData;
        }

        public DateTime ExpirationDate(Item item) {
            if (item.ExpirationDate != DateTime.MinValue)
                return item.ExpirationDate;
            if (item.Category == null)
                return DateTime.MaxValue;
            return ExpirationDate(shelfLifeData[item.Category], item.SellByDate);
        }

        private DateTime ExpirationDate(ShelfLife shelfLife, DateTime sellByDate) {
            return sellByDate.AddDays(shelfLife.Refrigerated);
        }
    }
}
