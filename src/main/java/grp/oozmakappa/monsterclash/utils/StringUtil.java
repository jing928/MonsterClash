package grp.oozmakappa.monsterclash.utils;

/**
 * @author Chenglong Ma
 */
public class StringUtil {
    /**
     * Return capitalized format {@link String}
     *
     * @param original the raw format string
     * @return
     */
    public static String capitalizeFirstLetter(String original) {
        if (original == null || original.isEmpty()) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase();
    }

    public static String[] capitalizeFirstLetter(String... originals) {
        String[] res = new String[originals.length];
        for (int i = 0; i < originals.length; i++) {
            res[i] = capitalizeFirstLetter(originals[i]);
        }
        return res;
    }
}
