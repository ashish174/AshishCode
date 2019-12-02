package concurrency.executorFramework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableDemo {
    public static Logger logger = LoggerFactory.getLogger(CallableDemo.class);

    public static void main(String[] args) {
        try{
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            List<Future> futureList = new ArrayList<>();
            for(int i = 0; i<10; i++){
                Future<String> future = executorService.submit(new CallableTask(i));
                futureList.add(future);
            }
            executorService.shutdown();
            while (!executorService.isTerminated()){
                executorService.awaitTermination(2000, TimeUnit.MILLISECONDS);
            }
            if(executorService.isTerminated()){

                for(Future future : futureList){
                    logger.info("Value : {}", future.get());
                }


            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
