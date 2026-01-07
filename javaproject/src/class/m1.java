
import java.util.Scanner;
public class m1 {
    static final float PI = 3.1415926f;

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        float radius = sc.nextFloat();
        System.out.println("圆的周长是：" + 2 * PI * radius);
        System.out.println("圆的面积是：" + PI * radius * radius);
    }
}
