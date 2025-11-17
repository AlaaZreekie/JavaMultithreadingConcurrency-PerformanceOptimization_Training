import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static final int MAX_PASSWORD = 9999;
    public static void main(String[] args) {
        Random rand = new Random();

        Vault vault = new Vault(rand.nextInt(MAX_PASSWORD));

        List<Thread> threads = new ArrayList<>();

        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThreaD(vault));
        threads.add(new PoliceThreaD());

        for (Thread t : threads) {
            t.start();
        }
    }

    private static class Vault {
        private  int password;
        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            }catch (InterruptedException e) {
            }
            return this.password == guess;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting HackerThread " + this.getName());
            super.start();
        }

    }

    private static class AscendingHackerThread extends HackerThread {
        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess = 0; guess < MAX_PASSWORD; guess++) {
                if(vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guess the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThreaD extends HackerThread {
        public DescendingHackerThreaD(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess = MAX_PASSWORD; guess >= 0; guess--) {
                if(vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guess the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThreaD extends Thread {
        @Override
        public void run() {
            for(int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {

                }
                System.out.println(i);
            }

            System.out.println("Game Over");
            System.exit(0);
        }
    }

}