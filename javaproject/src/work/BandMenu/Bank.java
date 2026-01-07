package work.BandMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Account{
    public int getKind;
    public int kind;
    public String account_num;
    public int password;
    public int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getAccount_num() {
        return account_num;
    }

    public void setAccount_num(String account_num) {
        this.account_num = account_num;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    List<String> selectedAccounts = new ArrayList<>();

    public Account(int kind,String account_num,int password,int balance){
        this.kind=kind;
        this.account_num=account_num;
        this.password=password;
        this.balance=balance;
    }
    public String toString(){
        return  "账号"+account_num+"密码"+password+"余额"+balance;
    }
}


public class Bank{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);

        CreateAccount createAccount=new CreateAccount();

        boolean exit = false;
        while (!exit) {
//            printMenu();
            System.out.println("MENU");
            System.out.println("1.开户  2.ATM  3.pos机消费  4.退出 ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    //createAccount;
                    break;
                case 2:
                    //PrintAccount;
                    break;
                case 3:
                    //Expense;
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("输入错误，请重新输入！");
            }
        }

    }
}

//public static void printMenu() {
//    System.out.println("MENU");
//    System.out.println("1.开户  2.ATM  3.pos机消费  4.退出 ");
//}

