package jvm;

public class ReferenceCountingGC {

    private Object instance = null;

    private static final int _1MB = 1024 * 1024;

    /**
     * 该成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testReferenceGC(){
        ReferenceCountingGC objectA = new ReferenceCountingGC();
        ReferenceCountingGC objectB = new ReferenceCountingGC();
        objectA.instance = objectB;
        objectB.instance = objectA;

        objectA = null;
        objectB = null;

        System.gc();
    }

    public static void main(String[] args) {
        testReferenceGC();
    }
}
