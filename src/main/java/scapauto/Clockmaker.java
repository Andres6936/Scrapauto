package scapauto;

import java.util.concurrent.TimeUnit;

public final class Clockmaker
{
    /**
     * This method is licensed under The MIT License (MIT) Copyright (c) 2015 Michael Gantman
     * <br><br>
     * <p>
     * This method is a convenience method for sleep that "swallows" {@link InterruptedException}  and
     * has {@link TimeUnit} parameter in addition to time period so it makes it very convenient. So with
     * this method there is no need to convert the time into milliseconds. Just simply write
     * <br><br>
     *
     * <p>{@code waitFor(10, TimeUnit.SECONDS);}</p>
     * <br> No Exception catching needed
     *
     * @param period   time period to sleep
     * @param timeUnit {@link TimeUnit} that specifies in which units the time period is measured
     */
    public static void waitFor(final int period, TimeUnit timeUnit)
    {
        try {
            timeUnit.sleep(period);
        } catch (InterruptedException ignored) {
        }
    }
}
