package com.INetwork.readwritefile.controller;

import com.INetwork.readwritefile.repository.ReadWriteFileRepository;
import com.INetwork.readwritefile.service.ReadWriteFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class ReadWriteController {
    private final ReadWriteFileService readWriteFileService;
    @GetMapping("/read")
    public ResponseEntity<String> readFile(){
        return ResponseEntity.ok(readWriteFileService.readFile());
    }
    @GetMapping("/read/lower")
    public ResponseEntity<String> readLowerFile(){
        return ResponseEntity.ok(readWriteFileService.readAsLowerFile());
    }
    @GetMapping("/read/upper")
    public ResponseEntity<String> readUpperFile(){
        return ResponseEntity.ok(readWriteFileService.readAsUpperFile());
    }
    @PostMapping("/write")
    public ResponseEntity<String> writeFile(@RequestParam("text") String text){
        return ResponseEntity.ok(readWriteFileService.writeFile(text));
    }
}
