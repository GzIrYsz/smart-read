package fr.cyu.smartread.spellchecking.stringmetrics.levenshtein;

import fr.cyu.smartread.spellchecking.stringmetrics.StringMetricsInterface;

/**
 * The computer for the Levenshtein distance between two words.
 *
 * @author Alexandre
 */
public class LevenshteinDistance implements StringMetricsInterface {
    private final short insertionCost;
    private final short deletionCost;
    private final short substitutionCost;

    /**
     * Constructs a new LevenshteinDistance object with the given parameters.
     *
     * @param insertionCost The insertion cost.
     * @param deletionCost The deletion cost.
     * @param substitutionCost The substitution cost.
     */
    public LevenshteinDistance(int insertionCost, int deletionCost, int substitutionCost) {
        this.insertionCost = (short) insertionCost;
        this.deletionCost = (short) deletionCost;
        this.substitutionCost = (short) substitutionCost;
    }

    /**
     * The default constructor for LevenshteinDistance. Substitution cost, deletion cost and insertion cost are all set
     * to 1.
     */
    public LevenshteinDistance() {
        this(1, 1, 1);
    }

    /**
     * Computes the distance between the two given words.
     *
     * @param source The source word.
     * @param target The target word.
     * @return The distance between the two words.
     */
    @Override
    public float computeDistance(String source, String target) {
        if (source.equals(target))
            return 0;

        String formattedSource = formatStrForLevArrayDistanceCalculation(source);
        String formattedTarget = formatStrForLevArrayDistanceCalculation(target);

        short[][] levArray = initLevTab(formattedSource, formattedTarget);
        int xLength = levArray.length;
        int yLength = levArray[0].length;

        for (short i = 1; i < xLength; i++) {
            for (short j = 1; j < yLength; j++) {
                CellLevArray actualCell = new CellLevArray(i, j, levArray[i][j]);
                stepCalculationLevArray(formattedSource, formattedTarget, actualCell, levArray);
            }
        }

        return getComputationResult(levArray);
    }

    private short[][] initLevTab(String formattedSource, String formattedTarget) {
        short[][] levArray = new short[formattedTarget.length()][formattedSource.length()];

        for (short i = 0; i < formattedSource.length(); i++) {
            final int xCordLevArray = 0;

            levArray[xCordLevArray][i] = (short) (i * insertionCost);
        }
        
        for (int j = 0; j < formattedTarget.length(); j++) {
            final int yCordLevArray = 0;

            levArray[j][yCordLevArray] = (short) (j * deletionCost);
        }

        return levArray;
    }

    private void stepCalculationLevArray(String formattedSource, String formattedTarget, CellLevArray actualCell, short[][] levArray) {
        int x = actualCell.getX(); // xCoord of actualCell is equal to Xth letter of the formattedTarget str
        int y = actualCell.getY(); // yCoord of actualCell is equal to Xth letter of the formattedSource str

        if (formattedSource.charAt(y) == formattedTarget.charAt(x)) {
            levArray[x][y] = getActualDiagonalValueFromLevArray(actualCell, levArray);
        } else {
            levArray[x][y] = getLocalMinValueFromLevArray(actualCell, levArray);
        }
    }
    private static String formatStrForLevArrayDistanceCalculation(final String str) {
        return " " + str;
    }

    private short getLocalMinValueFromLevArray(CellLevArray actualCell, short[][] levArray) {
        short x = actualCell.getX();
        short y = actualCell.getY();
        short xMinus1 = (short) (x - 1);
        short yMinus1 = (short) (y - 1);

        CellLevArray cell1 = new CellLevArray(x, yMinus1, levArray[x][yMinus1]); // deletion case
        CellLevArray cell2 = new CellLevArray(xMinus1, yMinus1, levArray[xMinus1][yMinus1]); // substitution case
        CellLevArray cell3 = new CellLevArray(xMinus1, y, levArray[xMinus1][y]); // insertion case

       return (short) Math.min(cell1.getValue() + deletionCost, Math.min(cell2.getValue() + substitutionCost, cell3.getValue() + insertionCost));
    }

    private static short getActualDiagonalValueFromLevArray(CellLevArray actualCell, short[][] levArray) {
        return levArray[actualCell.getX() - 1][actualCell.getY() - 1];
    }

    private static short getComputationResult(short[][] levArray) {
        int xLength = levArray.length;
        int yLength = levArray[0].length;

        return levArray[xLength - 1][yLength - 1];
    }
}