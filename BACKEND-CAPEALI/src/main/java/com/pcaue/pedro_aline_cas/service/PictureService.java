package com.pcaue.pedro_aline_cas.service;

import com.pcaue.pedro_aline_cas.model.Picture;
import com.pcaue.pedro_aline_cas.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class PictureService {

    @Value("${file.upload-dir}")
    private String uploadDir; // Diretório onde as imagens serão salvas

    private final PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Picture savePicture(String name, MultipartFile file) throws IOException {
        String fileName = String.valueOf(System.currentTimeMillis());
        Path filePath = Paths.get(uploadDir + File.separator + fileName);

        Files.createDirectories(filePath.getParent());
        file.transferTo(filePath.toFile());

        Picture picture = new Picture();
        picture.setName(name);
        picture.setSrc(filePath.toString());

        return pictureRepository.save(picture);
    }

    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }

    public boolean deletePicture(String id) {
        Optional<Picture> pictureOptional = pictureRepository.findById(id);
        if (pictureOptional.isPresent()) {
            Picture picture = pictureOptional.get();

            // Exclui o arquivo físico
            try {
                Files.deleteIfExists(Paths.get(picture.getSrc()));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            // Remove o registro do banco
            pictureRepository.delete(picture);
            return true;
        }
        return false; // Retorna falso se a imagem não for encontrada
    }
}
