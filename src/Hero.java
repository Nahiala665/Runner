import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hero extends MovingThing{
    private double vx;
    private double vy;
    private double ax;
    private double ay;
    private int index;

    public Hero(){
        super(1500,240,"Alien.png",50,50);
        x=1500;
        y=240;
        this.vx=400;
        this.ax=0;
        this.vy=0;
        this.ay=0;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }


    public void jump() {
        if (y == 250) { //the hero can only jump if he is on the ground
            ay = -60000; //When the hero jump, the acceleration on y is set to -6000 if
        }
    }

    public void update(){
        double elapsedTime=0.016;  ////fixed update at 16ms (60Hz)
        ax=10;
        vx=vx+elapsedTime*ax;
        x=x+elapsedTime*vx;
        vy=vy+elapsedTime*ay;
        y=y+elapsedTime*vy;
        if (y>=250) { //maintain the hero on ground level
            y=250;
            vy=0;
        }
        if (vy<0) { //hero is jumping up
            super.getSprite().setViewport(new Rectangle2D(20,160,70,110));
        }
        if (vy>0){ //hero is falling down
            super.getSprite().setViewport(new Rectangle2D(90,160,80,110));
        }
        if (y<250){ //if the hero is jumping, then the gravity will affect him
            ay=3000;
        }
        if (vy==0 && y==250) { //the hero is running
            index++;
            super.getSprite().setViewport(new Rectangle2D(8 + (index/5)%6 * 83, 0, 78, 100));
        }
    }

}
