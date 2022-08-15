package com.INetwork.readwritefile.service;

import com.INetwork.readwritefile.repository.ReadWriteFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ReadWriteFileService {

    private final ReadWriteFileRepository fileRepository;


    public String readFile(){
        return fileRepository.readFile();
    }
    public String readAsUpperFile(){
        return fileRepository.readFile().toUpperCase(Locale.ROOT);
    }
    public String readAsLowerFile(){
        return fileRepository.readFile().toLowerCase(Locale.ROOT);
    }
    public String writeFile(String txt){
        return fileRepository.writeFile(txt);
    }

}
