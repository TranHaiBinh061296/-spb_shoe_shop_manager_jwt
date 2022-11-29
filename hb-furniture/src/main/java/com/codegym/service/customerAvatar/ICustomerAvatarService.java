package com.codegym.service.customerAvatar;

import com.codegym.model.Avatar;

public interface ICustomerAvatarService {
    Avatar getAvatarByCustomer_Id(Long id);
    Avatar getById(String id);
}
