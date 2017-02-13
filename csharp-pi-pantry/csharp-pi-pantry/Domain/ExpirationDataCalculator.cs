using System;

namespace Pipantry.Domain {
    public class ExpirationDataCalculator {
        private ShelfLifeRepository shelfLifeData;

        public ExpirationDataCalculator(ShelfLifeRepository shelfLifeData) {
            this.shelfLifeData = shelfLifeData;
        }

        public DateTime getExpirationDate(Item item) {
            if (item.ExpirationDate != null)
                return item.ExpirationDate;
            if (item.Category == null)
                return DateTime.MaxValue;
            return getExpirationDate(shelfLifeData.get(item.Category), item.SellByDate);
        }

        private DateTime getExpirationDate(ShelfLife shelfLife, DateTime sellByDate) {
            return sellByDate.AddDays(shelfLife.Refrigerated);
        }
    }
}
