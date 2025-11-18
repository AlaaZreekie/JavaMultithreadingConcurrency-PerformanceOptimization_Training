package locks.reenterantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        PricesContainer pricesContainer = new PricesContainer();
        PriceUpdater priceUpdater = new PriceUpdater(pricesContainer);
        priceUpdater.start();

        Thread displayThread = new Thread(() -> {
            while (true) {
                if (pricesContainer.getLockObject().tryLock()) {
                    try {
                        System.out.println("---- Cryptocurrency Prices ----");
                        System.out.printf("Bitcoin (BTC): \t\t%.2f%n", pricesContainer.getBitcoinPrice());
                        System.out.printf("Ethereum (ETH): \t%.2f%n", pricesContainer.getEtherPrice());
                        System.out.printf("Litecoin (LTC): \t%.2f%n", pricesContainer.getLitecoinPrice());
                        System.out.printf("Bitcoin Cash (BCH): \t%.2f%n", pricesContainer.getBitcoinCashPrice());
                        System.out.printf("Ripple (XRP): \t\t%.4f%n", pricesContainer.getRipplePrice());
                        System.out.println("---------------------------------");
                    } finally {
                        pricesContainer.getLockObject().unlock();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        displayThread.setDaemon(true);
        displayThread.start();
    }

    public static class PricesContainer {
        private Lock lockObject = new ReentrantLock();
        private double bitcoinPrice;
        private double etherPrice;
        private double litecoinPrice;
        private double bitcoinCashPrice;
        private double ripplePrice;

        public Lock getLockObject() {
            return lockObject;
        }

        public double getBitcoinPrice() {
            return bitcoinPrice;
        }

        public void setBitcoinPrice(double bitcoinPrice) {
            this.bitcoinPrice = bitcoinPrice;
        }

        public double getEtherPrice() {
            return etherPrice;
        }

        public void setEtherPrice(double etherPrice) {
            this.etherPrice = etherPrice;
        }

        public double getLitecoinPrice() {
            return litecoinPrice;
        }

        public void setLitecoinPrice(double litecoinPrice) {
            this.litecoinPrice = litecoinPrice;
        }

        public double getBitcoinCashPrice() {
            return bitcoinCashPrice;
        }

        public void setBitcoinCashPrice(double bitcoinCashPrice) {
            this.bitcoinCashPrice = bitcoinCashPrice;
        }

        public double getRipplePrice() {
            return ripplePrice;
        }

        public void setRipplePrice(double ripplePrice) {
            this.ripplePrice = ripplePrice;
        }
    }

    public static class PriceUpdater extends Thread {
        private PricesContainer pricesContainer;
        private Random random = new Random();

        public PriceUpdater(PricesContainer pricesContainer) {
            this.pricesContainer = pricesContainer;
        }

        @Override
        public void run() {
            while (true) {
                pricesContainer.getLockObject().lock();
                try {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pricesContainer.setBitcoinPrice(random.nextInt(20000));
                    pricesContainer.setEtherPrice(random.nextInt(2000));
                    pricesContainer.setLitecoinPrice(random.nextInt(500));
                    pricesContainer.setBitcoinCashPrice(random.nextInt(5000));
                    pricesContainer.setRipplePrice(random.nextDouble());
                } finally {
                    pricesContainer.getLockObject().unlock();
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
