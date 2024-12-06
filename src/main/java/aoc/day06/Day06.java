package aoc.day06;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import aoc.Day;
import aoc.Utils;

public class Day06 implements Day {

    Set<List<Integer>> grid;
    int startingGuardX;
    int startingGuardY;
    int xSize;
    int ySize;

    @Override
    public String part1(String input) {
        init(input);
        var xDir = -1;
        var yDir = 0;
        var visited = Sets.newHashSet();
        var guardX = this.startingGuardX;
        var guardY = this.startingGuardY;
        while (isWithinGrid(guardX, guardY)) {
            if (this.grid.contains(List.of(guardX, guardY))) {
                guardX -= xDir;
                guardY -= yDir;
                var swap = yDir;
                yDir = -xDir;
                xDir = swap;
            } else {
                visited.add(List.of(guardX, guardY));
                guardX += xDir;
                guardY += yDir;
            }
        }
        return Integer.toString(visited.size());
    }

    @Override
    public String part2(String input) {
        init(input);

        var xDir = -1;
        var yDir = 0;
        var guardX = this.startingGuardX;
        var guardY = this.startingGuardY;
        var seenLoops = Sets.newHashSet();
        while (isWithinGrid(guardX, guardY)) {
            if (this.grid.contains(List.of(guardX, guardY))) {
                guardX -= xDir;
                guardY -= yDir;
                var swap = yDir;
                yDir = -xDir;
                xDir = swap;
            } else {
                guardX += xDir;
                guardY += yDir;
                if (!seenLoops.contains(List.of(guardX, guardY)) && isLoopWithAdded(guardX, guardY)) {
                    seenLoops.add(List.of(guardX, guardY));
                }
            }
        }
        return Integer.toString(seenLoops.size());
    }

    private void init(String input) {
        var lines = Utils.splitLines(input);
        this.xSize = lines.size();
        this.ySize = lines.get(0).length();
        this.grid = Sets.newHashSet();
        for (var x = 0; x < lines.size(); x++) {
            for (var y = 0; y < lines.get(x).length(); y++) {
                var ch = lines.get(x).charAt(y);
                if (ch == '#') {
                    this.grid.add(List.of(x, y));
                } else if (ch == '^') {
                    this.startingGuardX = x;
                    this.startingGuardY = y;
                }
            }
        }
    }

    private boolean isWithinGrid(int guardX, int guardY) {
        return guardX >= 0 && guardY >= 0 && guardX < this.xSize && guardY < this.ySize;
    }

    private boolean isLoopWithAdded(int addX, int addY) {
        var xDir = -1;
        var yDir = 0;
        var visited = Sets.newHashSet();
        var guardX = this.startingGuardX;
        var guardY = this.startingGuardY;
        while (isWithinGrid(guardX, guardY)) {
            if (visited.contains(List.of(guardX, guardY, xDir, yDir))) {
                return true;
            } else if ((guardX == addX && guardY == addY) || this.grid.contains(List.of(guardX, guardY))) {
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

}
