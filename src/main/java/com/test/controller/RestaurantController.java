package com.test.controller;

import com.test.dto.response.ResponseDto;
import com.test.dto.response.RestaurantResponseDto;
import com.test.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> createRestaurant(@Valid @RequestBody RestaurantResponseDto dto){
        ResponseDto<RestaurantResponseDto> restaurnat = restaurantService.createRestaurant(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurnat);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> getRestaurantById(@PathVariable Long id){
        ResponseDto<RestaurantResponseDto> restaurnat = restaurantService.getRestaurantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurnat);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<RestaurantResponseDto>>> getAllRestaurant(){
        ResponseDto<List<RestaurantResponseDto>> restaurants = restaurantService.getAllRestarants();
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }

    @PutMapping
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> updateRestaurant(
            @Valid Long id,
            @Valid @RequestBody RestaurantResponseDto dto
    ){
        ResponseDto<RestaurantResponseDto> response = restaurantService.updateRestarant(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto<Void>> deleteRestaurant(@Valid Long id){
        ResponseDto<Void> response = restaurantService.deleteRestarant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseDto<List<RestaurantResponseDto>>> getAllRestaurantByName(@PathVariable String name){
        ResponseDto<List<RestaurantResponseDto>> response = restaurantService.getAllRestaurantByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
