import java.awt.Color;

public class Wall extends Cell {

    public Wall(char col, int row, int x, int y) {
        super(col, row, x, y);
        this.description = "wall";
        colour = Color.DARK_GRAY;
    }

    @Override
    public int crossingTime() {
        return 1000;
    }
}
