import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StaticThing {
    private double x;
    private double y;
    private ImageView sprite;

    public ImageView getSprite(){ return sprite; }

    public StaticThing(double xpos, double ypos, String fileName, Integer x, Integer y, Integer length, Integer width){
        this.x=xpos;
        this.y=ypos;

        Image spriteSheet = new Image(fileName);
        sprite = new ImageView(spriteSheet);
        sprite.setViewport(new Rectangle2D(x,y,length, width));
        sprite.setX(xpos);
        sprite.setY(ypos);

    }
}

