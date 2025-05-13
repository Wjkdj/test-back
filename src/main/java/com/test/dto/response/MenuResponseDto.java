package com.test.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MenuResponseDto {
    private Long id;
    private Long restaurantId;
    private String name;
    private double price;
    private String description;

    public static class Builder {
        private Long id;
        private Long restaurantId;
        private String name;

        private int price;
        private String description;

        public Builder(int price, String description) {
            this.price = price;
            this.description = description;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder restaurantId(Long restaurantId) {
            this.restaurantId=restaurantId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public MenuResponseDto build() { return new MenuResponseDto(id,restaurantId , name, price, description); }

    }
}
