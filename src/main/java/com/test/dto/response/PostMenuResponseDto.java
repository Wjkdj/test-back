package com.test.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostMenuResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private RestaurantResponseDto restaurant;
}
