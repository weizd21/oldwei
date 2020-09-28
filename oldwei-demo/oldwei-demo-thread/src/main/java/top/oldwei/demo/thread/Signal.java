package top.oldwei.demo.thread;

/**
 * @Author:weizd
 * @Date:20-5-28
 *
 * 线程A 和 线程B 轮流打印 signal
 *
 * 利用信号量进行线程间通信
 *
 *
 *
 *
 */
public class Signal {

    private static volatile int signal = 0;

    public static void main(String[] args) {


        Thread threadA = new Thread(new ThreadA());
        Thread threadB = new Thread(new ThreadB());


        threadA.start();

        threadB.start();
    }




    static class ThreadA implements Runnable{

        @Override
        public void run() {
            while (signal < 10){
                if(signal % 2 == 0){
                    System.out.println(Thread.currentThread().getName() +": "+signal);
                    synchronized (this){
                        signal++;
                    }
                }
//                System.out.println(Thread.currentThread().getName());
            }

        }
    }


    static class ThreadB implements Runnable{

        @Override
        public void run() {
            while (signal < 10){
                if(signal % 2 != 0){
                    System.out.println(Thread.currentThread().getName() +": "+signal);
                    synchronized (this){
                        signal++;
                    }
                }
//                System.out.println(Thread.currentThread().getName());
            }

        }
    }

}
