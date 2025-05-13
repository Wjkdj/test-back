package com.test.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_table")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
