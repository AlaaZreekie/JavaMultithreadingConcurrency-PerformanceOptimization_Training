import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("20000000"), new BigInteger("33333333333")));

        thread.start();
        //this will stop the thread from working for long long time
        thread.interrupt();
    }

    private static class BlockingTask implements Runnable {
        @Override
        public void run() {
            //do things
            try {
                Thread.sleep(500000);
            }catch (InterruptedException e){
                System.out.println(e + " interrupted");
            }

        }
    }

    private static class LongComputationTask implements Runnable {

        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                //calling the interrupt method is not enough so we have to make sure that the hot spot in the code can be interrupted
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " interrupted");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        };
    }
}