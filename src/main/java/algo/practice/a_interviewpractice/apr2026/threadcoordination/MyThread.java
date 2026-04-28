package algo.practice.a_interviewpractice.apr2026.threadcoordination;

public class MyThread extends Thread{
    String name;
    int sequence;
    LockStateHolder lockStateHolder;

    public MyThread(String name, int sequence, LockStateHolder lockStateHolder){
        this.name=name;
        this.sequence=sequence;
        this.lockStateHolder=lockStateHolder;
    }

    public void run(){
        int value = sequence;
        int myIndex = sequence; // 1,2,3
        for(int i=0; i<20; i++){
            synchronized(lockStateHolder){
                while((lockStateHolder.turn%3+1)!=myIndex){
                    try {
                        lockStateHolder.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                value = sequence + 3*i;
                System.out.println(""+value);
                lockStateHolder.turn++;
                //dont use notify. What if T1 wake T3, instead of T2. Then T3 also went to waiting.
                //Now T1 & T2 & T3 all waiting
                //So must use notifyAll()
                lockStateHolder.notifyAll();
            }
        }
    }




    public static void main(String[] args){
        LockStateHolder lockStateHolder = new LockStateHolder(0);
        MyThread t1 = new MyThread("Thread1", 1, lockStateHolder);
        MyThread t2 = new MyThread("Thread2", 2, lockStateHolder);
        MyThread t3 = new MyThread("Thread3", 3, lockStateHolder);

        t1.start();
        t2.start();
        t3.start();

        //join() is to make main thread/func wait until the worker threads complete
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Done");
    }

}
