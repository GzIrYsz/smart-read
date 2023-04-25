package fr.cyu.smartread.spellchecking.stringmetrics.levenshtein;

/**
 * //TODO
 *
 * @author Alexandre
 */
public class CellLevArray {
   private final short x;
    private final short y;
    private final short value;

    public CellLevArray(short x, short y, short value) throws IllegalArgumentException {
        this.x = x;
        this.y = y;
        this.value = value;

        if (areYorXorValueNegative())
            throw new IllegalArgumentException("Couple (x,y) cannot be negative");
    }

    private boolean areYorXorValueNegative() {
        return this.x < 0 || this.y < 0 || this.value < 0;
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }

    public short getValue() {
        return value;
    }
}
