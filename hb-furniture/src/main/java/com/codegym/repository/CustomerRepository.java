package com.codegym.repository;

import com.codegym.model.Customer;
import com.codegym.model.dto.CustomerAvatarDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT NEW com.codegym.model.dto.CustomerAvatarDTO(" +
            "c.id, " +
            "c.fullName, " +
            "c.email, " +
            "c.phone, " +
            "c.locationRegion, " +
            "a.id, " +
            "a.fileName, " +
            "a.fileFolder, " +
            "a.fileUrl, " +
            "a.cloudId, " +
            "a.fileType " +
            ") " +
            "FROM Customer AS c " +
            "LEFT JOIN Avatar AS a " +
            "ON a.customer = c " +
            "WHERE c.deleted = false"
    )
    List<CustomerAvatarDTO> getAllCustomersAvartaDTO();

    @Query("SELECT NEW com.codegym.model.dto.CustomerAvatarDTO(" +
            "c.id, " +
            "c.fullName, " +
            "c.email, " +
            "c.phone, " +
            "c.locationRegion, " +
            "a.id, " +
            "a.fileName, " +
            "a.fileFolder, " +
            "a.fileUrl, " +
            "a.cloudId, " +
            "a.fileType " +
            ") " +
            "FROM Customer AS c " +
            "LEFT JOIN Avatar AS a " +
            "ON a.customer = c " +
            "WHERE c.deleted = false and c.id = :id"
    )
    CustomerAvatarDTO getCustomersAvatarDTOById(Long id);

    List<Customer> findAllByIdNot(Long senderId);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByEmailAndIdIsNot(String email, Long id);
    List<Customer> findAllByDeletedIsFalse();
}
