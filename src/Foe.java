import javafx.geometry.Rectangle2D;

public class Foe extends MovingThing {
    private int index;
    private int n;

    public Foe() {
        super(2000, 210, "foe.png",50,50);
        x = 2000;
        y = 210;
        super.getSprite().setViewport(new Rectangle2D(0, 0, 160, 110));

    }

    //the same foe is used, just displaced on the x-axis and replaced on ground level
    public void addX(int i){
        this.x=x+i;
        this.y=210;
    }

    public void addY(int i){
        this.y=y+i;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }


    public void update() {
        index++;
        super.getSprite().setViewport(new Rectangle2D(18 + (index/5)%2 * 150, 0, 160, 110));

    }
}
