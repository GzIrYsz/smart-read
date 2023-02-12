package fr.cyu.smartread.spellchecking.stringmetrics;

public class LevenshteinDistance implements StringMetricsInterface {
    private final int substitutionCost;
    private final int deletionCost;
    private final int insertionCost;

    public LevenshteinDistance(int insertionCost, int deletionCost, int substitutionCost) {
        this.insertionCost = insertionCost;
        this.deletionCost = deletionCost;
        this.substitutionCost = substitutionCost;
    }

    public LevenshteinDistance() {
        this(1, 1, 1);
    }


    @Override
    public float computeDistance(String source, String target) {
        // TODO faire une version itérative de cette fonction
        final int strRefLength = source.length();
        final int strTestLength = target.length();

        if (Math.min(strRefLength, strTestLength) == 0) {
            if (strRefLength >= strTestLength)
                return strRefLength * deletionCost;
            return strTestLength * insertionCost;
        }

        if (source.charAt(0) == target.charAt(0))
            return computeDistance(removeFirstLetterStr(source), removeFirstLetterStr(target));

        float insertionValue = insertionCost  + computeDistance(removeFirstLetterStr(source), target);
        float deletionValue = deletionCost + computeDistance(source, removeFirstLetterStr(target));
        float substitutionValue = substitutionCost  + computeDistance(removeFirstLetterStr(source), removeFirstLetterStr(target));

        return min(insertionValue, deletionValue, substitutionValue);
    }

    private float min(float a, float b, float c) {
        return Math.min(a, Math.min(b, c));
    }

    private String removeFirstLetterStr(String str) {
        return str.substring(1);
    }
}