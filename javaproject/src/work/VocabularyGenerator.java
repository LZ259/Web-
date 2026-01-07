package work;

import java.io.*;
import java.nio.file.*;
import java.util.*;


public class VocabularyGenerator {
        public static void main(String[] args) {
            // 文件路径
            Path inputPath = Paths.get("in.txt");
            Path outputPath = Paths.get("words.txt");

            // 用于存储单词的集合（自动去重，且忽略大小写）
            Set<String> wordSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

            try (BufferedReader reader = Files.newBufferedReader(inputPath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // 分割单词（假设单词由空格、标点符号等非字母字符分隔）
                    String[] words = line.split("[\\W_]+");
                    for (String word : words) {
                        // 忽略空字符串和非字母字符串
                        if (word.matches("[a-zA-Z]+")) {
                            wordSet.add(word.toLowerCase());
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading the input file: " + e.getMessage());
                return;
            }

            try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
                for (String word : wordSet) {
                    writer.write(word);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Error writing to the output file: " + e.getMessage());
            }
        }
    }