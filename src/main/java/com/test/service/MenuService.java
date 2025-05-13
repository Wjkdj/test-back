package com.test.service;

import com.test.dto.request.PostMenuRequestDto;
import com.test.dto.response.MenuResponseDto;
import com.test.dto.response.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface MenuService {
    ResponseDto<MenuResponseDto> craeteMenu(Long restaurantId, @Valid PostMenuRequestDto dto);

    ResponseDto<MenuResponseDto> updateMenu(Long restaurant_id, Long menuId, @Valid PostMenuRequestDto dto);

    ResponseDto<Void> deleteMenu(Long restaurantId, Long menuId);
}
