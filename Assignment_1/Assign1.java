
import java.math.BigInteger;

public class Assign1 {

    private BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("factorial value must be non-negative");
        }
        BigInteger total = BigInteger.valueOf(1);

        for (int i = 1; i <= n; i++) {
            total = total.multiply(BigInteger.valueOf(i));
        }
        return total;
    }

    private int fibonacci(int n) {
        if (n < 0 || n > 40) {
            throw new IllegalArgumentException("fibonacci value must be between 0 and 40");
        }
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static void main(String[] args) {
        System.out.println();
    }
}