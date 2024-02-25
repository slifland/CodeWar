package CodeWar.engine;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    public Text ironTrackerTeam1;
    public Text siliconTrackerTeam1;
    public Text ironTrackerTeam2;
    public Text siliconTrackerTeam2;

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

        ironTrackerTeam1 = new Text("Team 1 Iron: " + gameWorld.teamA.getIron());
        siliconTrackerTeam1 = new Text("Team 1 Silicon: " + gameWorld.teamA.getSilicon());
        ironTrackerTeam2 = new Text("Team 2 Iron: " + gameWorld.teamB.getIron());
        siliconTrackerTeam2 = new Text("Team 2 Silicon: " + gameWorld.teamB.getSilicon());
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

        Label winnerLabel = new Label("test");
        winnerLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 25));
        winnerLabel.relocate(15,300);
        root.getChildren().add(winnerLabel);

        Button playButton = new Button("Play");
        playButton.relocate(120,600);
        root.getChildren().add(playButton);

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                tiles[i][j] = new Tile(i, j, gameWorld.gameWorld[i][j].passable);
                tileGroup.getChildren().add(tiles[i][j]);
            }
        }

        Text turnText = new Text("Turn: 0");
        turnText.setFont(Font.font("Verdana", 20));
        turnText.relocate(100, 760);
        root.getChildren().add(turnText);

        while(runner.active())
        {
            runner.update();
        }
        if(runner.champion != 0){
            winnerLabel.setText("Winner is Player " + runner.champion);
        }
        else{
            if(gameWorld.teamA.getIron() > gameWorld.teamB.getIron())  winnerLabel.setText("Winner is Player A \n(on tiebreakers, \nmore Iron)");
            else if(gameWorld.teamB.getSilicon() > gameWorld.teamA.getIron()) winnerLabel.setText("Winner is Player B \n(on tiebreakers, \nmore Silicon)");
            else if(gameWorld.teamA.getIron() > gameWorld.teamB.getSilicon())   winnerLabel.setText("Winner is Player A \n(on tiebreakers, \nmore Iron)");
            else if(gameWorld.teamB.getSilicon() > gameWorld.teamA.getSilicon()) winnerLabel.setText("Winner is Player B \n(on tiebreakers, \nmore Silicon)");
            else System.out.println("Tie!");
        }

        finishedGame = runner.pastTurns;

        Slider turnSlider = new Slider();
        turnSlider.setMin(0);
        turnSlider.setMax(finishedGame.size() - 1);
        turnSlider.setMinWidth(750);
        turnSlider.setBlockIncrement(1);

        root.getChildren().add(turnSlider);
        turnSlider.relocate(270,765);

        turnSlider.valueProperty().addListener(
                new ChangeListener<Number>() {

                    public void changed(ObservableValue<? extends Number >
                                                observable, Number oldValue, Number newValue)
                    {
                        turnText.setText("Turn: " + (newValue.intValue() + 1) + "/" + (finishedGame.size()));
                        showTurn(runner.pastTurns.get(newValue.intValue()));
                    }
                });

        playButton.setOnAction(actionEvent ->
        {
            int turnCount = 0;
            while (turnCount < finishedGame.size())
            {
                showTurn(finishedGame.get(turnCount));
                pauseExecution(250);
            }
        });

        showTurn(finishedGame.get(0));
        return root;
    }

    private void pauseExecution(int millis)
    {
        long startTime = System.currentTimeMillis();
        long currentTime = startTime;
        do
        {
           currentTime =  System.currentTimeMillis();
        }while(currentTime < startTime + millis);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(createContent());
        stage.setTitle("CodeWar");
        stage.setScene(scene);
        stage.show();
    }

    public void showTurn(GameWorld gw){
        ironTrackerTeam1.setText("Team 1 Iron: " + gw.teamA.getIron());
        siliconTrackerTeam1.setText("Team 1 Silicon: " + gw.teamA.getSilicon());
        ironTrackerTeam2.setText("Team 2 Iron: " + gw.teamB.getIron());
        siliconTrackerTeam2.setText("Team 2 Silicon: " + gw.teamB.getSilicon());
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                MapTile correspondingMapTile = gw.gameWorld[i][j];
                if(correspondingMapTile.robotInfoOnTile.playerOwner != -1){
                    RobotInfo r = correspondingMapTile.robotInfoOnTile;
                    int team = r.getPlayerOwner();
                    if(team == 1){
                        switch(r.robotType){
                            default -> System.out.println("oops!");
                            case GameConstants.SCOUT -> tiles[i][j].updateTile(ImageSources.scout0);
                            case GameConstants.INFANTRY -> tiles[i][j].updateTile(ImageSources.infantry0);
                            case GameConstants.MINER -> tiles[i][j].updateTile( ImageSources.miner0);
                            case GameConstants.HQ -> tiles[i][j].updateTile(ImageSources.HQ0);
                            case GameConstants.SUPERBOT -> tiles[i][j].updateTile(ImageSources.superBot0);
                            case GameConstants.CITADEL -> tiles[i][j].updateTile(ImageSources.citadel0);
                        }
                    }
                    else if(team == 2){
                        switch(r.robotType){
                            default -> System.out.println("oops!");
                            case GameConstants.SCOUT -> tiles[i][j].updateTile(ImageSources.scout1);
                            case GameConstants.INFANTRY -> tiles[i][j].updateTile( ImageSources.infantry1);
                            case GameConstants.MINER -> tiles[i][j].updateTile(ImageSources.miner1);
                            case GameConstants.HQ -> tiles[i][j].updateTile(ImageSources.HQ1);
                            case GameConstants.SUPERBOT -> tiles[i][j].updateTile(ImageSources.superBot1);
                            case GameConstants.CITADEL -> tiles[i][j].updateTile(ImageSources.citadel1);
                        }
                    }
                }
                else{
                    if(correspondingMapTile.numIron > 0){
                        tiles[i][j].updateTile(ImageSources.ironNoMine);
                    }
                    else if(correspondingMapTile.numSilicon > 0){
                        tiles[i][j].updateTile(ImageSources.siliconMine);
                    }
                    else if(correspondingMapTile.passable){
                        tiles[i][j].updateTile(ImageSources.ground);
                    }else
                    {
                        tiles[i][j].updateTile(ImageSources.mountains);
                    }
                }
            }
        }
    }

    public static void main(String[] args)
    {
        launch();
    }
}

