package com.test.service.implementations;

import com.test.common.ResponseMessage;
import com.test.dto.response.MenuResponseDto;
import com.test.dto.response.ResponseDto;
import com.test.dto.response.RestaurantResponseDto;
import com.test.entity.Restaurant;
import com.test.repository.RestaurantRepository;
import com.test.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<RestaurantResponseDto> createRestaurant(RestaurantResponseDto dto) {
        RestaurantResponseDto responseDto = null;

        Restaurant newRestaurant = Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phoneNumber(String.valueOf(dto.getPhoneNumber()))
                .build();

        Restaurant saved = restaurantRepository.save(newRestaurant);

        responseDto = RestaurantResponseDto.builder()
                .id(String.valueOf(saved.getId()))
                .name(saved.getName())
                .address(saved.getAddress())
                .phoneNumber(saved.getPhoneNumber())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional
    public ResponseDto<RestaurantResponseDto> getRestaurantById(Long id) {
        RestaurantResponseDto responseDto = null;

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));

        List<MenuResponseDto> menus = restaurant.getMenus().stream()
                .map(menu -> MenuResponseDto.builder()
                        .id((long) menu.getId())
                        .name(menu.getName())
                        .price(Double.parseDouble(menu.getPrice()))
                        .description(menu.getDescription())
                        .build())
                .collect(Collectors.toList());

        responseDto = RestaurantResponseDto.builder()
                .id(String.valueOf(restaurant.getId()))
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional
    public ResponseDto<List<RestaurantResponseDto>> getAllRestarants() {
        List<RestaurantResponseDto> responseDto = null;

        List<Restaurant> restaurants = restaurantRepository.findAll();

        responseDto = restaurants.stream()
                .map(restaurant -> RestaurantResponseDto.builder()
                        .id(String.valueOf(restaurant.getId()))
                        .name(restaurant.getName())
                        .address(restaurant.getAddress())
                        .phoneNumber(restaurant.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    public ResponseDto<RestaurantResponseDto> updateRestarant(Long id, RestaurantResponseDto dto) {
        RestaurantResponseDto responseDto = null;

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));

        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

        responseDto = RestaurantResponseDto.builder()
                .id(String.valueOf(updatedRestaurant.getId()))
                .name(updatedRestaurant.getName())
                .address(updatedRestaurant.getAddress())
                .phoneNumber(updatedRestaurant.getPhoneNumber()

                )
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    public ResponseDto<Void> deleteRestarant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));

        restaurant.getMenus().forEach(restaurant::removeMenu);

        restaurantRepository.delete(restaurant);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<RestaurantResponseDto>> getAllRestaurantByName(String name) {
        List<RestaurantResponseDto> responseDto = null;

        List<Restaurant> restaurants = restaurantRepository.findByName(name);

        responseDto = restaurants.stream()
                .map(restaurant -> RestaurantResponseDto.builder()
                        .id(String.valueOf(restaurant.getId()))
                        .name(restaurant.getName())
                        .address(restaurant.getAddress())
                        .phoneNumber(restaurant.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }
}
