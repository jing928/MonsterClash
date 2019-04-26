package grp.oozmakappa.monsterclash.utils;

import java.io.File;
import java.util.*;

/**
 * @author Chenglong Ma
 */
public class FileUtil {

    public static File getResource(String filename) {
        String path = Objects.requireNonNull(FileUtil.class.getClassLoader().getResource(filename)).getPath();
        return new File(path);
    }

    /**
     * Returns the file list in the specific dir.
     * The format of dir is relative path in 'resources/'
     *
     * @param dir the folder path under 'resources/'
     * @return the paths in the dir.
     */
    public static List<String> getSubFiles(String dir) {
        File file = getResource(dir);
        List<String> res = new ArrayList<>();
        File[] subFiles = file.listFiles();
        Arrays.sort(subFiles);
        for (File subFile : Objects.requireNonNull(subFiles)) {
            if (subFile.isFile()) {
                res.add(subFile.getAbsolutePath());
            }
        }
        return Collections.unmodifiableList(res);
    }
}
