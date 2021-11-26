package create.factory;

public class Airplane extends Enemy {

    public Airplane(int x, int y){
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("绘制飞机于上层图层，出现坐标："+x+","+y);
        System.out.println("飞机向玩家发起攻击");
    }
}
