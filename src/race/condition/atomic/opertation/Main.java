package race.condition.atomic.opertation;

import java.util.Random;

public class Main {
    public static void main() {
        Metrics metrics = new Metrics();

        BusinessLogic businessLogicThread1 = new BusinessLogic(metrics);
        BusinessLogic businessLogicThread2 = new BusinessLogic(metrics);

        MetricsPrinter metricsPrinter = new MetricsPrinter(metrics);

        businessLogicThread1.start();
        businessLogicThread2.start();
        metricsPrinter.start();
    }

    public static class MetricsPrinter extends Thread {
        private Metrics  metrics;
        public MetricsPrinter(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                double currentAvg = metrics.getAvg();
                System.out.println("Current avg: "+ currentAvg);
            }
        }


    }

    public static class BusinessLogic extends Thread {
        private Metrics metrics;
        private Random random = new Random();

        public BusinessLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                long start = System.currentTimeMillis();

                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                long end = System.currentTimeMillis();
                metrics.addSample(end - start);
            }
        }
    }

    public static class Metrics {
        private long count = 0;
        //volatile we need to make this declaration atomic
        private volatile double avg = 0.0;

        //we have to protect this to be atomic
        public synchronized void addSample(long sample) {
            double currentSum = avg * count;
            count++;
            avg = (currentSum + sample) / count;
        }

        public double getAvg() {
            return avg;
        }
    }
}

