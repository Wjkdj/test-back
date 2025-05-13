package com.test.service;

import com.test.dto.response.ResponseDto;
import com.test.dto.response.RestaurantResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
    ResponseDto<RestaurantResponseDto> createRestaurant(@Valid RestaurantResponseDto dto);

    ResponseDto<RestaurantResponseDto> getRestaurantById(Long id);

    ResponseDto<List<RestaurantResponseDto>> getAllRestarants();

    ResponseDto<RestaurantResponseDto> updateRestarant(@Valid Long id, @Valid RestaurantResponseDto dto);

    ResponseDto<Void> deleteRestarant(@Valid Long id);

    ResponseDto<List<RestaurantResponseDto>> getAllRestaurantByName(String name);
}
