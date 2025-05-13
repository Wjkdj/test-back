package com.test.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant_table")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL,     orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Menu> menus = new ArrayList<Menu>();

    public void addMenu(Menu menu) {
        this.menus.add(menu);
        menu.setRestaurant(this);
    }

    public void removeMenu(Menu menu) {
        this.menus.remove(menu);
        menu.setRestaurant(null);
    }
}
