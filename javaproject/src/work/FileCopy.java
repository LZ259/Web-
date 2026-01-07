package work;

import java.io.*;

public class FileCopy {
        public static void main(String[] args) {
            File inputFile = new File("fcopy.in");
            File outputFile = new File("fcopy.out");

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String processedLine = processLine(line);
                    writer.write(processedLine);
                    writer.newLine();
                }

                System.out.println("File copied and processed successfully.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static String processLine(String line) {
            StringBuilder result = new StringBuilder();
            boolean lastCharWasSpace = false;

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);

                if (Character.isWhitespace(c)) {
                    if (!lastCharWasSpace) {
                        result.append(' ');
                        lastCharWasSpace = true;
                    }
                } else {
                    result.append(c);
                    lastCharWasSpace = false;
                }
            }

            // Ensure the result doesn't end with a trailing space
            if (result.length() > 0 && Character.isWhitespace(result.charAt(result.length() - 1))) {
                result.setLength(result.length() - 1);
            }

            return result.toString();
        }
    }
