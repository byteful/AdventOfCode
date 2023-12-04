package aoc2023.day4;

import shared.Timings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Day4 {
    public static void main(String[] args) throws IOException {
        Timings.start();
        final File file = new File("src/aoc2023/day4/input.txt");
        Timings.checkpoint("read file");

        int points = 0;
        int cards = 0;

        final List<String> lines = Files.readAllLines(file.toPath());
        final int[] lasts = IntStream.generate(() -> 1).limit(lines.size()).toArray();

        for (int i = 0; i < lines.size(); i++) {
            final String line = lines.get(i);
            final String[] sets = line.split(":")[1].split("\\|");

            final Set<String> winning = new HashSet<>(Arrays.asList(sets[0].split(" ")));
            winning.removeIf(x -> x.isEmpty() || x.isBlank());
            final Set<String> have = new HashSet<>(Arrays.asList(sets[1].split(" ")));
            have.removeIf(x -> x.isEmpty() || x.isBlank());

            final int before = have.size();
            have.removeAll(winning);
            final int after = have.size();

            final int diff = before - after;
            if (diff > 0) {
                points += (int) Math.pow(2, diff - 1);
            }

            final int last = lasts[i];
            for (int j = i + 1; j <= Math.min(i + diff, lasts.length - 1); j++) {
                lasts[j] += last;
            }

            cards += last;
        }

        System.out.println("p1: " + points);
        System.out.println("p2: " + cards);
        Timings.stop();
    }
}
