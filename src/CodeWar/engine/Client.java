package CodeWar.engine;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class Client extends Application
{
    public static Runner runner;
    public static int CELL_SIZE = 25;
    public Tile[][] tiles;
    public int size;
    private Group tileGroup = new Group();

    public Parent createContent()
    {
        Pane root = new Pane();

        runner = new Runner();
        GameWorld gameWorld = runner.world;
        size = gameWorld.sizeX;
        tiles = new Tile[size][size];

        root.setPrefSize(size * CELL_SIZE, size * CELL_SIZE);
        root.getChildren().addAll(tileGroup);

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                tiles[i][j] = new Tile(i, j, gameWorld.gameWorld[i][j].passable);
                tileGroup.getChildren().add(tiles[i][j]);
            }
        }
        return root;
    }
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(createContent());
        stage.setTitle("CodeWar");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
        Runner runner = new Runner();
        //Client client = new Client(runner);
        /*while(runner.active()){
            runner.update();
        }*/
    }
}

