

public class Main {

    public static void main(String[] args) {

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



    }
}