package job;

import javax.management.Query;
import java.util.Scanner;
import java.util.Random;


class Course{
    public String c_code;
    private String c_name;
    public int c_credit;
    private String t_name;
    private int s_number;

    public Course() {
    }

    public Course(String c_code, String c_name, int c_credit, String t_name, int s_number) {
        this.c_code = c_code;
        this.c_name = c_name;
        this.c_credit = c_credit;
        this.t_name = t_name;
        this.s_number = s_number;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public int getC_credit() {
        return c_credit;
    }

    public void setC_credit(int c_credit) {
        this.c_credit = c_credit;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public int getS_number() {
        return s_number;
    }

    public void setS_number(int s_number) {
        this.s_number = s_number;
    }
}
class Student {
    public static int s_code=2024000000;
    private String s_name;
    private String course_data;
    private String s_credit;

    public int gets_code(){
        Random random=new Random();
        int x=random.nextInt(0,100);
        s_code=s_code+x*10000+1;
        return s_code;
    }

    public Student() {
    }

    public Student(int s_code, String s_name, String course_data, String s_credit){
        this.s_code=s_code;
        this.s_name=s_name;
        this.course_data=course_data;
        this.s_credit=s_credit;
    }
    public int getS_code(){
        return s_code;
    }
    public String getS_name(){
        return s_name;
    }

    public String getCourse_data() {
        return course_data;
    }

    public String getS_credit() {
        return s_credit;
    }
}
public class xuanke {
    public static void main(String[] args) {
        Student students = new Student();
        final int MAX_NUM_OF_Courses=100;
        Course[] courses=new Course[MAX_NUM_OF_Courses];
        Scanner sc=new Scanner(System.in);
        AddCourse addCourse=new AddCourse();
        AddStudent addStudent=new AddStudent();
        StudentChooseCourse studentChoosecourse=new StudentChooseCourse();
        Query query=new Query();
        //int i=0,j=0;
        while(true){
            System.out.println("MainMenu");
            System.out.println("1.课程信息 2.学生信息 3.学生选课 4.查询 5.退出系统");
            int choice=sc.nextInt();
            switch (choice){
                case 1: {
                    //i++;
                    addCourse.c();
                    break;
                }
                case 2: {
                    //j++;
                    addStudent.b();
                    System.out.println("该学生学号为"+students.s_code);
                    break;
                }
                case 3:{
                    //if(courses!=null){
                        //for(j=0;j<courses.length;j++)
                         //   System.out.println(courses[j]);
                       for(Course course:courses){
                            System.out.println(course);
                        }
                       studentChoosecourse.a();
                       break;
                }

                case 4: {
                   //query.d();
                    break;
                }
                case 5:{
                    break;
                }
                default: {
                    System.out.println("输入错误，请重新输入！");
                    break;
                }
            }
        }
    }
}