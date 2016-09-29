package cz.fit.dpo.mvcshooter.model;

/**
 * Created by Kuba on 20. 12. 2015.
 */
public class TimeKeeper {
    private static long time = 0;

    public static void setTime(long newTime) {
        time = newTime;
    }

    public static long getTime() { return time; }
}
