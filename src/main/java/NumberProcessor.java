import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class NumberProcessor extends Thread{

    private int number;

    private Consumer<Integer> processor;

    private AtomicBoolean isNumberProcessed = new AtomicBoolean(true);

    public NumberProcessor(Consumer<Integer> processor){
        this.processor = processor;
    }
    public void process(int number){
        this.number = number;
        this.isNumberProcessed.set(false);
    }

    public boolean isNumberProcessed(){
        return isNumberProcessed.get();
    }

    @Override
    public void run(){
        while(!isInterrupted()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if(isNumberProcessed.get()){
                continue;
            }

          processor.accept(number);

            isNumberProcessed.set(true);
        }
    }
}
