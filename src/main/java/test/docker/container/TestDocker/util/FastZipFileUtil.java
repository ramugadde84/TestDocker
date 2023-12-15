package test.docker.container.TestDocker.util;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class FastZipFileUtil {

    public static void main(String[] args) {
        String sourceDirectory = "C:\\applications_personal\\files_test";
        String zipFilePath = "C:\\applications_personal\\files_test\\random.zip";

        try {
            long startTime = System.currentTimeMillis();
            zipDirectory(sourceDirectory, zipFilePath);
            long endTime = System.currentTimeMillis();

            System.out.println("Total time to process:"+ TimeUnit.MILLISECONDS
                    .toSeconds(endTime-startTime) + " seconds");
            System.out.println("Files zipped successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void zipDirectory(String sourceDirectory, String zipFilePath) throws IOException {
        Path sourcePath = Paths.get(sourceDirectory);

        try (OutputStream fos = new FileOutputStream(zipFilePath);
             ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(fos)) {

            Files.walk(sourcePath)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        try {
                            String entryName = sourcePath.relativize(path).toString();
                            ZipArchiveEntry entry = new ZipArchiveEntry(path.toFile(), entryName);
                            zipOut.putArchiveEntry(entry);
                            IOUtils.copy(new FileInputStream(path.toFile()), zipOut);
                            zipOut.closeArchiveEntry();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }
}
