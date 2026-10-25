package com.ozodbek.classifieds.category.attribute;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryAttributeRepository extends JpaRepository<CategoryAttribute, Long> {

    List<CategoryAttribute> findAllByCategory_Id(Long categoryId);

}
