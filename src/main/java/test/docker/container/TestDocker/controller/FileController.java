package test.docker.container.TestDocker.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("/api/files")
public class FileController {

    private final SimpMessagingTemplate messagingTemplate;

    public FileController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/download-zip")
    public ResponseEntity<StreamingResponseBody> downloadZipFile(@RequestParam String zipname) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "download.zip");

        Path path = Path.of("C:\\applications_personal\\files_test\\zip_downloads\\"+zipname+".zip");

        try {
            long fileSize = Files.size(path);
            headers.setContentLength(fileSize);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(outputStream -> writeToOutputStream(path, outputStream));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    private void writeToOutputStream(Path path, java.io.OutputStream outputStream) {
        try {
            byte[] buffer = new byte[1024];
            long totalBytesRead = 0;
            long fileSize = Files.size(path);

            try (var inputStream = Files.newInputStream(path)) {
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;

                    // Notify UI about the download progress (using WebSocket)
                   // messagingTemplate.convertAndSend("api/v1/topic/download-progress", totalBytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

