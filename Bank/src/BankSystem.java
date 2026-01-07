import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 定义银行卡抽象类，方便后续扩展不同类型的卡
abstract class BankCard {//抽象类，定义了银行卡共有的属性（卡号、密码、余额）以及通用的操作方法
    private String cardNumber;
    private String password;
    private double balance;

    public BankCard(String cardNumber, String password, double balance) {
        this.cardNumber = cardNumber;
        this.password = password;
        this.balance = balance;
    }
//定义了获取各属性的get方法，方便在其他地方获取银行卡的相关信息
    public String getCardNumber() {
        return cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {//存款，实现了简单的存款逻辑，直接将传入的金额加到余额上
        balance += amount;
    }

    public abstract boolean withdraw(double amount);//被声明为抽象方法，因为不同类型的卡（储蓄卡、信用卡等）取款逻辑不同，具体逻辑由子类去实现

    public void transfer(BankCard targetCard, double amount) {//实现了转账功能，先尝试从当前卡取款，如果取款成功则向目标卡存款，完成转账操作
        if (withdraw(amount)) {
            targetCard.deposit(amount);
        }
    }

    public void checkInfo() {//查询信息，输出银行卡的卡号和当前余额信息，方便用户查看账户情况
        System.out.println("卡号: " + cardNumber + ", 余额: " + balance);
    }
}

// 储蓄卡类，继承自银行卡抽象类
class DebitCard extends BankCard {//储蓄卡类
    public DebitCard(String cardNumber, String password, double balance) {
        super(cardNumber, password, balance);
    }
    //继承自BankCard抽象类，复用了父类的属性和部分方法，并且实现了父类中定义的抽象 withdraw方法
    @Override
    public boolean withdraw(double amount) {
        if (amount <= getBalance()) {//在withdraw方法中，首先判断要取款的金额是否小于等于卡内当前余额
            deposit(-amount);
            return true;
        }//如果是，则通过调用deposit方法（传入负数金额实现扣减余额的效果）来更新余额，并返回true表示取款成功
        System.out.println("余额不足，取款失败");
        return false;//若余额不足，则输出提示信息并返回false
    }
}
//同样继承自BankCard抽象类，实现了自己特有的取款和转账逻辑，以体现信用卡与储蓄卡的不同之处
// 信用卡类，继承自银行卡抽象类，考虑透支和手续费情况（简单示例手续费设为取款或转账金额的0.01）
class CreditCard extends BankCard {
    private static final double FEE_RATE = 0.01;//定义了一个私有静态常量FEE_RATE表示手续费率（这里简单设为取款或转账金额的 1%），用于在取款和转账操作中计算实际需要扣除的金额

    public CreditCard(String cardNumber, String password, double balance) {
        super(cardNumber, password, balance);
    }

    @Override
    public boolean withdraw(double amount) {//在withdraw方法中，先计算包含手续费的实际取款金额（取款金额乘以1 + FEE_RATE），然后判断该实际金额是否小于等于卡内余额，如果是则更新余额并返回true表示取款成功，否则输出提示信息并返回false
        double actualWithdrawAmount = amount * (1 + FEE_RATE);
        if (actualWithdrawAmount <= getBalance()) {
            deposit(-actualWithdrawAmount);
            return true;
        }
        System.out.println("信用额度不足，取款失败");
        return false;
    }

    @Override
    public void transfer(BankCard targetCard, double amount) {
        double actualTransferAmount = amount * (1 + FEE_RATE);
        if (actualTransferAmount <= getBalance()) {
            deposit(-actualTransferAmount);
            targetCard.deposit(amount);
        } else {
            System.out.println("信用额度不足，转账失败");
        }
    }
}

public class BankSystem {
    private static final int MAX_CARDS = 100;
    private List<BankCard> cards = new ArrayList<>();

