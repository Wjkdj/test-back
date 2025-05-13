package com.test.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantResponseDto {
    private String id;
    private String name;
    private String address;
    private String phoneNumber;
}
