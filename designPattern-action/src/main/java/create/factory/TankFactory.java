package create.factory;

import java.util.Random;

public class TankFactory implements Factory {

    @Override
    public Enemy create(int screenWidth) {
        Random random = new Random();
        return new Tank(random.nextInt(screenWidth), 0);
    }
}
