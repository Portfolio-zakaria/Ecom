package com.zakaria.ecom.repositories;

import com.zakaria.ecom.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    User findByEmail(@Param("email") String email);
}
