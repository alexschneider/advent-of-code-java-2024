package aoc.day04;

import aoc.Day;
import aoc.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.xml.stream.events.Characters;

import com.google.common.primitives.Chars;

public class Day04 implements Day {

    @Override
    public String part1(String input) {
        var lines = Utils.splitLines(input);
        var matrix = lines.stream().map(line -> Chars.asList(line.toCharArray())).collect(Collectors.toList());
        var sum = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) {
                    continue;
                }

                sum += countInDirections(matrix, x, y);
            }
        }
        return Integer.toString(sum);
    }

    private boolean isWord(List<List<Character>> matrix, int x, int y, int xDir, int yDir) {
        var wordToFind = "XMAS".toCharArray();
        for (Character c : wordToFind) {
            if (x < 0 || y < 0 || x >= matrix.size() || y >= matrix.get(x).size()) {
                return false;
            }
            if (!matrix.get(x).get(y).equals(c)) {
                return false;
            }
            x += xDir;
            y += yDir;
        }
        return true;
    }

    private int countInDirections(List<List<Character>> matrix, int xDir, int yDir) {
        var sum = 0;
        for (int x = 0; x < matrix.size(); x++) {
            for (int y = 0; y < matrix.size(); y++) {
                if (isWord(matrix, x, y, xDir, yDir)) {
                    sum++;
                }
            }
        }

        return sum;
    }

    private boolean isValidX(List<List<Character>> matrix, int x, int y) {
        if (x == 0 || y == 0 || x == matrix.size() - 1 || y == matrix.get(x).size() - 1) {
            return false;
        }
        var validList = List.of('M', 'S');

        var cross1 = List.of(matrix.get(x - 1).get(y - 1), matrix.get(x + 1).get(y + 1));
        var cross2 = List.of(matrix.get(x + 1).get(y - 1), matrix.get(x - 1).get(y + 1));
        return (cross1.equals(validList) || cross1.equals(validList.reversed()))
                && (cross2.equals(validList) || cross2.equals(validList.reversed()));
    }

    @Override
    public String part2(String input) {
        var lines = Utils.splitLines(input);
        var matrix = lines.stream().map(line -> Chars.asList(line.toCharArray())).collect(Collectors.toList());
        var sum = 0;
        for (int x = 0; x < matrix.size(); x++) {
            for (int y = 0; y < matrix.get(x).size(); y++) {
                if (matrix.get(x).get(y).equals((Character) 'A') && isValidX(matrix, x, y)) {
                    sum++;
                }
            }
        }
        return Integer.toString(sum);
    }

}
