package Tollgate;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

interface TollStrategy {
    double calculateToll(double distance, double... additionalParams);
}


class CarTollStrategy implements TollStrategy {

    public double calculateToll(double distance, double... additionalParams) {
        return distance * 0.5;
    }
}


class BusTollStrategy implements TollStrategy {

    public double calculateToll(double distance, double... additionalParams) {
        int passengerCount = (int) additionalParams[0];
        return passengerCount * 0.05 + 0.8 * distance;
    }
}


class TruckTollStrategy implements TollStrategy {

    public double calculateToll(double distance, double... additionalParams) {
        double weight = additionalParams[0];
        return weight * 0.01 + 0.7 * distance;
    }
}


class TollStation {
    private Map tollStrategies = new HashMap<>();

    public TollStation() {
        registerTollStrategy("小车", new CarTollStrategy());
        registerTollStrategy("客车", new BusTollStrategy());
        registerTollStrategy("货车", new TruckTollStrategy());
    }


    public void registerTollStrategy(String vehicleType, TollStrategy strategy) {
        tollStrategies.put(vehicleType, strategy);
    }

    public double calculateToll(String vehicleType, double distance, double... additionalParams) {
        TollStrategy strategy = tollStrategies.get(vehicleType.toLowerCase());
        if (strategy == null) {
            throw new IllegalArgumentException("新汽车型号: " + vehicleType);
        }
        return strategy.calculateToll(distance, additionalParams);
    }
}

public class Charge {
    public static void main(String[] args) {
        TollStation tollStation = new TollStation();
        Scanner scanner = new Scanner(System.in);
        String welcomeMessage = "欢迎光临XX收费站";
        String thankYouMessage = "谢谢付费，再见";

        while (true) {
            System.out.println(welcomeMessage);

            System.out.print("请输入车辆类型（小车/客车/火车/其他）：");
            String vehicleType = scanner.nextLine().trim().toLowerCase();
            if (vehicleType.equals("其他")) {
                break;
            }

            System.out.print("请输入行驶距离（km）：");
            double distance = scanner.nextDouble();
            scanner.nextLine();

            double[] additionalParams = new double[0];
            if (vehicleType.equals("客车")) {
                System.out.print("请输入乘客人数：");
                int passengerCount = scanner.nextInt();
                additionalParams = new double[]{passengerCount};
                scanner.nextLine();
            } else if (vehicleType.equals("货车")) {
                System.out.print("请输入货车重量（吨）：");
                double weight = scanner.nextDouble();
                additionalParams = new double[]{weight};
                scanner.nextLine(); // Consume newline
            }

            try {
                double toll = tollStation.calculateToll(vehicleType, distance, additionalParams);
                System.out.printf("应收费用：%.2f元\n", toll);
                // 模拟付费
                System.out.println("模拟付费成功！");
                System.out.println(thankYouMessage);
            } catch (IllegalArgumentException e) {
                System.out.println("错误：" + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }
}