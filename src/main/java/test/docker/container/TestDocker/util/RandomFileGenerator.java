package test.docker.container.TestDocker.util;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RandomFileGenerator {

    public static void main(String[] args) {
        String directoryPath = "C:\\applications_personal\\files_test";

        List<String> fileNames = new ArrayList<>();
        try {
            for (int i = 1; i <= 80; i++) {
                String filename = "random_file_" + i + ".txt";
                fileNames.add(filename);
                createRandomFile(directoryPath,filename , 100 * 100024); // 100 KB
            }

            long startTime = System.currentTimeMillis();
            processzipFile(fileNames,directoryPath);
            long endTime = System.currentTimeMillis();

            System.out.println("Total time to process:"+ TimeUnit.MILLISECONDS
                    .toSeconds(endTime-startTime) + " seconds");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processzipFile(List<String> fileNames, String directoryPath) throws IOException {
        // Create a File object for the directory
        File directory = new File("C:\\applications_personal\\files_test\\zip_downloads");

        // Ensure that the parent directories exist
        if (!directory.exists()) {
            directory.mkdirs(); // Create parent directories if they don't exist
        }

        // Specify the file path
        File file = new File(directory, "output.zip");
        try(OutputStream outputStream = new FileOutputStream(file);
        ZipArchiveOutputStream zipArchiveOutputStream =
                new ZipArchiveOutputStream(outputStream)) {
            createZipFile(fileNames,zipArchiveOutputStream,directoryPath);
        }
    }

    private static void createZipFile(List<String> fileNames, ZipArchiveOutputStream zipArchiveOutputStream, String directoryPath) {
      fileNames.stream()
              .forEach(documentFile -> {
                  try(FileInputStream f = new
                          FileInputStream(directoryPath + "/" + documentFile)) {
                      zipArchiveOutputStream.putArchiveEntry(new ZipArchiveEntry(documentFile));
                      IOUtils.copy(f,zipArchiveOutputStream);
                      zipArchiveOutputStream.closeArchiveEntry();

                  } catch (FileNotFoundException e) {
                      throw new RuntimeException(e);
                  } catch (IOException e) {
                      throw new RuntimeException(e);
                  } ;

              });
    }

    private static void createRandomFile(String directoryPath, String fileName, long fileSize) throws IOException {
        Path filePath = Path.of(directoryPath, fileName);
        Random random = new Random();

        try (FileChannel fileChannel = FileChannel.open(filePath, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            for (long writtenBytes = 0; writtenBytes < fileSize; writtenBytes += buffer.capacity()) {
                random.nextBytes(buffer.array());
                buffer.rewind();
                fileChannel.write(buffer);
            }
        }
    }
}

