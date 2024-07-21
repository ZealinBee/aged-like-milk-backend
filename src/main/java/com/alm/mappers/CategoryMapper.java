package com.alm.mappers;

import com.alm.dtos.categories.CreateCategoryDTO;
import com.alm.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CategoryMapper {
    Category createCategoryToCategory(CreateCategoryDTO categoryDTO);
}
