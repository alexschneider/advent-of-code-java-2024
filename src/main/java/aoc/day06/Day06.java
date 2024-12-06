package aoc.day06;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Sets;
import aoc.Day;
import aoc.Utils;

public class Day06 implements Day {

    @Override
    public String part1(String input) {
        var lines = Utils.splitLines(input);
        var grid = Sets.newHashSet();
        var guardX = 0;
        var guardY = 0;
        for (var x = 0; x < lines.size(); x++) {
            for (var y = 0; y < lines.get(x).length(); y++) {
                var ch = lines.get(x).charAt(y);
                if (ch == '#') {
                    grid.add(Pair.of(x, y));
                } else if (ch == '^') {
                    guardX = x;
                    guardY = y;
                }
            }
        }
        var xDir = -1;
        var yDir = 0;
        var visited = Sets.newHashSet();
        while (guardX >= 0 && guardY >= 0 && guardX < lines.size() && guardY < lines.get(guardX).length()) {
            if (grid.contains(Pair.of(guardX, guardY))) {
                guardX -= xDir;
                guardY -= yDir;
                var swap = yDir;
                yDir = -xDir;
                xDir = swap;
            } else {
                visited.add(Pair.of(guardX, guardY));
                guardX += xDir;
                guardY += yDir;
            }
        }
        return Integer.toString(visited.size());
    }

    private boolean isLoop(Set<Pair<Integer, Integer>> grid, int guardX, int guardY, int xSize, int ySize) {
        var xDir = -1;
        var yDir = 0;
        var visited = Sets.newHashSet();
        while (guardX >= 0 && guardY >= 0 && guardX < xSize && guardY < ySize) {
            if (visited.contains(List.of(guardX, guardY, xDir, yDir))) {
                return true;
            } else if (grid.contains(Pair.of(guardX, guardY))) {
                guardX -= xDir;
                guardY -= yDir;
                visited.remove(List.of(guardX, guardY, xDir, yDir));
                var swap = yDir;
                yDir = -xDir;
                xDir = swap;
            } else {
                visited.add(List.of(guardX, guardY, xDir, yDir));
                guardX += xDir;
                guardY += yDir;
            }
        }
        return false;
    }

    @Override
    public String part2(String input) {
        var lines = Utils.splitLines(input);
        var grid = new HashSet<Pair<Integer, Integer>>();
        var guardX = 0;
        var guardY = 0;
        var xSize = lines.size();
        var ySize = lines.get(0).length();
        for (var x = 0; x < xSize; x++) {
            for (var y = 0; y < ySize; y++) {
                var ch = lines.get(x).charAt(y);
                if (ch == '#') {
                    grid.add(Pair.of(x, y));
                } else if (ch == '^') {
                    guardX = x;
                    guardY = y;
                }
            }
        }
        var sum = 0;
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                var thisSet = new HashSet<Pair<Integer, Integer>>();
                thisSet.addAll(grid);
                thisSet.add(Pair.of(x, y));
                if (isLoop(thisSet, guardX, guardY, xSize, ySize)) {
                    sum += 1;
                }
            }
        }
        return Integer.toString(sum);
    }
}
