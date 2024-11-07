package com.pcaue.pedro_aline_cas.controller;

import com.pcaue.pedro_aline_cas.model.Picture;
import com.pcaue.pedro_aline_cas.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pictures")
public class PictureController {

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        try {
            Picture picture = pictureService.savePicture(name, file);
            return ResponseEntity.ok(picture);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erro ao salvar a imagem.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Picture>> findAll() {
        List<Picture> pictures = pictureService.getAllPictures();
        return ResponseEntity.ok(pictures);
    }

    // Endpoint para excluir uma imagem por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        boolean deleted = pictureService.deletePicture(id);
        if (deleted) {
            return ResponseEntity.ok("Imagem removida com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Imagem não encontrada.");
        }
    }
}
