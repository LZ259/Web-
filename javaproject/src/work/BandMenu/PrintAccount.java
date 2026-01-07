package work.BandMenu;

import java.util.Scanner;

public class PrintAccount {
    public static void main(String args[]) {
        Account account = new Account();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("1.存钱  2.取钱  3.查询账户信息  4.转账  5.退出");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println("请输入你的账号：");//识别账号

            switch (choice) {
                    case 1:
                        System.out.println("请输入你要存储的金额：");
                        int addmoney = sc.nextInt();
                        sc.nextLine();
                        account.getBalance() += addmoney * (1 - 0.01);
                        break;
                    case 2:
                        System.out.println("请输入你要取出的金额：");
                        int decreasemoney = sc.nextInt();
                        sc.nextLine();
                        if (decreasemoney > account.balance && account.getKind = 1) {
                            System.out.println("余额不足！");
                        } else {
                            account.getAccount_num() -= decreasemoney * (1 + 0.01);
                        }
                        break;
                    case 3:
                        System.out.println(account.toString());
                        break;
                    case 4:
                        System.out.println("请输入您转账对象的帐号：");//识别账号


                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("输入错误，请重新输入！");
            }
        }
    }
}