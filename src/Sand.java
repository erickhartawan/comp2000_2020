import java.awt.Color;
import java.util.Random;

public class Sand extends Cell {
    private static Random rand = new Random();
    private float yellow;

    public Sand(char col, int row, int x, int y) {
        super(col, row, x, y);
        this.description = "sand";
        // Yellow = Red + Green
        yellow = rand.nextFloat();
        // avoid shades that are almost black
        float shade = yellow * 0.8f + 0.2f;
        colour = new Color(shade, shade, 0.0f);
    }

    @Override
    public int crossingTime() {
        // The "yellowest" 20% is quicksand
        if(yellow >= 0.8f) {
            return 15;
        }
        else {
            return 5;
        }
    }
}
