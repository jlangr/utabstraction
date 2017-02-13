using System;

namespace Pipantry.Util
{
    public class DateTimeWrapper: IDateTime
    {
        public DateTime Now {  get { return DateTime.Now; } }
    }
}
