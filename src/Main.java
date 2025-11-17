import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Long> inputNumbers = Arrays.asList(0L, 3453L, 34543L, 2324L, 8888L, 23L, 2455L, 5556L);
        //Calc factorial for the list
    }

    public static class FactorialThread extends Thread {
        private long number;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;


        public FactorialThread(long number) {
            this.number = number;
        }

        @Override
        public void run() {
            this.result = factorial(this.number);
            this.isFinished = true;
        }

        public BigInteger factorial(long number) {
            BigInteger result = BigInteger.ONE;

            for (long i = number; i > 0; i--) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result;
        }

        public boolean isFinish() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }
    }

}