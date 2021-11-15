import javafx.geometry.Rectangle2D;

public class bonus extends MovingThing{
    private int index;
    private int n;

    public bonus() {
        super(1500, 210, "bonus.png",50,50);
        x = 3000;
        y = 210;
        super.getSprite().setViewport(new Rectangle2D(0, 0, 160, 170));

    }

    //the same bonus is used, just displaced on the x-axis
    public void addX(int i){
        this.x=x+i;
        this.y=210; //replaced on ground level
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
        super.getSprite().setViewport(new Rectangle2D(18 + (index/5)%2 * 150, 0, 160, 150));

    }
}
