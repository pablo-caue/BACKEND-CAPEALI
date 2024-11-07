package com.pcaue.pedro_aline_cas.repository;

import com.pcaue.pedro_aline_cas.model.Picture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends MongoRepository<Picture, String> {
}
