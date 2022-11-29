package com.codegym.model.dto;

import com.codegym.model.Customer;
import com.codegym.model.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerAvatarDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private AvatarDTO avatarDTO;
    private Boolean deleted;
    private LocationRegionDTO locationRegion;

    public CustomerAvatarDTO(Long id, String fullName, String email, String phone, LocationRegion locationRegion, AvatarDTO avatarDTO) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.locationRegion = locationRegion.toLocationRegionDTO();
        this.avatarDTO = avatarDTO;
    }

    public CustomerAvatarDTO(Long id, String fullName, String email,
                             String phone, LocationRegion locationRegion,
                             String idAvr, String fileName, String fileFolder, String fileUrl,
                             String cloudId, String fileType) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.deleted = false;
        this.avatarDTO = new AvatarDTO(idAvr,fileName,fileFolder,fileUrl,cloudId,fileType);
        this.locationRegion = locationRegion.toLocationRegionDTO();
    }

    public Customer toCustomer() {
        return new Customer()
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setLocationRegion(locationRegion.toLocationRegion());
    }
}


