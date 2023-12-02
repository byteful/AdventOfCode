package aoc2023.day2;

import shared.Timings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Day2 {
    public static void main(String[] args) throws Exception {
        Timings.start();
        final File file = new File("src/aoc2023/day2/input.txt");
        Timings.checkpoint("read file");

        partOne(file);
        Timings.checkpoint("part one finished");
        partTwo(file);
        Timings.stop();
    }

    private static void partTwo(File file) throws IOException {
        int sumPower = 0;
        for (String line : Files.readAllLines(file.toPath())) {
            final String[] gameData = line.split(":");
            final String cubes = gameData[1].trim();

            int biggestRed = 0, biggestBlue = 0, biggestGreen = 0;
            for (String set : cubes.split(";")) {
                for (String cube : set.trim().split(",")) {
                    final String[] data = cube.trim().split(" ");
                    final int amount = Integer.parseInt(data[0]);
                    final String type = data[1];

                    if(type.equals("red") && amount > biggestRed) {
                        biggestRed = amount;
                    }
                    if(type.equals("green") && amount > biggestGreen) {
                        biggestGreen = amount;
                    }
                    if(type.equals("blue") && amount > biggestBlue) {
                        biggestBlue = amount;
                    }
                }
            }

            sumPower += (biggestRed * biggestGreen * biggestBlue);
        }

        System.out.println(sumPower);
    }

    private static void partOne(File file) throws IOException {
        int sumId = 0;
        gameLoop:
        for (String line : Files.readAllLines(file.toPath())) {
            final String[] gameData = line.split(":");
            final int gameId = Integer.parseInt(gameData[0].split(" ")[1].trim());
            final String cubes = gameData[1].trim();
            for (String set : cubes.split(";")) {
                for (String cube : set.trim().split(",")) {
                    final String[] data = cube.trim().split(" ");
                    final int amount = Integer.parseInt(data[0]);
                    final String type = data[1];

                    if(type.equals("red") && amount > 12) {
                        continue gameLoop;
                    }
                    if(type.equals("green") && amount > 13) {
                        continue gameLoop;
                    }
                    if(type.equals("blue") && amount > 14) {
                        continue gameLoop;
                    }
                }
            }
            sumId += gameId;
        }

        System.out.println(sumId);
    }
}
