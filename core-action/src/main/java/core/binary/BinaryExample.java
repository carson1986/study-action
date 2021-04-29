package core.binary;

/**
 * 二进制操作
 * 按位逻辑运算
 * 位移运算
 */
public class BinaryExample {

    /**
     * 把Long, Integer, Short转成二进制和十六进制
     */
    public static void covertToBinary(){
        Long l = 10L;
        System.out.println(Long.toBinaryString(l));
        System.out.println(Long.toHexString(l));

        Long l1 = -10L;
        System.out.println(Long.toBinaryString(l1));
        System.out.println(Long.toHexString(l1));

        Integer i = 10;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Long.toHexString(i));

        Integer i1 = -10;
        System.out.println(Integer.toBinaryString(i1));
        System.out.println(Long.toHexString(i1));

        Short s = (short)10;
        System.out.println(Integer.toBinaryString(s));
        System.out.println(Integer.toHexString(s));

        Short s1 = (short)-10;
        System.out.println(Integer.toBinaryString(s1));
        System.out.println(Integer.toHexString(s1));
    }

    /**
     * 按位逻辑运算
     */
    public static void bitLogicalOperators(){
        System.out.println("3&1="+(3&1));
        System.out.println("-1&1="+(-1&1));

        System.out.println("3|1="+(3|1));
        System.out.println("-1|1="+(-1|1));

        System.out.println("3^1="+(3^1));
        System.out.println("-1^1="+(-1^1));

        System.out.println("~3="+(~3));
        System.out.println("~-1="+(~-1));
    }

    /**
     * 位移运算
     */
    public static void bitMove(){
        System.out.println("1<<2="+(1<<2));
        System.out.println("-1<<2="+(-1<<2));

        System.out.println("8>>2="+(8>>2));
        System.out.println("-1>>2="+(-1>>2));

        System.out.println("8>>>2="+(8>>>2));
        System.out.println("-1>>>2="+(-1>>>2));

        //-1左移几位，然后取消，可以计算出几位二进制的最大值
        System.out.println("~(-1<<4)="+~(-1<<4));

    }

    public static void main(String[] args) {
//        covertToBinary();
//        bitLogicalOperators();
        bitMove();
    }
}
