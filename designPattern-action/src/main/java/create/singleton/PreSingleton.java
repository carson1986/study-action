package create.singleton;

/**
 * 饿汉模式单例
 * 预加载模式
 */
public class PreSingleton {

    private static final PreSingleton instance = new PreSingleton();

    private PreSingleton(){

    }

    public static PreSingleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
    }
}
