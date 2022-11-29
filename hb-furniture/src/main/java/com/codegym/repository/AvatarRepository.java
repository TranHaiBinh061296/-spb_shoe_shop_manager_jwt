package com.codegym.repository;

import com.codegym.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, String> {
    Avatar getAvatarByCustomer_Id(Long id);
}
