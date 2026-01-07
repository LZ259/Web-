package work.BandMenu;

import java.util.Random;
import java.util.Scanner;

public class CreateAccount {
    public static void main(String args[]) {

        //private BandCombineOp mainClassRef;
        //public CreateAccount(BandCombineOp mainClassRef) {

            Scanner sc = new Scanner(System.in);
            Random random = new Random();
            //Account account = new Account();
            System.out.println("您想创建的卡种类：1.储蓄卡 2.信用卡");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    //account.getKind=1;
                case 2:
                    //account.getKind=2;
            }
            String account_num= String.format("%06d", random.nextInt(1000000));
            System.out.println("您的新卡号为" + account_num);
            //account.getAccount_num()=account_num;
            System.out.println("请设置密码");
            int password = sc.nextInt();
            sc.nextLine();
        }
    }

