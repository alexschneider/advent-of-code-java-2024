package aoc.day05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {

    @Test
    public void testPart1() {
        // Given
        String input = "47|53\r\n" + //
                "97|13\r\n" + //
                "97|61\r\n" + //
                "97|47\r\n" + //
                "75|29\r\n" + //
                "61|13\r\n" + //
                "75|53\r\n" + //
                "29|13\r\n" + //
                "97|29\r\n" + //
                "53|29\r\n" + //
                "61|53\r\n" + //
                "97|53\r\n" + //
                "61|29\r\n" + //
                "47|13\r\n" + //
                "75|47\r\n" + //
                "97|75\r\n" + //
                "47|61\r\n" + //
                "75|61\r\n" + //
                "47|29\r\n" + //
                "75|13\r\n" + //
                "53|13\r\n" + //
                "\r\n" + //
                "75,47,61,53,29\r\n" + //
                "97,61,53,29,13\r\n" + //
                "75,29,13\r\n" + //
                "75,97,47,61,53\r\n" + //
                "61,13,29\r\n" + //
                "97,13,75,29,47\r\n" + //
                "";

        // When
        String result = new Day05().part1(input);

        // Then
        assertEquals("143", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = "47|53\r\n" + //
                "97|13\r\n" + //
                "97|61\r\n" + //
                "97|47\r\n" + //
                "75|29\r\n" + //
                "61|13\r\n" + //
                "75|53\r\n" + //
                "29|13\r\n" + //
                "97|29\r\n" + //
                "53|29\r\n" + //
                "61|53\r\n" + //
                "97|53\r\n" + //
                "61|29\r\n" + //
                "47|13\r\n" + //
                "75|47\r\n" + //
                "97|75\r\n" + //
                "47|61\r\n" + //
                "75|61\r\n" + //
                "47|29\r\n" + //
                "75|13\r\n" + //
                "53|13\r\n" + //
                "\r\n" + //
                "75,47,61,53,29\r\n" + //
                "97,61,53,29,13\r\n" + //
                "75,29,13\r\n" + //
                "75,97,47,61,53\r\n" + //
                "61,13,29\r\n" + //
                "97,13,75,29,47\r\n" + //
                "";
        // When
        String result = new Day05().part2(input);

        // Then
        assertEquals("123", result);
    }
}
