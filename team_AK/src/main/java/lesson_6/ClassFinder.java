package lesson_6;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClassFinder {

    public List<Class<?>> findAllClassesInPackage(Class<?> startClass) {
        final int ExcludeExtensionAndPoint = 6;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        String classPath = startClass.getPackage().getName();
        URL fullPath = classLoader.getResource(classPath);
        assert fullPath != null;
        File startDir = new File(fullPath.getPath());

        List<File> files = (List<File>) FileUtils.listFiles(startDir, new String[]{"class"}, true);

        List<String> prepareClasses = files.stream()
                .map(file -> prepareClassPath(file.getParent(), classPath) + "." +
                        file.getName().substring(0, file.getName().length() - ExcludeExtensionAndPoint))
                .collect(Collectors.toList());

        List<Class<?>> classes = new ArrayList<>();
        prepareClasses.forEach(file -> {
            try {
                classes.add(Class.forName(file));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return classes;
    }

    private String prepareClassPath(String fullPath, String scannedPackage) {
        final char PKG_SEPARATOR = '.';
        final char DIR_SEPARATOR = 92;

        int getStartPositionInCurrentPackage = fullPath.indexOf(scannedPackage);

        return fullPath.replace(DIR_SEPARATOR, PKG_SEPARATOR)
                .substring(getStartPositionInCurrentPackage,fullPath.length());
    }
}