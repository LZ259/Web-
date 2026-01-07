package job;

class AccountBook {
    String accountName;
    double income;
    double outcome;
    AccountBook(String s,double num1,double num2){//初始化 给成员变量赋初值 初值为accountName
        accountName=s;
        income=num1;
        outcome=num2;
    }
}
public class zhangpu{
    public static void main(String args[]) {
        double balance;
        AccountBook accountBook_1;//声明
        accountBook_1 = new AccountBook("Zhangsan", 30, 10);//创建
        balance= accountBook_1.income-accountBook_1.outcome;
        System.out.println(accountBook_1.accountName+", income: "+(int)accountBook_1.income+"*, outcome: "+(int)accountBook_1.outcome+"*, balance: "+(int)balance+"*.");
    }
}
