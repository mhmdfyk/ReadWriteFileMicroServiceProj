package com.INetwork.advancedfileoperation.repository;

import com.INetwork.advancedfileoperation.service.Config;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Repository
@RequiredArgsConstructor
public class ReadWriteFileRepository {

    private final Config config;
    @SneakyThrows
    public String readFile() {
        File file = new File(config.getFilePath());
        if(file.exists()){
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
                return readFromInputStream(inputStream);
            }
            finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "";
    }
    @SneakyThrows
    public String writeFile(String txt)  {
        File file = new File(config.getFilePath());
        if(!file.exists()){
            if(!file.createNewFile()){
                return "";
            }
        }
        Path path = file.toPath();
        try {
            Files.writeString(path, txt, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return txt;
    }

    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
