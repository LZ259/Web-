package m1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

class Student {
    private String name;
    private int age;
    private double score;

    public Student(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}

class StudentManager {
    private Student[] students;
    private int count;

    public StudentManager(int capacity) {
        students = new Student[capacity];
        count = 0;
    }

    public void addStudent(Student student) {
        if (count < students.length) {
            students[count++] = student;
        } else {
            throw new ArrayIndexOutOfBoundsException("Student array is full");
        }
    }

    public void sortStudents() {
        if (students == null || count == 0) {
            throw new IllegalArgumentException("Student list is empty or null");
        }
        Arrays.sort(students, 0, count, Comparator.comparingDouble(Student::getScore).reversed());
    }

    public Student[] getStudents() {
        return Arrays.copyOf(students, count);
    }

    public void printStatistics() {
        if (count == 0) {
            System.out.println("No students to display statistics.");
            return;
        }

        double totalAge = 0;
        double totalScore = 0;
        double maxScore = Double.MIN_VALUE;
        double minScore = Double.MAX_VALUE;

        for (int i = 0; i < count; i++) {
            totalAge += students[i].getAge();
            totalScore += students[i].getScore();
            if (students[i].getScore() > maxScore) {
                maxScore = students[i].getScore();
            }
            if (students[i].getScore() < minScore) {
                minScore = students[i].getScore();
            }
        }

        double averageAge = totalAge / count;
        double averageScore = totalScore / count;

        System.out.println("Average Age: " + averageAge);
        System.out.println("Highest Score: " + maxScore);
        System.out.println("Lowest Score: " + minScore);
        System.out.println("Average Score: " + averageScore);
    }
}

public class StudentManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagerSystem manager = new StudentManagerSystem(100);
        boolean continueRunning = true;

        while (continueRunning) {
            try {
                System.out.println("1. 录入学生");
                System.out.println("2. 排序");
                System.out.println("3. 输出统计");
                System.out.println("0. 退出");
                System.out.print("请选择菜单项: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        addStudent(manager, scanner);
                        break;
                    case 2:
                        try {
                            manager.sortStudents();
                            System.out.println("学生排序完成:");
                            for (Student student : manager.getStudents()) {
                                System.out.println(student);
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("错误: " + e.getMessage());
                        }
                        break;
                    case 3:
                        manager.printStatistics();
                        break;
                    case 0:
                        continueRunning = false;
                        break;
                    default:
                        System.out.println("无效选择，请重新选择。");
                }
            } catch (Exception e) {
                System.out.println("发生错误: " + e.getMessage());
            } finally {
                System.out.print("是否继续 (0:退出, 1:继续)? ");
                try {
                    int continueChoice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    if (continueChoice != 1) {
                        continueRunning = false;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("无效输入，程序将退出。");
                    continueRunning = false;
                }
            }
        }

        scanner.close();
    }

    private static void addStudent(StudentManagerSystem manager, Scanner scanner) {
        try {
            System.out.print("请输入学生姓名: ");
            String name = scanner.nextLine();
            System.out.print("请输入学生年龄: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("请输入学生成绩: ");
            double score = Double.parseDouble(scanner.nextLine());

            Student student = new Student(name, age, score);
            manager.addStudent(student);
            System.out.println("学生录入成功!");
        } catch (NumberFormatException e) {
            System.out.println("输入格式错误，请输入有效的数字。");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("学生列表已满，无法添加更多学生。");
        }
    }
}
