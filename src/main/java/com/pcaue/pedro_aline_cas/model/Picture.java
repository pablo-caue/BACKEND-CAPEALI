package com.pcaue.pedro_aline_cas.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "pictures") // Define a coleção no MongoDB
public class Picture {

    // Getters e Setters
    @Id
    private String id; // ID como String para MongoDB

    private String name; // Nome da imagem
    private String src;  // Caminho da imagem no servidor

}
