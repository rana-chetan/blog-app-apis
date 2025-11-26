package com.example.blog.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;

    @NotBlank(message = "Category title must not be blank")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10, message = "Category description must be minimum of 10 characters")
    private String categoryDescription;
}
