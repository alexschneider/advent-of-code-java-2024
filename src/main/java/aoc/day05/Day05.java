package aoc.day05;

import java.util.Arrays;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Sets;
import aoc.Day;
import aoc.Utils;

public class Day05 implements Day {

    @Override
    public String part1(String input) {
        var splitInput = input.split("(\\r?\\n){2}");
        var rules = buildRules(splitInput[0]);
        var lines = Utils.splitLines(splitInput[1]);

        var sum = 0;
        for (var line : lines) {
            sum += findValidOrderNum(rules, line);
        }
        return Integer.toString(sum);
    }

    @Override
    public String part2(String input) {
        var splitInput = input.split("(\\r?\\n){2}");
        var rules = buildRules(splitInput[0]);
        var lines = Utils.splitLines(splitInput[1]);

        var sum = 0;
        for (var line : lines) {
            if (findValidOrderNum(rules, line) == 0) {
                sum += findInvalidOrderNum(rules, line);
            }
        }
        return Integer.toString(sum);
    }

    private ArrayListMultimap<String, String> buildRules(String input) {
        var lines = Utils.splitLines(input);
        ArrayListMultimap<String, String> rules = ArrayListMultimap.create();
        for (var line : lines) {
            var rule = line.split("\\|");
            rules.put(rule[0], rule[1]);
        }
        return rules;
    }

    private int findMiddleNum(String[] pages) {
        return Integer.parseInt((String) pages[pages.length / 2]);
    }

    private int findValidOrderNum(ArrayListMultimap<String, String> rules, String line) {
        var seen = Sets.newHashSet();
        var pages = line.split(",");
        for (var page : pages) {
            for (var rule : rules.get(page)) {
                if (seen.contains(rule)) {
                    return 0;
                }
            }

            seen.add(page);
        }

        return findMiddleNum(pages);
    }

    private int findInvalidOrderNum(ArrayListMultimap<String, String> rules, String line) {
        var pages = line.split(",");

        Arrays.sort(pages, (s1, s2) -> {
            if (rules.containsEntry(s1, s2)) {
                return -1;
            } else if (rules.containsEntry(s2, s1)) {
                return 1;
            } else {
                return 0;
            }
        });

        return findMiddleNum(pages);
    }

}
