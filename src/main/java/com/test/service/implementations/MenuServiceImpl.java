package com.test.service.implementations;

import com.test.common.ResponseMessage;
import com.test.dto.request.PostMenuRequestDto;
import com.test.dto.response.MenuResponseDto;
import com.test.dto.response.ResponseDto;
import com.test.entity.Menu;
import com.test.entity.Restaurant;
import com.test.repository.MenuRepository;
import com.test.repository.RestaurantRepository;
import com.test.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    @Transactional(readOnly = false)
    public ResponseDto<MenuResponseDto> craeteMenu(Long restaurantId, PostMenuRequestDto dto) {
        MenuResponseDto responseDto = null;

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + restaurantId));

        Menu newMenu = Menu.builder()
                .name(dto.getName())
                .price(String.valueOf(dto.getPrice()))
                .description(dto.getDescription())
                .build();

        restaurant.addMenu(newMenu);

        Menu savedMenu = menuRepository.save(newMenu);

        responseDto = new MenuResponseDto.Builder(savedMenu.getName(), savedMenu.getDescription())
                .id(savedMenu.getId())
                .restaurant_id(savedMenu.getRestaurant().getId())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Transactional
    @Override
    public ResponseDto<MenuResponseDto> updateMenu(Long restaurantId, Long menuId, PostMenuRequestDto dto) {
        MenuResponseDto responseDto = null;

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + menuId));

        if(!menu.getRestaurant().getId().equals(restaurantId)) {
            throw new IllegalArgumentException("Menu does not belong to the specified Restaurant");
        }

        menu.setName(dto.getName());
        Menu updatedMenu = menuRepository.save(menu);

        responseDto = MenuResponseDto.builder()
                .id(Long.valueOf(updatedMenu.getId()))
                .name(updatedMenu.getName())
                .price(Double.parseDouble(updatedMenu.getPrice()))
                .description(updatedMenu.getDescription())
                .restaurantId(updatedMenu.getRestaurant().getId())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    public ResponseDto<Void> deleteMenu(Long restaurantId, Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + menuId));

        if(!menu.getRestaurant().getId().equals(restaurantId)) {
            throw new IllegalArgumentException("Menu does not belong to the specified Restaurant");
        }

        menu.getRestaurant().removeMenu(menu);

        menuRepository.delete(menu);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}
