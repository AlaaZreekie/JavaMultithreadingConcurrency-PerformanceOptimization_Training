package race.condition;

import java.io.IOException;

public class Main {
    static void main(String [] args) throws InterruptedException, IOException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

//        incrementThread.start();
//        incrementThread.join();
//
//        decrementingThread.start();
//        decrementingThread.join();

        //here each time we run we will have different items count
        incrementThread.start();
        decrementingThread.start();
        incrementThread.join();
        decrementingThread.join();

        System.out.println("We currently have " + inventoryCounter.getItems() + " items");
    }

    public static class IncrementingThread extends Thread {
        private InventoryCounter counter;
        public IncrementingThread(InventoryCounter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for(int i = 0; i < 10000; i++) {
                counter.increment();
            }
        }
    }

    public static class DecrementingThread extends Thread {
        private InventoryCounter counter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.counter = inventoryCounter;
        }

        @Override
        public void run() {
            for(int i = 0; i < 10000; i++) {
                counter.decrement();
            }
        }
    }

    private static class InventoryCounter {
        private int items = 0;
        Object lock = new Object();
        public  void increment() {
            synchronized ( this.lock) {
                items++;
            }
        }
        public synchronized int getItems() {
            synchronized ( this.lock ) {
                return items;
            }
        }
        public void decrement() {
            synchronized ( this.lock) {
                items--;
            }
        }
    }
}
