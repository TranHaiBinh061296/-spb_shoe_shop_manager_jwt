package com.codegym.controller.api;


import com.codegym.exception.DataInputException;
import com.codegym.exception.EmailExistsException;
import com.codegym.model.Customer;
import com.codegym.model.LocationRegion;
import com.codegym.model.dto.AvatarDTO;
import com.codegym.model.dto.CustomerAvatarCreateDTO;
import com.codegym.model.dto.CustomerAvatarDTO;
import com.codegym.repository.AvatarRepository;
import com.codegym.service.customer.ICustomerService;
import com.codegym.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private AppUtils appUtils;

    @Autowired
    private AvatarRepository avatarRepository;

    @GetMapping
    private ResponseEntity<?> findAllByDeletedIsFalse() {
        List<CustomerAvatarDTO> customers = customerService.getAllCustomersAvatarDTO();
        if (customers.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    private ResponseEntity<?> getCustomer(@PathVariable Long customerId) {
        Optional<Customer> customerOptional = customerService.findById(customerId);
        if (!customerOptional.isPresent()) {
            throw new DataInputException("ID khách hàng không hợp lệ");
        }
        return new ResponseEntity<>(customerOptional.get().toCustomerAvatarDTO(), HttpStatus.OK);
    }



    @PostMapping
    private ResponseEntity<?> doCreate(@Validated @ModelAttribute CustomerAvatarCreateDTO customerAvatarCreateDTO, BindingResult bindingResult) {
        new CustomerAvatarCreateDTO().validate(customerAvatarCreateDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        LocationRegion locationRegion = new LocationRegion();
        locationRegion.setId(0L);
        locationRegion.setProvinceId(customerAvatarCreateDTO.getProvinceId());
        locationRegion.setProvinceName(customerAvatarCreateDTO.getProvinceName());
        locationRegion.setDistrictId(customerAvatarCreateDTO.getDistrictId());
        locationRegion.setDistrictName(customerAvatarCreateDTO.getDistrictName());
        locationRegion.setWardId(customerAvatarCreateDTO.getWardId());
        locationRegion.setWardName(customerAvatarCreateDTO.getWardName());
        locationRegion.setAddress(customerAvatarCreateDTO.getAddress());


        customerAvatarCreateDTO.setId(0L);
        Customer customer = customerAvatarCreateDTO.toCustomer(locationRegion);
        CustomerAvatarDTO newCustomer;
        if (customerAvatarCreateDTO.getFile() != null) {
            newCustomer = customerService.saveWithAvatar(customer, customerAvatarCreateDTO.getFile());
        } else {
            customer.getLocationRegion().setId(null);
            newCustomer = customerService.save(customer).toCustomerAvatarDTO();
            newCustomer.setAvatarDTO(new AvatarDTO());
        }
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PatchMapping("/{customerId}")
    private ResponseEntity<?> doUpdate(@PathVariable Long customerId, @Validated @ModelAttribute CustomerAvatarCreateDTO customerAvatarUpdateDTO, BindingResult bindingResult) {

        new CustomerAvatarCreateDTO().validate(customerAvatarUpdateDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            throw new DataInputException("ID khách hàng không hợp lệ");
        }

        Customer customer = customerOptional.get();
        Long locationId = customer.getLocationRegion().getId();

        Optional<Customer> emailOptional = customerService.findByEmailAndIdIsNot(customerAvatarUpdateDTO.getEmail(), customerId);
        if (emailOptional.isPresent()) {
            throw new EmailExistsException("Email đã tồn tại!");
        }

        customer.setFullName(customerAvatarUpdateDTO.getFullName());
        customer.setEmail(customerAvatarUpdateDTO.getEmail());
        customer.setPhone(customerAvatarUpdateDTO.getPhone());

        LocationRegion locationRegion = new LocationRegion();
        locationRegion.setId(locationId);
        locationRegion.setProvinceId(customerAvatarUpdateDTO.getProvinceId());
        locationRegion.setProvinceName(customerAvatarUpdateDTO.getProvinceName());
        locationRegion.setDistrictId(customerAvatarUpdateDTO.getDistrictId());
        locationRegion.setDistrictName(customerAvatarUpdateDTO.getDistrictName());
        locationRegion.setWardId(customerAvatarUpdateDTO.getWardId());
        locationRegion.setWardName(customerAvatarUpdateDTO.getWardName());
        locationRegion.setAddress(customerAvatarUpdateDTO.getAddress());

        customer.setLocationRegion(locationRegion);
        AvatarDTO avatarDTO = new AvatarDTO();
        try {
            avatarDTO = avatarRepository.getAvatarByCustomer_Id(customerId).toAvatarDTO();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (customerAvatarUpdateDTO.getFile() == null) {
            CustomerAvatarDTO updateCustomer = customerService.save(customer).toCustomerAvatarDTO();
            updateCustomer.setAvatarDTO(avatarDTO);
            return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
        }else  {
            CustomerAvatarDTO updatedCustomer = customerService.updateWithAvatar(customer,customerAvatarUpdateDTO.getFile(),avatarDTO);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        }
    }
    @PatchMapping("/delete/{customerId}")
    private ResponseEntity<?> doDelete(@PathVariable Long customerId) {
        Optional<Customer> customerOptional = customerService.findById(customerId);
        if (!customerOptional.isPresent()) {
            throw new DataInputException("ID khách hàng không hợp lệ");
        }
        Customer customer = customerOptional.get();
        customer.setDeleted(true);
        customerService.save(customer);
        return new ResponseEntity<>(customerOptional.get().toCustomerAvatarDTO(), HttpStatus.OK);
    }
}
