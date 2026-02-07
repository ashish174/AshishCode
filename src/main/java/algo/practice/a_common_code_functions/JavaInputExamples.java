package algo.practice.a_common_code_functions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class JavaInputExamples {

    // ==================== 2. CONSOLE INPUT (Scanner) ====================

    public static void readFromConsoleScanner() {
        System.out.println("\n=== Reading from Console using Scanner ===");
        Scanner scanner = new Scanner(System.in);

        // Reading a single line
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        // Reading an integer
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        // Reading a double
        System.out.print("Enter your GPA: ");
        double gpa = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        // Reading multiple integers on one line
        System.out.print("Enter 3 numbers separated by space: ");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        System.out.println("Name: " + name + ", Age: " + age + ", GPA: " + gpa);
        System.out.println("Numbers: " + a + ", " + b + ", " + c);

        scanner.close();
    }

    // ==================== 3. BUFFERED READER (faster for large inputs) ====================

    public static void readFromConsoleBufferedReader() throws IOException {
        System.out.println("\n=== Reading from Console using BufferedReader ===");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter a line of text: ");
        String line = reader.readLine();

        // Parsing integers from input
        System.out.print("Enter numbers separated by space: ");
        String[] numbers = reader.readLine().split(" ");
        int[] intArray = Arrays.stream(numbers)
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println("You entered: " + line);
        System.out.println("Numbers: " + Arrays.toString(intArray));

        reader.close();
    }


    // ==================== 4. READING FROM TEXT FILE ====================

    public static void readTextFile(String filename) {
        System.out.println("\n=== Reading Text File ===");

        // Method 1: Using Scanner
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            System.out.println("-- Using Scanner --");
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }

        // Method 2: Using BufferedReader (better for large files)
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            System.out.println("\n-- Using BufferedReader --");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Method 3: Read all lines at once (Java 8+)
        try {
            System.out.println("\n-- Using Files.readAllLines --");
            List<String> lines = Files.readAllLines(Paths.get(filename));
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Method 4: Using Files.lines() with Stream (Java 8+)
        try {
            System.out.println("\n-- Using Files.lines() Stream --");
            Files.lines(Paths.get(filename))
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }



    // ==================== 6. READING CSV FILE ====================

    public static void readCSVFile(String filename) {
        System.out.println("\n=== Reading CSV File ===");

        // Method 1: Using BufferedReader with split
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (isHeader) {
                    System.out.println("Headers: " + Arrays.toString(values));
                    isHeader = false;
                } else {
                    System.out.println("Data: " + Arrays.toString(values));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }

        // Method 2: Using Scanner
        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.useDelimiter(",|\\n");

            while (scanner.hasNext()) {
                System.out.print(scanner.next() + " | ");
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }


}
