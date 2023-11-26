import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeDisplayer {

    static AtomicInteger counter;
    static Timer timer;

    public static void main(String[] args) {
        counter = new AtomicInteger(0);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                counter.incrementAndGet();
            }
        };
        //create thread to print counter value
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                //To make infinite loop just need while(true)
                while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Program time lasting: " + counter);
                        if(counter.get() == 20){
                            timer.cancel();//end the timer
                            break;//end this loop
                        }
                    }
                }
        });

        timer = new Timer("MyTimer");//create a new timer
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //To make infinite loop just need while(true)
                while (true) {
                           try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("5 seconds have gone: " + counter);
                           if(counter.get() == 20){
                               timer.cancel();//end the timer
                               break;//end this loop
                           }
                }
            }
        });
        t.start();//start thread to display counter
        t1.start();
    }
}
