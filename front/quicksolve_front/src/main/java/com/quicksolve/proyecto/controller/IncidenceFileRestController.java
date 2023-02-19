package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.FileDTO;
import com.quicksolve.proyecto.service.IncidenceFileService;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class IncidenceFileRestController {

    @Autowired
    private IncidenceFileService incidenceFileService;

    @PostMapping("/rest/imagenes/{incidenceId}")
    public ResponseEntity<Set<FileDTO>> getFilesByIncidenceId(@PathVariable Long incidenceId){
        if (incidenceId == null){
            return ResponseEntity.badRequest().build();
        }
        Set<FileDTO> files = incidenceFileService.findAllByIncidenceId(incidenceId);

        if (files == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(files);
    }

    @DeleteMapping("/rest/imagenes/eliminar")
    public ResponseEntity<HttpResponse> deleteFileByIncidenceId(@RequestParam String src, @RequestParam Long incidenceId){
        incidenceFileService.deleteBySrc(src, incidenceId);
        return ResponseEntity.ok().build();
    }
}
