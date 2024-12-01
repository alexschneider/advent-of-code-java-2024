package aoc.day01;

import aoc.Day;
import aoc.Utils;

import java.util.Collections;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMultiset;

public class Day01 implements Day {

    @Override
    public String part1(String input) {
        var lines = Utils.splitLines(input);
        var left = lines.stream()
                .map(t -> Integer.parseInt(t.split("[ ]+")[0]))
                .sorted()
                .collect(Collectors.toList());
        var right = lines.stream()
                .map(t -> Integer.parseInt(t.split("[ ]+")[1]))
                .sorted()
                .collect(Collectors.toList());
        var sum = 0;
        for (var i = 0; i < left.size(); i++) {
            var diff = Math.abs(left.get(i) - right.get(i));
            sum += diff;
        }
        return Integer.toString(sum);
    }

    @Override
    public String part2(String input) {
        var lines = Utils.splitLines(input);
        var right = lines.stream()
                .map(t -> Integer.parseInt(t.split("[ ]+")[1]))
                .collect(ImmutableMultiset.toImmutableMultiset());

        var sum = lines.stream()
                .map(t -> Integer.parseInt(t.split("[ ]+")[0]))
                .map(t -> right.count(t) * t)
                .reduce(0, Integer::sum);

        return Integer.toString(sum);
    }

}
