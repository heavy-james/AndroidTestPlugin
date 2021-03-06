package heavy.test.plugin.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class FileUtil {

    public static List<File> getAllFiles(File file) {
        List<File> files = new LinkedList<File>();
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File tempfile : file.listFiles()) {
                    files.addAll(getAllFiles(tempfile));
                }
            } else {
                files.add(file);
            }
        }
        return files;
    }

    public static List<File> getAllJARS(File file) {
        List<File> files = getAllFiles(file);
        List<File> toRemove = new ArrayList<>();
        for (File temp : files) {
            if (!temp.isFile() || !temp.exists() || !temp.getName().endsWith(".jar")) {
                toRemove.add(temp);
            }
        }
        files.removeAll(toRemove);
        return files;
    }

    public static List<File> getAllJARS(String[] paths) {
        List<File> result = new LinkedList<File>();
        for (String path : paths) {
            result.addAll(getAllJARS(new File(path)));
        }
        return result;
    }


    public static void writeFile(String fileName, String content, boolean append) {
        if (content != null) {
            writeFile(fileName, content.toCharArray(), append);
        }
    }

    public static File createFile(String fileName) {
        File result = new File(fileName);
        if (result.exists() && result.canWrite()) {
            return result;
        }
        File path = new File(result.getParent());
        if (!path.exists()) {
            path.mkdirs();
        }
        if (!result.exists()) {
            try {
                result.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void writeFile(String fileName, char[] datas, boolean append) {
        writeFile(createFile(fileName), datas, append);
    }

    public static void writeFile(File file, char[] datas, boolean append) {
        if (!file.exists() || !file.isFile()) {
            return;
        }
        FileWriter filerWriter = null;
        BufferedWriter bufWriter = null;
        try {
            filerWriter = new FileWriter(file, append);
            bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(datas);
            bufWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufWriter != null) {
                try {
                    bufWriter.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (filerWriter != null) {
                try {
                    filerWriter.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

}
