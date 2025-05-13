package com.test.controller;

import com.test.dto.request.PostMenuRequestDto;
import com.test.dto.response.MenuResponseDto;
import com.test.dto.response.ResponseDto;
import com.test.entity.Menu;
import com.test.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<ResponseDto<MenuResponseDto>> createMenu(
            @PathVariable Long restaurant_id,
            @Valid @RequestBody PostMenuRequestDto dto
            ) {
        ResponseDto<MenuResponseDto> response = menuService.craeteMenu(restaurant_id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{menu_id}")
    public ResponseEntity<ResponseDto<MenuResponseDto>> updateMenu(
            @PathVariable Long restaurant_id,
            @PathVariable Long menu_id,
            @Valid @RequestBody PostMenuRequestDto dto
    ){
        ResponseDto<MenuResponseDto> response = menuService.updateMenu(restaurant_id, menu_id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{menu_id}")
    public ResponseEntity<ResponseDto<Void>> deleteMenu(
            @PathVariable Long restaurant_id,
            @PathVariable Long menu_id
    ){
        ResponseDto<Void> response = menuService.deleteMenu(restaurant_id, menu_id);

        return ResponseEntity.noContent().build();
    }
}
