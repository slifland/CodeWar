package CodeWar.engine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane
{
    int row;
    int col;

    boolean hasRobot = false;
    boolean hasIron = false;
    boolean hasSilicon = false;
    Image imageSource;
    ImageView image;
    public Tile(int row, int col, boolean passable)
    {
        this.row = row;
        this.col = col;

        setWidth(Client.CELL_SIZE);
        setHeight(Client.CELL_SIZE);

        if(passable)
        {
            imageSource = ImageSources.ground;
        }
        else
        {
            imageSource = ImageSources.mountains;
        }

        image = new ImageView();
        image.setImage(imageSource);
        image.setFitHeight(Client.CELL_SIZE);
        image.setFitWidth(Client.CELL_SIZE);
        this.getChildren().addAll(new Rectangle(Client.CELL_SIZE, Client.CELL_SIZE, Color.CORNFLOWERBLUE), image);

        relocate(row * Client.CELL_SIZE, col * Client.CELL_SIZE);
    }

    public void updateTile(Image imageSourcc)
    {
        this.imageSource = imageSourcc;
        image.setImage(imageSourcc);
    }
}
