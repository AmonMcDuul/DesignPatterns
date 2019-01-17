package com.joshuajenster.school.repository;

import com.joshuajenster.school.domain.ProductCatalog;
import org.springframework.data.repository.CrudRepository;

public interface ProductCatalogRepository extends CrudRepository<ProductCatalog, Long> {
}
