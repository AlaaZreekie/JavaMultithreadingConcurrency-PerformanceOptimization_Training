

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run(){
                //Code that will run in a new thread
                System.out.println("We are now on the thread: "+Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getPriority());
                throw new RuntimeException("Interrupted");
            }
        });


        thread.setName("New Worker Thread");
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in thread: " + Thread.currentThread().getName()+ " before staring new thread");
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Uncaught exception in thread: "+Thread.currentThread().getName()+ " "+e);
            }
        });
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName()+ " after staring new thread");

        Thread.sleep(10000);
    }
}