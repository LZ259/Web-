package job;

import java.util.Scanner;

public class AddCourse {
    public void c(){
    Scanner sc=new Scanner(System.in);
    Course b=new Course();
    System.out.print("请输入课程编号（格式：KC+6位编号）:");
    String c_code = sc.nextLine();
    System.out.print("请输入课程名称:");
    String c_name = sc.nextLine();
    System.out.print("请输入课程学分:");
    String c_credit = sc.nextLine();
    System.out.print("请输入课程授课老师名:");
    String t_name = sc.nextLine();
    System.out.print("请输入课程可选课人数:");
    int s_number = sc.nextInt();
    Course courses = new Course(c_code,c_name,b.c_credit,t_name,s_number);
    //courses.add(courses);
    System.out.println("添加课程成功！");
    }
}
