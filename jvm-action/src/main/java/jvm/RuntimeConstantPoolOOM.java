package jvm;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 方法区和常量池OOM
 * VM args :-XX:PermSize=6M -XX:MaxPermSize=6M  JDK8.0已不支持
 */
public class RuntimeConstantPoolOOM {

    /**
     * 测试OOM
     * VM args : -Xmx6m -Xms6M
     * Result : Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
     */
    public void testOOM(){
        Set<String> set = Sets.newHashSet();
        short i = 0;
        while(true){
            set.add(String.valueOf(i++).intern());
        }
    }

    /**
     * 测试引用，JDK8以后，常量池已经都放到堆里了
     */
    public void testReference(){
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println("str1.intern() == str1 " + (str1.intern() == str1)); //true

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println("str2.intern() == str2 " + (str2.intern() == str2)); //false
    }

    public static void main(String[] args) {
        RuntimeConstantPoolOOM runtimeConstantPoolOOM = new RuntimeConstantPoolOOM();
//        runtimeConstantPoolOOM.testOOM();
        runtimeConstantPoolOOM.testReference();

    }
}
