package aoc2023.day1;

import shared.Timings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {
    private static final Map<String, String> WORDS = new HashMap<>();
    private static final Pattern REGEX = Pattern.compile("(?=(one|two|three|four|five|six|seven|eight|nine|[1-9]))");

    static {
        WORDS.put("one", "1");
        WORDS.put("two", "2");
        WORDS.put("three", "3");
        WORDS.put("four", "4");
        WORDS.put("five", "5");
        WORDS.put("six", "6");
        WORDS.put("seven", "7");
        WORDS.put("eight", "8");
        WORDS.put("nine", "9");
    }

    public static void main(String[] args) throws IOException {
        Timings.start();
        final File file = new File("src/aoc2023/day1/input.txt");
        Timings.checkpoint("read file");
        int sum = 0;

        for (String line : Files.readAllLines(file.toPath())) {
            final Matcher matcher = REGEX.matcher(line);
            String first = null, last = null;

            while (matcher.find()) {
                final String match = matcher.group(1);

                if (Character.isDigit(match.charAt(0))) {
                    if (first == null) first = match;
                    last = match;
                } else {
                    final String word = WORDS.get(match.toLowerCase());

                    if (first == null) first = word;
                    last = word;
                }
            }

            sum += Integer.parseInt(first + last);
        }

        System.out.println(sum);
        Timings.stop();
    }
}