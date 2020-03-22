package Lib;
import java.util.concurrent.atomic.AtomicInteger;

public class UniqueId {
    private static AtomicInteger atomicInteger = new AtomicInteger();
    public static String getUniqueID() {
        return Integer.toString(atomicInteger.incrementAndGet());
    }
}
