package com.test.repository;

import com.test.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query(value = "SELECT * FROM post WHERE name = :name", nativeQuery = true)
    List<Restaurant> findByName(@Param("name") String name);
}
