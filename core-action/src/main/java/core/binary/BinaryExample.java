package core.binary;

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

    public static void main(String[] args) {
        covertToBinary();
    }
}
