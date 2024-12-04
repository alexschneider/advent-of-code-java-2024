package aoc.day03;

import aoc.Day;
import aoc.Utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day03 implements Day {

    @Override
    public String part1(String input) {
        var pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        var matcher = pattern.matcher(input);
        var sum = 0;
        while (matcher.find()) {
            var left = Integer.parseInt(matcher.group(1));
            var right = Integer.parseInt(matcher.group(2));
            sum += left * right;
        }
        return Integer.toString(sum);
    }

    @Override
    public String part2(String input) {
        var inputPattern = Pattern.compile("don't\\(\\).+?do\\(\\)", Pattern.DOTALL | Pattern.MULTILINE);
        var inputs = inputPattern.split(input);
        var sum = 0;
        for (var item : inputs) {
            var pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
            var matcher = pattern.matcher(item);
            while (matcher.find()) {
                var left = Integer.parseInt(matcher.group(1));
                var right = Integer.parseInt(matcher.group(2));
                sum += left * right;
            }
        }
        return Integer.toString(sum);
    }

}
