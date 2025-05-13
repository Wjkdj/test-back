package com.test.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostMenuRequestDto {
    private String name;
    private String description;
    private Double price;
    private Long restaurant_id;
}
