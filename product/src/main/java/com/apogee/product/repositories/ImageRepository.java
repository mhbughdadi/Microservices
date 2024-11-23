package com.apogee.product.repositories;

import com.apogee.product.entities.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity,Long> {

    @Override
    List<ImageEntity> findAll();

    @Override
    <S extends ImageEntity> S save(S entity);
}
