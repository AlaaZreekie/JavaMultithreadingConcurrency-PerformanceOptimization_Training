import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Thread thread = new Thread(new BlockingTask());
        System.out.println("Starting thread");
        thread.start();
        System.out.println("Waiting for thread");
        thread.interrupt();
        System.out.println("Thread interrupted");
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
}