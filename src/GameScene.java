import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameScene extends Scene{
    private Button btn;
    private Camera camera;
    private ImageView Sprite;
    private StaticThing planetLeft;
    private StaticThing planetRight;
    private Hero hero;
    private Foe foe;
    private StaticThing lives;
    private Integer numberOfLives;
    private boolean isInvincible;
    private StaticThing background;
    private StaticThing start;

    private AnimationTimer timer;

    public void render(){
        double offset = camera.getX()%712;
        hero.getSprite().setX(hero.getX()-camera.getX());
        hero.getSprite().setY(hero.getY()-camera.getY());
        foe.getSprite().setX(foe.getX()-camera.getX());
        foe.getSprite().setY((foe.getY()-camera.getY()));
        planetLeft.getSprite().setX(0-offset);
        planetRight.getSprite().setX(712-offset);
    }


    public GameScene(Group root, double length, double width){
        super(root,length,width);

        //game background
        planetLeft=new StaticThing(0,0,"Planet.jpg",0,0,712,401);
        root.getChildren().add(planetLeft.getSprite());
        planetRight=new StaticThing(0,0,"Planet.jpg",0,0,712,401);
        root.getChildren().add(planetRight.getSprite());

        //Hero & Foe
        hero=new Hero();
        root.getChildren().add(hero.getSprite());
        foe=new Foe();
        root.getChildren().add(foe.getSprite());

        //lives
        lives=new StaticThing(20,20,"Lives.png",31,19,122,34);
        root.getChildren().add(lives.getSprite());
        numberOfLives=3;

        //starting background
        start=new StaticThing(0,0,"black.png",0,0,600,400);
        root.getChildren().add(start.getSprite());

        //Button
        Button btn = new Button("Ready Player 1");
        btn.setLayoutX(220);
        btn.setLayoutY(150);
        btn.setPrefSize(150, 50);
        root.getChildren().add(btn);

        //camera
        camera=new Camera(0,0,hero);

        //TIMER
        timer = new AnimationTimer() {
            private long lastTime;
            @Override
            public void handle(long l) {
                if (l-lastTime>0.016*1_000_000_000) {  //fixed update at 16ms (60Hz)
                    hero.update();
                    foe.update();
                    lastTime = l;

                    //the foe is displaced on the x-axis when it is outside the screen
                    if (foe.getX() < hero.getX() - 800) {
                        foe.addX(1500);
                    }
                    //the hero stays in that loop while going through an enemy
                    if (!isInvincible && hero.getHitBox().intersects(foe.getHitBox())) {
                            numberOfLives = numberOfLives - 1;
                            isInvincible = true;
                            foe.addY(2000); //the foe is removed
                    }
                    //when not touching a foe, the hero loses its invincibility
                    else if (isInvincible && !hero.getHitBox().intersects(foe.getHitBox())){
                            isInvincible=false;
                    }

                    //game over screen
                    if (numberOfLives==0){
                        background=new StaticThing(0,0,"GameOver.png",50,0,712,401);
                        root.getChildren().add(background.getSprite());
                        timer.stop();
                    }

                    //lives display
                    lives.getSprite().setViewport(new Rectangle2D(31, 19+(3-numberOfLives)*42, 122, 36));

                }
                camera.update();
                render();
            }
        };

        //start button
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                timer.start();
                root.getChildren().remove(btn);
                root.getChildren().remove(start.getSprite());
            }
        };

        // when button is pressed
        btn.setOnAction(event);


        //jumping button
        this.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent keyEvent) {
                if(keyEvent.getText().equals(" ")){
                    hero.jump();
                }
            }
        });

    }


}
