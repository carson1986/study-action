package core.thread;

public class CreateThread {



    public static void main(String[] args) {
        ExtendThread extendThread = new ExtendThread();
        extendThread.start();

        Thread runThread = new Thread(new ImplRunnable());
        runThread.start();
    }
}

/**
 * 继承Thread类
 */
class ExtendThread extends Thread{

    @Override
    public void run(){
        System.out.println("hello extent thread");
    }
}

/**
 * 实现runnable接口
 */
class ImplRunnable implements Runnable{

    public void run() {
        System.out.println("hello impl runnable");
    }
}
