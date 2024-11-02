package com.apogee.product.repositories;

import com.apogee.product.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Long,ProductEntity> {

}
