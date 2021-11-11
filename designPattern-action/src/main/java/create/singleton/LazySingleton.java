package create.singleton;

/**
 * 懒汉单例模式
 */
public class LazySingleton {

    private static volatile LazySingleton instance ;

    private LazySingleton(){

    }

    public static LazySingleton getInstance(){
        if(instance == null){
            synchronized (LazySingleton.class){
                if(instance == null){
                    instance = new LazySingleton();
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
    }
}
