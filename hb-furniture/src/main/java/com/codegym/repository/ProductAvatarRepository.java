package com.codegym.repository;


import com.codegym.model.ProductAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAvatarRepository extends JpaRepository<ProductAvatar, String> {
}
