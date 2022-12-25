package com.example.lesson_1_task_2.service;

import com.example.lesson_1_task_2.entity.Category;
import com.example.lesson_1_task_2.payload.ApiResponse;
import com.example.lesson_1_task_2.payload.CategoryDto;
import com.example.lesson_1_task_2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse addCategory(CategoryDto categoryDto) {

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepository.save(category);
        return new ApiResponse("Category added", true);
    }

    public List<Category> getCategories() {

        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    public Category getCategoryById(Integer id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    public ApiResponse editCategory(Integer id, CategoryDto categoryDto) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return new ApiResponse("Category not found", false);
        }
        Category editingCategory = optionalCategory.get();
        editingCategory.setName(categoryDto.getName());
        editingCategory.setDescription(categoryDto.getDescription());
        categoryRepository.save(editingCategory);
        return new ApiResponse("Category edited", true);
    }

    public ApiResponse deleteCategory(Integer id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return new ApiResponse("Category not found", false);
        }
        categoryRepository.deleteById(id);
        return new ApiResponse("Category deleted", true);
    }
}
