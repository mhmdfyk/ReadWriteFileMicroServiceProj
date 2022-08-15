package com.INetwork.advancedfileoperation.service;

import com.INetwork.advancedfileoperation.repository.ReadWriteFileRepository;
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
    public String removeWord(String txt){
        String fileTxt = readFile();
        return fileRepository.writeFile(fileTxt.replaceAll("(?i)".concat(txt), ""));
    }
    public String removeDuplicate(){
        String fileTxt = readFile();
        StringBuilder txt = new StringBuilder();
        String[] words = fileTxt.split(" ");
        txt.append(words[0].concat(" "));
        for (int i = 1; i < words.length; ++i){
            if(!words[i].toLowerCase(Locale.ROOT).equals(words[i - 1].toLowerCase(Locale.ROOT))){
                txt.append(words[i].concat(" "));
            }
        }
        return fileRepository.writeFile(txt.toString());
    }

}
