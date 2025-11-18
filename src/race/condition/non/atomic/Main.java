package race.condition.non.atomic;

public class Main {
    static void main() {
        SharedClass sharedClass = new SharedClass();
        Thread thread1 = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                sharedClass.checkForDataRace();
            }
        });
        Thread thread2 = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                sharedClass.increment();
            }
        });

        thread2.start();
        thread1.start();
    }

    public  static class SharedClass {
        private int x = 0;
        private int y = 0;

        public void increment() {
            x++;
            y++;
        }

        public void checkForDataRace() {
            if(y > x) {
                System.out.println("Data race is full and y > x");
            }
        }
    }
}
