package com.codegym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AvatarDTO {

    private String id;
    private String fileName;
    private String fileFolder;
    private String fileUrl;
    private String cloudId;
    private String fileType;

}
