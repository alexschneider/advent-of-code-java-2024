/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package aoc;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

public class App {

    /**
     * This is the "entrypoint" for the application. That is, it is the first
     * code that gets run for the project.
     *
     * @param args Java will pass the command line arguments as this parameter.
     * The first argument should be the day to run (e.g. "1" or "12") and the
     * second (optional) argument should be the part to run ("1" or "2"). The
     * second argument defaults to "1".
     */
    public static void main(String... args) {
        int year = defaultYear();
        int day = defaultDay();

        if (args.length != 0) {
            day = intOrDie(args[0]);
        }

        int part = 1;
        if (args.length > 1) {
            part = intOrDie(args[1]);
        }

        String input = readInput(year, day);
        Day dayInstance = getDayInstance(day);

        String result;
        if (part == 1) {
            result = dayInstance.part1(input);
        } else {
            result = dayInstance.part2(input);
        }

        System.out.println(result);
    }

    /**
     * This is just a helper method that can be used if you add a {@code main}
     * method to your Day.
     *
     * For example, you could add this to Day01.java:
     *
     * <code><pre>
     * public static void main(String[] args) {
     *   App.runPart1ForDay(1);
     * }
     * </pre></code>
     *
     * @param day The day to run. Make sure it matches the Day you are calling
     * it from!
     *
     */
    public static void runPart1ForDay(int day) {
        main(String.valueOf(day), "1");
    }

    /**
     * This is just a helper method that can be used if you add a {@code main}
     * method to your Day.
     *
     * For example, you could add this to Day01.java:
     *
     * <code><pre>
     * public static void main(String[] args) {
     *   App.runPart2ForDay(1);
     * }
     * </pre></code>
     *
     * @param day The day to run. Make sure it matches the Day you are calling
     * it from!
     *
     */
    public static void runPart2ForDay(int day) {
        main(String.valueOf(day), "1");
    }

    /**
     * If today is in the month of December, return which day of December it is. In other words, if today is December 3rd, this will return 3.
     *
     * If today is <i>not</i> in the month of December, this will return 1.
     *
     * @return The day of the month if today is in December, or 1 otherwise.
     */
    private static int defaultDay() {
        LocalDate today = LocalDate.now();
        if (today.getMonth() == Month.DECEMBER) {
            return today.getDayOfMonth();
        } else {
            return 1;
        }

    }

    /**
     * This will return the current year during December, or the prior year any
     * other time.
     *
     * That way, if you are still working on the 2024 puzzles in January 2025,
     * this will still return 2024.
     *
     * If you are working on puzzles older than a year, hard code the year
     * instead of calling this method.
     *
     * @return The current year during December, or the prior year otherwise.
     */
    private static int defaultYear() {
        LocalDate today = LocalDate.now();
        if (today.getMonth() == Month.DECEMBER) {
            return today.getYear();
        } else {
            return today.getYear() - 1;
        }
    }

    /**
     * Reads a file from the classpath (src/main/resources directory). If anything goes wrong (missing file,
     * IOException) the result will be empty.
     *
     * @param fileName The name of the file. Should be something in src/main/resources
     *
     * @return An Optional containing the contents of the file as a String, or
     * an empty Optional if the file could not be read for any reason.
     */
    private static Optional<String> readClassPathFile(String fileName) {
        URL url = ClassLoader.getSystemResource(fileName);
        if (url == null) {
            System.out.println("No file " + fileName + " on the classpath.");
            return Optional.empty();
        }
        Path path;
        try {
            path = Paths.get(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        if (!Files.exists(path)) {
            return Optional.empty();
        }

        try {
            return Optional.of(Files.readString(path));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * <p>Determine the input file name for the current day. Format is "day##.txt" where ## is the zero-padded day number.</p>
     *
     * <p>Example:</p>
     * <ul>
     * <li>day01.txt</li>
     * <li>day13.txt</li>
     * </ul>
     *
     * @param day The day number (1-25)
     * @return The name of the input file for the given day.
     */
    private static String inputFileName(int day) {
        String paddedDay = String.valueOf(day);
        if (day < 10) {
            paddedDay = "0" + day;
        }
        return "day" + paddedDay + ".txt";
    }

    /**
     * This will download your personal input from adventofcode.com and store it
     * in the file specified by {@link #inputFileName(int)}.
     *
     * @param year The year to use
     * @param day The puzzle day to get input for
     * @param cookie Your personal login cookie. Get it by inspecting the
     * "cookie" header when you are logged into adventofcode.com. Do not share
     * this value.
     * @return The contents of the input that were written to a
     * file.
     */
    private static String downloadInput(int year, int day, String cookie) {
        String url = String.format("https://adventofcode.com/%d/day/%d/input", year, day);
        System.out.println("Downloading " + url);

        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("cookie", cookie.trim())
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to read input from " + url
                    + " using cookie " + cookie.substring(0, 5) + "***** ."
                    + " Response was " + response.statusCode()
                    + " " + response.body());
        }

        String input = response.body();
        try {
            Files.writeString(Paths.get("./src/main/resources/" + inputFileName(day)), input, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return input;
    }

    /**
     * Reads the input for the given year and day.
     *
     * If a file exists where specified by {@link #inputFileName(int)}, then that will be used.
     *
     * If no such file exists, but there is a 'session.txt' file on the
     * classpath (in src/main/resources) then the input will be downloaded using
     * the contents of session.txt as the cookie header.
     *
     * @param year the year to get input for
     * @param day the day to get input for
     * @return The input as a String.
     */
    private static String readInput(int year, int day) {
        Optional<String> fileInput = readClassPathFile(inputFileName(day));

        String input = null;
        if (fileInput.isPresent()) {
            input = fileInput.get();
        } else {
            Optional<String> cookie = readClassPathFile("session.txt");
            if (cookie.isPresent()) {
                input = downloadInput(year, day, cookie.get());
            } else {
                System.err.println("Cannot get input for year " + year + " and day " + day + "."
                        + " Either put the input at 'src/main/resources/day[xx].txt"
                        + " or use your browser's network inspector to read the 'cookie' header from the request for"
                        + " input and store it in 'src/main/resources/session.txt' (this file will be ignored by Git).");
                System.exit(1);
            }
        }
        return input;
    }

    /**
     * Parse the given String as an Integer or die trying (exit the JVM).
     *
     * @param numeric A String that better be numeric.
     * @return The String as an int
     */
    private static int intOrDie(String numeric) {
        try {
            return Integer.valueOf(numeric);
        } catch (NumberFormatException e) {
            System.out.println("The argument '" + numeric + "' could not be interpreted as an integer number.");
            System.exit(1);
        }
        return -1;
    }

    /**
     * Uses "Reflection" (Java metaprogramming) to get an instance of the class for the given day.
     *
     * Specifically, if you pass "1" it will return an instance of {@code aoc.day01.Day01}.
     *
     * If there is no Day class for the given day, the JVM will exit.
     *
     * @param day The day number
     * @return An instance of the Day implementation for the given day (e.g. Day01.class for day = 1)
     */
    private static Day getDayInstance(int day) {
        String dayClassName = String.format("aoc.day%02d.Day%02d", day, day);
        try {
            Class<?> dayClass = Class.forName(dayClassName);
            if (!Day.class.isAssignableFrom(dayClass)) {
                throw new IllegalArgumentException(dayClassName + " does not implement aoc.Day");
            }
            return (Day) dayClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to load " + dayClassName
                    + " class. Did you remember to create it and implement the Day interface?");
            System.exit(1);
        }
        return null;
    }
}
