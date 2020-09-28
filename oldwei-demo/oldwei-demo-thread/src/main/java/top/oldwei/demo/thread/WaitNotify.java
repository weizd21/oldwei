package top.oldwei.demo.thread;


/**
 * @Author:weizd
 * @Date:20-5-29
 * 等待通知机制
 *
 *
 *
 *
 */
public class WaitNotify {

    private static Object lock = new Object();

    public static void main(String[] args) {

        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();

    }


    static class ThreadA implements Runnable{

        @Override
        public void run() {

            synchronized (lock){
                for(int i=0;i<10;i++){
                    try {
                        System.out.println(Thread.currentThread().getName()+ ": "+ i);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }


    static class ThreadB implements Runnable{

        @Override
        public void run() {

            synchronized (lock){
                for(int i=0;i<10;i++){
                    try {
                        System.out.println(Thread.currentThread().getName()+ ": "+ i);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

}
