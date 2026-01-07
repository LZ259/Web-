package Tollgate;


import java.util.Scanner;

public class Highway {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("欢迎光临xx收费站");
            System.out.println("1.七座以下小汽车 2.大客车 3.货车");
            System.out.println("请输入您的车辆型号：");
            int choice = sc.nextInt();
            System.out.println("请输入行驶的路程（km）:");
            double mileage=sc.nextDouble();
            switch (choice) {
                case 1: {
                    double charge = mileage * 0.5;
                    System.out.println("您需要缴费"+charge+"元");
                    System.out.println("支付成功！谢谢付费，再见！");
                    break;
                }
                case 2: {
                    System.out.println("请输入乘客人数：");
                    int passengerCount=sc.nextInt();
                    double charge=passengerCount*0.05+mileage*0.8;
                    System.out.println("您需要缴费"+charge+"元");
                    System.out.println("支付成功！谢谢付费，再见！");
                    break;
                }
                case 3: {
                    System.out.println("请输入车辆重量(吨)：");
                    int vehicleWeight=sc.nextInt();
                    double charge=vehicleWeight*0.01+mileage*0.7;
                    System.out.println("您需要缴费"+charge+"元");
                    System.out.println("支付成功！谢谢付费，再见！");
                    break;
                }
            }
        }

    }
}
