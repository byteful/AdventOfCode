package shared;

public final class Timings {
    private static long start;

    public static void start() {
        start = System.currentTimeMillis();
    }

    public static void checkpoint(String checkpoint) {
        System.out.printf("Checkpoint '" + checkpoint + "' reached after %sms.%n", System.currentTimeMillis() - start);
    }

    public static void stop() {
        System.out.printf("This problem took %sms to complete.%n", System.currentTimeMillis() - start);
    }
}
