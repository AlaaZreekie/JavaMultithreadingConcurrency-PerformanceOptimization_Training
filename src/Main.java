import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Long> inputNumbers = Arrays.asList(0L, 3453L, 34543L, 2324L, 8888L, 23L, 2455L, 5556L);
        //Calc factorial for the list

        List<FactorialThread> factorialThreads = new ArrayList<>();

        for (Long inputNumber : inputNumbers) {
            factorialThreads.add(new FactorialThread(inputNumber));
        }

        for (FactorialThread factorialThread : factorialThreads) {
            factorialThread.setDaemon(true);
            factorialThread.start();
            //here we have the rase condition between the start and the check for the result
            //so we do not know in witch stage is each thread "here join solve this"
        }

        for (FactorialThread factorialThread : factorialThreads) {
            try {
                //set value of how much u want to wait
                factorialThread.join(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < factorialThreads.size(); i++) {
            FactorialThread factorialThread = factorialThreads.get(i);
            if(factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " =  " + factorialThread.getResult());
            }
            else  {
                System.out.println("Factorial of " + inputNumbers.get(i) + " still in progress");
            }
        }
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

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }
    }

}