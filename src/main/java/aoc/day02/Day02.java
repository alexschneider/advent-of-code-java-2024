package aoc.day02;

import aoc.Day;
import aoc.Utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 implements Day {

    @Override
    public String part1(String input) {
        var lines = Utils.splitLines(input);
        var safe = 0;
        for (var line : lines) {
            var dataList = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());

            if (isSafe(dataList, false)) {
                safe++;
            }

        }
        return Integer.toString(safe);
    }

    private boolean isSafe(List<Integer> data, boolean problemDampener) {
        var lastData = 0;
        var increasing = 0;

        for (var i = 0; i < data.size(); i++) {
            var num = data.get(i);
            if (lastData != 0) {
                var difference = Math.abs(num - lastData);
                if (difference < 1 || difference > 3) {
                    if (problemDampener) {
                        return tryProblemDampener(data, i);
                    } else {
                        return false;
                    }
                }

                var thisIncreasing = (num - lastData) / difference;
                if (increasing == 0) {
                    increasing = thisIncreasing;
                } else if (thisIncreasing != increasing) {
                    if (problemDampener) {
                        return tryProblemDampener(data, i);
                    } else {
                        return false;
                    }
                }
            }

            lastData = num;
        }
        return true;
    }

    private boolean tryProblemDampener(List<Integer> data, int i) {
        var dataWithoutCurrent = new LinkedList<Integer>(data);
        dataWithoutCurrent.remove(i);
        if (isSafe(dataWithoutCurrent, false)) {
            return true;
        }

        var dataWithoutPrevious = new LinkedList<Integer>(data);
        dataWithoutPrevious.remove(i - 1);
        if (isSafe(dataWithoutPrevious, false)) {
            return true;
        }

        if (i > 1) {
            var dataWithoutSecondPrevious = new LinkedList<Integer>(data);
            dataWithoutSecondPrevious.remove(i - 2);
            if (isSafe(dataWithoutSecondPrevious, false)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String part2(String input) {
        var lines = Utils.splitLines(input);
        var safe = 0;
        for (var line : lines) {
            var dataList = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());

            if (isSafe(dataList, true)) {
                safe++;
            }
        }
        return Integer.toString(safe);
    }

}
