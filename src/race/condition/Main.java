package race.condition;

import java.io.IOException;

public class Main {
    static void main(String [] args) throws InterruptedException, IOException {

    }

    public static class IncrementThread extends Thread {

    }

    private static class InverntoryCounter {
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
