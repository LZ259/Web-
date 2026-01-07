package m1;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Course {
    String courseId;
    String courseName;
    int credits;
    String teacher;
    int maxStudents;
    List<String> selectedStudents = new ArrayList<>();

    public Course(String courseId, String courseName, int credits, String teacher, int maxStudents) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
    }

    public String toString() {
        return "课程编号: " + courseId + ", 课程名称: " + courseName + ", 学分: " + credits + ", 授课老师姓名: " + teacher;
    }
}

class Student {
    String studentId;
    String name;
    List<String> selectedCourses = new ArrayList<>();
    List<Date> selectedCourseDates = new ArrayList<>();
    int requiredCredits;

    public Student(String studentId, String name, int requiredCredits) {
        this.studentId = studentId;
        this.name = name;
        this.requiredCredits = requiredCredits;
    }

    public String toString() {
        return "学生学号: " + studentId + ", 学生姓名: " + name;
    }
}
public class _12{
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    studentSelectCourse();
                    break;
                case 4:
                    query();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("输入错误，请重新输入！");
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. 课程信息");
        System.out.println("2. 学生信息");
        System.out.println("3.学生选课");
        System.out.println("4. 查询");
        System.out.println("5. 退出系统");
        System.out.print("请输入你的选择: ");
    }

    private static void addCourse() {
        System.out.print("输入课程编号(KC+6位数字): ");
        String courseId = scanner.nextLine();
        System.out.print("输入课程名称: ");
        String courseName = scanner.nextLine();
        System.out.print("输入课程学分: ");
        int credits = scanner.nextInt();
        scanner.nextLine();
        System.out.print("输入授课老师姓名: ");
        String teacher = scanner.nextLine();
        System.out.print("输入最大学生数: ");
        int maxStudents = scanner.nextInt();
        scanner.nextLine();

        Course course = new Course(courseId, courseName, credits, teacher, maxStudents);
        courses.add(course);
        System.out.println("课程添加成功.");
    }

    private static void addStudent() {
        int year = 2024;
        String deptCode = String.format("%02d", random.nextInt(100));
        String studentNumber = String.format("%04d", students.size() + 1);
        String studentId = year + deptCode + studentNumber;

        System.out.print("输入学生姓名: ");
        String name = scanner.nextLine();
        System.out.print("输入学生应修学分: ");
        int requiredCredits = scanner.nextInt();
        scanner.nextLine();

        Student student = new Student(studentId, name, requiredCredits);
        students.add(student);
        System.out.println("学生添加成功.");
        System.out.println("该学生学号为"+studentId);
    }

    private static void studentSelectCourse() {
        printCourseList();
        System.out.print("输入学生学号: ");
        String studentId = scanner.nextLine();
        System.out.print("输入想选课程编号: ");
        String courseId = scanner.nextLine();

        Student student = findStudentById(studentId);
        Course course = findCourseById(courseId);

        if (student == null) {
            System.out.println("未找到该学生.");
            return;
        }

        if (course == null) {
            System.out.println("未找到该课程.");
            return;
        }

        if (course.selectedStudents.size() >= course.maxStudents) {
            System.out.println("课程已满.");
            return;
        }

        course.selectedStudents.add(studentId);
        student.selectedCourses.add(course.courseId);
        student.selectedCourseDates.add(new Date());

        System.out.println("选课成功.");
    }

    private static void printCourseList() {
        System.out.println("空闲课程:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void query() {
        System.out.print("输入学生学号或课程编号查询: ");
        String input = scanner.nextLine();

        Course course = findCourseById(input);
        if (course != null) {
            System.out.println("选择该课程的学生姓名" + course.courseName + ":");
            for (String studentId : course.selectedStudents) {
                Student student = findStudentById(studentId);
                if (student != null) {
                    System.out.println(student);
                }
            }
        } else {
            Student student = findStudentById(input);
            if (student != null) {
                System.out.println(student);
                System.out.println("已选课程:");
                for (int i = 0; i < student.selectedCourses.size(); i++) {
                    Course c = findCourseById(student.selectedCourses.get(i));
                    if (c != null) {
                        System.out.println("课程: " + c.courseName + ", 学分: " + c.credits);
                    }
                }
                int totalCredits = student.selectedCourses.size() * 3;
                System.out.println("总学分: " + totalCredits);
            } else {
                System.out.println("所有课程信息:");
                for (Course cours : courses) {
                    System.out.println("课程: " + cours.courseName);
                    for (String studentId : cours.selectedStudents) {
                        Student studen = findStudentById(studentId);
                        if (student != null) {
                            System.out.println("  学生: " + studen.name);
                        }
                    }
                }
            }
        }
    }

    private static Course findCourseById(String courseId) {
        for (Course course : courses) {
            if (course.courseId.equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    private static Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.studentId.equals(studentId)) {
                return student;
            }
        }
        return null;
    }
}