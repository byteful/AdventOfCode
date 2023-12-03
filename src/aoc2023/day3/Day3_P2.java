package aoc2023.day3;

import shared.Timings;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static aoc2023.day3.Day3_P1.getAdjacentNumbers;

public class Day3_P2 {
    public static void main(String[] args) throws Exception {
        Timings.start();
        final File file = new File("src/aoc2023/day3/input.txt");
        Timings.checkpoint("read file");

        final List<String> schematic = Files.readAllLines(file.toPath());
        int sum = 0;
        for (int i = 0; i < schematic.size(); i++) {
            for (int j = 0; j < schematic.get(i).length(); j++) {
                final char currentChar = schematic.get(i).charAt(j);

                if (currentChar != '.' && isSymbol(currentChar)) {
                    sum += getAdjacentNumbersSum(schematic, i, j);
                }
            }
        }
        System.out.println(sum);

        Timings.stop();
    }

    private static boolean isSymbol(char ch) {
        return ch == '*';
    }

    private static int getAdjacentNumbersSum(List<String> schematic, int row, int col) {
        int sum = 1;
        final boolean[][] visited = new boolean[schematic.size()][schematic.get(0).length()];

        int runs = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < schematic.size() && j >= 0 && j < schematic.get(i).length()) {
                    final char adjacentChar = schematic.get(i).charAt(j);

                    if (Character.isDigit(adjacentChar) && !visited[i][j]) {
                        runs++;
                        sum *= getAdjacentNumbers(schematic, i, j, visited);
                    }
                }
            }
        }

        return runs != 2 ? 0 : sum;
    }
}
