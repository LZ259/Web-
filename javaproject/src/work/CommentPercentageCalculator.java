package work;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CommentPercentageCalculator {
        public static void main(String[] args) {
            String filePath = "filein.c"; // 假设filein.c在当前目录下
            double commentPercentage = calculateCommentPercentage(filePath);
            System.out.println((int)commentPercentage + "%");
        }

        public static double calculateCommentPercentage(String filePath) {
            StringBuilder fileContent = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return 0.0;
            }

            String content = fileContent.toString();
            int totalChars = content.length();
            int commentChars = 0;
            boolean inComment = false;

            for (int i = 0; i < totalChars; i++) {
                char currentChar = content.charAt(i);
                if (!inComment && currentChar == '/' && i + 1 < totalChars && content.charAt(i + 1) == '*') {
                    inComment = true;
                    i++; // 跳过'*'
                } else if (inComment && currentChar == '*' && i + 1 < totalChars && content.charAt(i + 1) == '/') {
                    inComment = false;
                    i++; // 跳过'/'
                } else if (inComment) {
                    commentChars++;
                }
            }

            return (double)commentChars / totalChars * 100;
        }
}
