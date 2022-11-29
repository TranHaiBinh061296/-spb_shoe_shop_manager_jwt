package com.codegym.utils;

import com.cloudinary.utils.ObjectUtils;
import com.codegym.exception.DataInputException;


import com.codegym.model.Avatar;
import com.codegym.model.Customer;
import com.codegym.model.Product;
import com.codegym.model.ProductAvatar;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UploadUtils {
    public static final String IMAGE_UPLOAD_FOLDER = "avatar_images";

    public static final String PRODUCT_IMAGE_UPLOAD_FOLDER = "product_images";

    public Map buildImageUploadParams(Avatar avatar) {
        if (avatar == null || avatar.getId() == null)
            throw new DataInputException("Không thể upload hình ảnh của sản phẩm chưa được lưu");

        String publicId = String.format("%s/%s", IMAGE_UPLOAD_FOLDER, avatar.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildImageUploadParams(ProductAvatar productAvatar) {
        if (productAvatar == null || productAvatar.getId() == null)
            throw new DataInputException("Không thể upload hình ảnh của sản phẩm chưa được lưu");

        String publicId = String.format("%s/%s", PRODUCT_IMAGE_UPLOAD_FOLDER, productAvatar.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildImageDestroyParams(Customer customer, String publicId) {
        if (customer == null)
            throw new DataInputException("Không thể destroy hình ảnh của sản phẩm không xác định");

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildImageDestroyParams(Product product,String publicId) {
        if (product == null)
            throw new DataInputException("Không thể destroy hình ảnh của sản phẩm không xác định");

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

}
