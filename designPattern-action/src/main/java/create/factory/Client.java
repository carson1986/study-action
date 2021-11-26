package create.factory;

public class Client {

    public static void main(String[] args) {


        factory();

    }

    public static void simpleFactory(){
        System.out.println("游戏开始");
        SimpleFactory factory = new SimpleFactory(100);
        factory.create("Airplane").show();
        factory.create("Tank").show();
    }

    public static void factory(){
        System.out.println("游戏开始");

        int screenWidth = 100;
        Factory factory = new AirplaneFactory();
        for(int i=0; i<5; i++){
            factory.create(screenWidth).show();
        }

        factory = new TankFactory();
        for(int i=0; i<5; i++){
            factory.create(screenWidth).show();
        }
    }
}
