import java.awt.Color;
import java.util.Random;

public class PalmTree extends Cell {
    private static Random rand = new Random();

    public PalmTree(char col, int row, int x, int y) {
        super(col, row, x, y);
        this.description = "palm tree";
        float green = rand.nextFloat();
        // avoid shades that are almost black
        float shade = green * 0.8f + 0.2f;
        colour = new Color(0.0f, shade, 0.0f);
    }

    @Override
    public int crossingTime() {
        return 2;
    }
}
