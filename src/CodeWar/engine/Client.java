package CodeWar.engine;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class Client extends Application
{
    public static Runner runner;
    public List<GameWorld> finishedGame;
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

        root.setPrefSize(size * CELL_SIZE + 275, size * CELL_SIZE + 50);
        root.getChildren().addAll(tileGroup);
        tileGroup.relocate(275,0);

        Label codeWarLabel = new Label("CODE WARS");
        codeWarLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 25));
        codeWarLabel.relocate(50,0);
        root.getChildren().add(codeWarLabel);

        Text ironTrackerTeam1 = new Text("Team 1 Iron: " + gameWorld.teamA.getIron());
        Text siliconTrackerTeam1 = new Text("Team 1 Silicon: " + gameWorld.teamA.getSilicon());
        Text ironTrackerTeam2 = new Text("Team 2 Iron: " + gameWorld.teamA.getIron());
        Text siliconTrackerTeam2 = new Text("Team 2 Silicon: " + gameWorld.teamB.getSilicon());
        ironTrackerTeam1.setFont(Font.font("Verdana", 20));
        ironTrackerTeam2.setFont(Font.font("Verdana", 20));
        siliconTrackerTeam1.setFont(Font.font("Verdana", 20));
        siliconTrackerTeam2.setFont(Font.font("Verdana", 20));
        ironTrackerTeam1.relocate(30, 100);
        siliconTrackerTeam1.relocate(30, 120);
        ironTrackerTeam2.relocate(30, 140);
        siliconTrackerTeam2.relocate(30, 160);
        root.getChildren().add(ironTrackerTeam1);
        root.getChildren().add(ironTrackerTeam2);
        root.getChildren().add(siliconTrackerTeam1);
        root.getChildren().add(siliconTrackerTeam2);


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

    public void showTurn(GameWorld gw){
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                MapTile correspondingMapTile = gw.gameWorld[i][j];
                if(!correspondingMapTile.passable){
                    tiles[i][j].imageSource = ImageSources.mountains;
                }
                else if(correspondingMapTile.robotInfoOnTile != null){
                    RobotInfo r = correspondingMapTile.robotInfoOnTile;
                    int team = r.playerOwner;
                    if(team == 1){
                        switch(r.robotType){
                            default -> System.out.println("oops!");
                            case GameConstants.SCOUT -> tiles[i][j].imageSource = ImageSources.scout0;
                            case GameConstants.INFANTRY -> tiles[i][j].imageSource = ImageSources.infantry0;
                            case GameConstants.MINER -> tiles[i][j].imageSource = ImageSources.miner0;
                            case GameConstants.HQ -> tiles[i][j].imageSource = ImageSources.HQ0;
                            //case GameConstants.SUPERBOT -> tiles[i][j].imageSource = ImageSources.scout0;
                        }
                    }
                    else if(team == 2){
                        switch(r.robotType){
                            default -> System.out.println("oops!");
                            case GameConstants.SCOUT -> tiles[i][j].imageSource = ImageSources.scout1;
                            case GameConstants.INFANTRY -> tiles[i][j].imageSource = ImageSources.infantry1;
                            case GameConstants.MINER -> tiles[i][j].imageSource = ImageSources.miner1;
                            case GameConstants.HQ -> tiles[i][j].imageSource = ImageSources.HQ1;
                            //case GameConstants.SUPERBOT -> tiles[i][j].imageSource = ImageSources.scout0;
                        }
                    }
                }
                else{
                    if(correspondingMapTile.numIron > 0){
                        tiles[i][j].imageSource = ImageSources.ironNoMine;
                    }
                    else if(correspondingMapTile.numSilicon > 0){
                        tiles[i][j].imageSource = ImageSources.siliconMine;
                    }
                    else{
                        tiles[i][j].imageSource = ImageSources.ground;
                    }
                }
            }
        }
    }

    public static void main(String[] args)
    {
        launch();
        //Client client = new Client(runner);
        /*while(runner.active()){
            runner.update();
        }*/
    }
}

