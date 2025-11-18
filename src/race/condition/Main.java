package race.condition;

import java.io.IOException;

public class Main {
    static void main(String [] args) throws InterruptedException, IOException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementThread.start();
        incrementThread.join();

        decrementingThread.start();
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
        public void increment() {
            items++;
        }
        public int getItems() {
            return items;
        }
        public void decrement() {
            items--;
        }
    }
}
