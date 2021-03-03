package jvm;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * java堆溢出，堆内存不动态扩展
 * VM args : -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {

    static class OOMObject{}

    public static void main(String[] args) {
        List<OOMObject> list = Lists.newArrayList();
        while(true){
            list.add(new OOMObject());
        }
    }
}
