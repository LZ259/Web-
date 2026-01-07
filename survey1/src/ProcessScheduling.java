import java.util.*;

// 进程控制块（PCB）
class PCB {
    String name;        // 进程名
    int priority;       // 优先数（仅用于优先数算法）
    int cpuTime;        // 已运行CPU时间
    int needTime;       // 还需运行时间
    int count;          // 已运行轮数
    int round;          // 分配的时间片数
    String state;       // 状态（R:运行, W:等待, F:完成）

    public PCB(String name, int needTime) {
        this.name = name;
        this.needTime = needTime;
        this.cpuTime = 0;
        this.count = 0;
        this.round = 2; // 默认时间片为2
        this.state = "W";
        this.priority = 50 - needTime; // 初始优先数
    }
}

public class ProcessScheduling {
    static Queue<PCB> readyQueue = new LinkedList<>();  // 就绪队列
    static List<PCB> finishedQueue = new ArrayList<>(); // 完成队列
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("选择调度算法：\n1. 时间片轮转法\n2. 优先数算法");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 清除换行符

        System.out.println("输入进程信息（格式：进程名 需要时间），输入end结束：");
        List<PCB> processes = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("end")) break;
            String[] parts = input.split("\\s+");
            processes.add(new PCB(parts[0], Integer.parseInt(parts[1])));
        }

        switch (choice) {
            case 1:
                roundRobin(processes);
                break;
            case 2:
                priorityScheduling(processes);
                break;
            default:
                System.out.println("无效选择");
        }
    }

    // 时间片轮转法
    static void roundRobin(List<PCB> processes) {
        readyQueue.addAll(processes);
        while (!readyQueue.isEmpty()) {
            PCB current = readyQueue.poll();
            current.state = "R";
            printStatus(processes, false);

            // 模拟运行一个时间片（2单位）
            int timeSlice = Math.min(2, current.needTime);
            current.cpuTime += timeSlice;
            current.needTime -= timeSlice;
            current.count++;
            current.state = current.needTime == 0 ? "F" : "W";

            if (current.needTime > 0) {
                readyQueue.add(current);
            } else {
                finishedQueue.add(current);
            }
        }
        printFinalResult(processes);
    }

    // 优先数算法
    static void priorityScheduling(List<PCB> processes) {
        PriorityQueue<PCB> priorityQueue = new PriorityQueue<>(
                (a, b) -> b.priority - a.priority // 按优先数降序排列
        );
        priorityQueue.addAll(processes);

        while (!priorityQueue.isEmpty()) {
            PCB current = priorityQueue.poll();
            current.state = "R";
            printStatus(processes, true);

            // 模拟运行一个时间片（2单位）
            int timeSlice = Math.min(2, current.needTime);
            current.cpuTime += timeSlice;
            current.needTime -= timeSlice;
            current.count++;
            current.priority -= 3; // 每次运行后优先数减3
            current.state = current.needTime == 0 ? "F" : "W";

            if (current.needTime > 0) {
                priorityQueue.add(current);
            } else {
                finishedQueue.add(current);
            }
        }
        printFinalResult(processes);
    }

    // 打印当前状态
    static void printStatus(List<PCB> processes, boolean isPriority) {
        System.out.println("\n当前状态：");
        System.out.printf("%-6s %-8s %-8s %-6s %-6s %-6s\n",
                "Name", "cputime", "needtime", "count", isPriority ? "pri" : "round", "state");
        for (PCB p : processes) {
            System.out.printf("%-6s %-8d %-8d %-6d %-6d %-6s\n",
                    p.name, p.cpuTime, p.needTime, p.count,
                    isPriority ? p.priority : p.round, p.state);
        }

        System.out.print("就绪队列：");
        for (PCB p : processes) {
            if (p.state.equals("W")) System.out.print(p.name + " ");
        }
        System.out.print("\n完成队列：");
        for (PCB p : finishedQueue) {
            System.out.print(p.name + " ");
        }
        System.out.println();
    }

    // 打印最终结果
    static void printFinalResult(List<PCB> processes) {
        System.out.println("\n最终结果：");
        printStatus(processes, false);
    }
}
