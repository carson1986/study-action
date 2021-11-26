package create.factory;

import java.util.Random;

public class AirplaneFactory implements Factory {

    @Override
    public Enemy create(int screenWidth) {
        Random random = new Random();
        return new Airplane(random.nextInt(screenWidth), 0);
    }
}
