import java.awt.Color;
import java.util.Random;

public class Oasis extends Cell {
    private static Random rand = new Random();

    public Oasis(char col, int row, int x, int y) {
        super(col, row, x, y);
        this.description = "oasis";
        float blue = rand.nextFloat();
        // avoid shades that are almost black
        float shade = blue * 0.8f + 0.2f;
        colour = new Color(0.0f, 0.0f, shade);
    }

    @Override
    public int crossingTime() {
        return 8;
    }
}
