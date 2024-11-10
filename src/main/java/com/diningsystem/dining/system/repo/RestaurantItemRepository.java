package com.diningsystem.dining.system.repo;

import com.diningsystem.dining.system.model.RestaurantItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantItemRepository extends JpaRepository<RestaurantItem, Long> {

}
