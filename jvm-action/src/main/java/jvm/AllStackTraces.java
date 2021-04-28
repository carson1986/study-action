package jvm;

import java.util.Map;

/**
 * 查询所有的堆栈信息
 */
public class AllStackTraces {

    public static void main(String[] args) {
        Map<Thread, StackTraceElement[]> theadStackTraceMap =  Thread.getAllStackTraces();
        for(Thread thread : theadStackTraceMap.keySet()){
            StackTraceElement[] stackTraceElements = theadStackTraceMap.get(thread);
            System.out.print(thread.getName()+"========");
            for(StackTraceElement element : stackTraceElements){
                System.out.print(""+element);
            }
        }
    }
}
