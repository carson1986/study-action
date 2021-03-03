package jvm;

/**
 * 虚拟机栈和本地方法栈溢出
 * VM args: -Xss128K
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    /**
     * 栈递归
     */
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public void test(){
        long unused1,unused2,unused3,unused4,unused5,unused6,unused7,unused8,unused9,unused10,
             unused11,unused12,unused13,unused14,unused15,unused16,unused17,unused18,unused19,unused20,
             unused21,unused22,unused23,unused24,unused25,unused26,unused27,unused28,unused29,unused30, 
             unused31,unused32,unused33,unused34,unused35,unused36,unused37,unused38,unused39,unused40;
        stackLength++;
        test();
        unused1=unused2=unused3=unused4=unused5=unused6=unused7=unused8=unused9=unused10=
        unused11=unused12=unused13=unused14=unused15=unused16=unused17=unused18=unused19=unused20=
        unused21=unused22=unused23=unused24=unused25=unused26=unused27=unused28=unused29=unused30=
        unused31=unused32=unused33=unused34=unused35=unused36=unused37=unused38=unused39=unused40=0;
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF stackSOF = new JavaVMStackSOF();
        try {
//            stackSOF.stackLeak();
            stackSOF.test();
        }catch (Throwable e){
            System.out.println("stackLength: "+ stackSOF.stackLength);
            throw e;
        }
    }
}
