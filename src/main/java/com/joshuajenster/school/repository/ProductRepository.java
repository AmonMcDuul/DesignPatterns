package com.joshuajenster.school.repository;

import com.joshuajenster.school.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