    public static void main(String[] args) {
        BankSystem bankSystem = new BankSystem();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请选择操作:");
            System.out.println("1. 开户");
            System.out.println("2. ATM操作");
            System.out.println("3. POS机消费");
            System.out.println("4. 退出");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    bankSystem.openAccount(scanner);
                    break;
                case 2:
                    bankSystem.atmOperation(scanner);
                    break;
                case 3:
                    bankSystem.posOperation(scanner);
                    break;
                case 4:
                    bankSystem.saveData();
                    System.out.println("已退出系统");
                    return;
                default:
                    System.out.println("无效选择，请重新选择");
            }
        }
    }

    private void openAccount(Scanner scanner) {
        System.out.println("请选择卡类型(1. 储蓄卡  2. 信用卡)");
        int cardType = scanner.nextInt();
        System.out.println("请输入卡号:");
        String cardNumber = scanner.next();
        System.out.println("请输入密码:");
        String password = scanner.next();
        System.out.println("请输入初始余额:");
        double balance = scanner.nextDouble();
        BankCard newCard;
        if (cardType == 1) {
            newCard = new DebitCard(cardNumber, password, balance);
        } else {
            newCard = new CreditCard(cardNumber, password, balance);
        }
        cards.add(newCard);
        System.out.println("开户成功");
    }

    private void atmOperation(Scanner scanner) {
        System.out.println("请输入卡号:");
        String cardNumber = scanner.next();
        BankCard targetCard = findCardByNumber(cardNumber);
        if (targetCard == null) {
            System.out.println("卡号不存在");
            return;
        }
        System.out.println("请输入密码:");
        String password = scanner.next();
        if (!password.equals(targetCard.getPassword())) {
            System.out.println("密码错误");
            return;
        }
        while (true) {
            System.out.println("请选择ATM操作(1. 存钱  2. 取钱  3. 查询账户信息  4. 转账  5. 退出)");
            int atmChoice = scanner.nextInt();
            switch (atmChoice) {
                case 1:
                    System.out.println("请输入存款金额:");
                    double depositAmount = scanner.nextDouble();
                    targetCard.deposit(depositAmount);
                    System.out.println("存款成功");
                    break;
                case 2:
                    System.out.println("请输入取款金额:");
                    double withdrawAmount = scanner.nextDouble();
                    targetCard.withdraw(withdrawAmount);
                    break;
                case 3:
                    targetCard.checkInfo();
                    break;
                case 4:
                    System.out.println("请输入转账目标卡号:");
                    String targetCardNumber = scanner.next();
                    BankCard targetTransferCard = findCardByNumber(targetCardNumber);
                    if (targetTransferCard == null) {
                        System.out.println("目标卡号不存在");
                        continue;
                    }
                    System.out.println("请输入转账金额:");
                    double transferAmount = scanner.nextDouble();
                    targetCard.transfer(targetTransferCard, transferAmount);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("无效选择，请重新选择");
            }
        }
    }

    private void posOperation(Scanner scanner) {
        System.out.println("请输入卡号:");
        String cardNumber = scanner.next();
        BankCard targetCard = findCardByNumber(cardNumber);
        if (targetCard == null) {
            System.out.println("卡号不存在");
            return;
        }
        System.out.println("请输入密码:");
        String password = scanner.next();
        if (!password.equals(targetCard.getPassword())) {
            System.out.println("密码错误");
            return;
        }
        System.out.println("请输入消费金额:");
        double consumeAmount = scanner.nextDouble();
        if (targetCard instanceof CreditCard || targetCard.getBalance() >= consumeAmount) {
            targetCard.withdraw(consumeAmount);
            System.out.println("消费成功");
        } else {
            System.out.println("余额不足，消费失败");
        }
    }

    private BankCard findCardByNumber(String cardNumber) {
        for (BankCard card : cards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card;
            }
        }
        return null;
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("bank_data.ser"))) {
            oos.writeObject(cards);
        } catch (IOException e) {
            System.out.println("数据保存失败: " + e.getMessage());
        }
    }
}
