package jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 本机直接内存溢出
 * VM args : -Xmx20M -XX:MaxDirectMemorySize=10M (实际测试，并没有生效)
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Throwable {

        int num = 0;
        Field unsafeFiled = Unsafe.class.getDeclaredFields()[0];
        unsafeFiled.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeFiled.get(null);
        try {
            while (true) {
                num++;
                unsafe.allocateMemory(_1MB);
            }
        }catch (Throwable e){
            System.out.println("num = "+num);
            throw  e;
        }
    }
}
