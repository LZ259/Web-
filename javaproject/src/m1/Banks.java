package m1;
import java.util.Scanner;

abstract class Card {
    protected String cardNumber;
    protected String password;
    protected double balance;

    public Card(String cardNumber, String password, double initialBalance) {
        this.cardNumber = cardNumber;
        this.password = password;
        this.balance = initialBalance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("存款成功！当前余额：" + balance);
        } else {
            System.out.println("存款金额必须大于0！");
        }
    }

    public abstract void withdraw(double amount);
    public abstract void transfer(Card targetCard, double amount);

    public boolean consume(double amount) {
        if (this instanceof CreditCard) {
            balance -= amount;
            System.out.println("消费成功！当前余额：" + balance);
            return true;
        } else if (this.balance >= amount) {
            balance -= amount;
            System.out.println("消费成功！当前余额：" + balance);
            return true;
        } else {
            System.out.println("余额不足，消费失败！");
            return false;
        }
    }
}

class SavingsCard extends Card {
    public SavingsCard(String cardNumber, String password, double initialBalance) {
        super(cardNumber, password, initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            balance -= amount;
            System.out.println("取款成功！当前余额：" + balance);
        } else {
            System.out.println("取款金额必须大于0且余额充足！");
        }
    }

    @Override
    public void transfer(Card targetCard, double amount) {
        if (amount > 0 && this.balance >= amount) {
            balance -= amount;
            targetCard.deposit(amount);
            System.out.println("转账成功！当前余额：" + balance);
        } else {
            System.out.println("转账金额必须大于0且余额充足！");
        }
    }
}

class CreditCard extends Card {
    private double creditLimit;
    private double annualFee;

    public CreditCard(String cardNumber, String password, double initialBalance, double creditLimit, double annualFee) {
        super(cardNumber, password, initialBalance);
        this.creditLimit = creditLimit;
        this.annualFee = annualFee;
    }

    @Override
    public void withdraw(double amount) {
        double availableBalance = this.balance + (this.creditLimit - getOutstandingBalance());
        if (amount > 0 && availableBalance >= amount) {
            balance -= amount;
            System.out.println("取款成功，收取手续费！当前余额：" + balance);
        } else {
            System.out.println("取款金额必须大于0且可用余额充足！");
        }
    }

    @Override
    public void transfer(Card targetCard, double amount) {
        double availableBalance = this.balance + (this.creditLimit - getOutstandingBalance());
        if (amount > 0 && availableBalance >= amount) {
            balance -= amount;
            targetCard.deposit(amount);
            System.out.println("转账成功，收取手续费！当前余额：" + balance);
        } else {
            System.out.println("转账金额必须大于0且可用余额充足！");
        }
    }

    private double getOutstandingBalance() {
        return Math.max(0, this.balance - this.creditLimit);
    }
}

class BankSystem {
    private Card[] cards;
    private int cardCount;
    private Scanner scanner;

    public BankSystem(int maxCardCount) {
        this.cards = new Card[maxCardCount];
        this.cardCount = 0;
        this.scanner = new Scanner(System.in);
    }

    public void Banks() {
        while (true) {
            System.out.println("请选择功能：");
            System.out.println("1. 开户");
            System.out.println("2. ATM操作");
            System.out.println("3. POS机消费");
            System.out.println("4. 退出");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    openAccount();
                    break;
                case 2:
                    atmOperation();
                    break;
                case 3:
                    posConsume();
                    break;
                case 4:
                    System.out.println("退出系统！");
                    return;
                default:
                    System.out.println("无效选择，请重新选择！");
            }
        }
    }

    private void openAccount() {
        if (cardCount >= cards.length) {
            System.out.println("已达到最大开卡数量！");
            return;
        }

        System.out.println("请输入卡号：");
        String cardNumber = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();
        System.out.println("请输入初始余额：");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("请选择卡类型（1: 储蓄卡, 2: 信用卡）：");
        int cardType = scanner.nextInt();
        scanner.nextLine();

        Card newCard;
        if (cardType == 1) {
            newCard = new SavingsCard(cardNumber, password, initialBalance);
        } else if (cardType == 2) {
            System.out.println("请输入信用额度：");
            double creditLimit = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("请输入年费：");
            double annualFee = scanner.nextDouble();
            scanner.nextLine();
            newCard = new CreditCard(cardNumber, password, initialBalance, creditLimit, annualFee);
        } else {
            System.out.println("无效的卡类型！");
            return;
        }

        cards[cardCount++] = newCard;
        System.out.println("开户成功！");
    }

    private void atmOperation() {
        System.out.println("请输入卡号：");
        String cardNumber = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();

        Card targetCard = null;
        for (Card card : cards) {
            if (card != null && card.getCardNumber().equals(cardNumber) && card.getPassword().equals(password)) {
                targetCard = card;
                break;
            }
        }

        if (targetCard == null) {
            System.out.println("卡号或密码错误！");
            return;
        }

        while (true) {
            System.out.println("请选择ATM功能：");
            System.out.println("1. 存款");
            System.out.println("2. 取款");
            System.out.println("3. 查询账户信息");
            System.out.println("4. 转账");
            System.out.println("5. 返回上一级");
            int atmChoice = scanner.nextInt();
            scanner.nextLine();

            switch (atmChoice) {
                case 1:
                    System.out.println("请输入存款金额：");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
            }
        }
    }
}
