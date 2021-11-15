import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class MovingThing {
    protected double x;
    protected double y;
    private ImageView sprite;
    protected double width;
    protected double height;

    public ImageView getSprite() {
        return sprite;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public MovingThing(double x, double y, String fileName, double width, double height) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        Image spriteSheet = new Image(fileName);
        sprite = new ImageView(spriteSheet);
        sprite.setX(x);
        sprite.setY(y);

    }

    public Rectangle2D getHitBox(){
        return new Rectangle2D(x+1,y+1,width-10, height+10);
    }

}



