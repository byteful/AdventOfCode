package aoc2023.day6;

import shared.Timings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class Day6 {
    public static void main(String[] args) throws IOException {
        Timings.start();
        final File file = new File("src/aoc2023/day6/input.txt");
        Timings.checkpoint("read file");

        final List<String> lines = Files.readAllLines(file.toPath());
        System.out.println("p1: " + calculate(lines.get(0), lines.get(1), 1));
        System.out.println("p2: " + calculate(lines.get(0), lines.get(1), 2));

        Timings.stop();
    }

    private static long[] split(String line, int part) {
        if (part == 1) {
            return Arrays.stream(line.split(":")[1].trim().split(" ")).filter(x -> !x.isEmpty() && !x.isBlank()).mapToLong(Long::parseLong).toArray();
        } else if (part == 2) {
            return new long[]{Long.parseLong(line.split(":")[1].replace(" ", ""))};
        }

        throw new RuntimeException("whoops");
    }

    private static int calculate(String td, String dd, int part) {
        int total = 1;

        final long[] times = split(td, part);
        final long[] dists = split(dd, part);

        for (int i = 0; i < times.length; i++) {
            final long time = times[i];
            final long dist = dists[i];

            int ways = 0;
            for (int j = 0; j < time; j++) {
                if (j * (time - j) > dist) ways++;
            }

            total *= ways;
        }

        return total;
    }
}
