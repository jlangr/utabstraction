package com.langrsoft.pi.pantry;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class TestClock {
    public static Clock fixedTo(LocalDate localDate) {
        return Clock.fixed(localDate.atStartOfDay().toInstant(ZoneOffset.UTC), ZoneOffset.UTC);
    }
}
