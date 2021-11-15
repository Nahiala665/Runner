import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

    public class Window extends Application{

        public void start(Stage primaryStage){
            primaryStage.setTitle("Extrarunner");
            Group root = new Group();
            GameScene scene = new GameScene(root,600,400);
            primaryStage.setScene(scene);
            primaryStage.show();

        }
        public static void main(String[] args) {
            launch(args);

        }
    }
