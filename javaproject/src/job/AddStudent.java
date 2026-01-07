package job;

import java.util.Random;
import java.util.Scanner;

public class AddStudent {
   public void b(){
        Scanner sc=new Scanner(System.in);
        Student e= new Student();
        //System.out.print("请输入入学年份：");
        System.out.print("请输入学生姓名：");
        String s_name = sc.nextLine();
        System.out.print("请输入学生所选课程及选课时期：");
        String course_data = sc.nextLine();
        System.out.print("请输入学生应选学分数：");
        String s_credit = sc.nextLine();
        Student student=new Student(e.s_code,s_name,course_data,s_credit);
        System.out.println("添加学生成功！");
    }
}
