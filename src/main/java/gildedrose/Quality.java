package gildedrose;

public class Quality {
    
    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 50;
    
    private int value;

    public Quality(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void decrease() {
        if (value > MIN_QUALITY) {
            value--;
        }
    }

    public void increase() {
        if (value < MAX_QUALITY) {
            value++;
        }
    }

    public void reset() {
        value = MIN_QUALITY;
    }

    public boolean isPositive() {
        return value > MIN_QUALITY;
    }
}
