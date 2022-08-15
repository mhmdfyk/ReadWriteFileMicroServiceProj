package com.INetwork.advancedfileoperation.controller;

import com.INetwork.advancedfileoperation.service.ReadWriteFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class AdvancedOperationController {
    private final ReadWriteFileService readWriteFileService;
    @PostMapping("/remove")
    public ResponseEntity<String> writeFile(@RequestParam("word") String text){
        return ResponseEntity.ok(readWriteFileService.removeWord(text));
    }
    @PostMapping("/remove/duplicate")
    public ResponseEntity<String> removeConsDuplicate(){
        return ResponseEntity.ok(readWriteFileService.removeDuplicate());
    }
}
