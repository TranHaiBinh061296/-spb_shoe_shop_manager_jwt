package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.dto.AvatarDTO;
import com.codegym.model.dto.CustomerAvatarDTO;
import com.codegym.service.IGeneralService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {
    Optional<Customer> findByEmailAndIdIsNot(String email, Long id);
    List<Customer> findAllByDeletedIsFalse();

    List<CustomerAvatarDTO> getAllCustomersAvatarDTO();
    CustomerAvatarDTO getCustomersAvatarDTOById(Long id);

    CustomerAvatarDTO saveWithAvatar(Customer customer, MultipartFile avatarFile);
    CustomerAvatarDTO updateWithAvatar(Customer customer, MultipartFile avatarFile, AvatarDTO avatarDTO);
}
