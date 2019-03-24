package grp.oozmakappa.monsterclash.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Chenglong Ma
 */
public class FileUtil {

    /**
     * Returns the file list in the specific dir.
     * The format of dir is relative path in 'resources/'
     *
     * @param dir the folder path under 'resources/'
     * @return the paths in the dir.
     */
    public static List<String> getSubFiles(String dir) {
        String path = Objects.requireNonNull(FileUtil.class.getClassLoader().getResource(dir)).getPath();
        File file = new File(path);
        List<String> res = new ArrayList<>();
        File[] subFiles = file.listFiles();
        for (File subFile : Objects.requireNonNull(subFiles)) {
            if (subFile.isFile()) {
                res.add(subFile.getAbsolutePath());
            }
        }
        return Collections.unmodifiableList(res);
    }
}
