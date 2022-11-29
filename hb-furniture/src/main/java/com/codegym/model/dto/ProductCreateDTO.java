package com.codegym.model.dto;

import com.codegym.model.Product;
import com.codegym.model.ProductAvatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCreateDTO implements Validator{

    private Long id;
    @NotEmpty(message = "Tên sản phẩm không được trống")
    @Length(min = 5, message = "Tên sản phẩm có ít nhất 5 ký tự")
    @Length(max = 50, message = "Tên sản phẩm có nhiều nhất 50 ký tự")
    private String title;

    @NotNull(message = "Vui lòng nhập số tiền sản phẩm!")
    @Min(value = 10000, message = "Số tiền nhỏ nhất là 10,000VND")
    @Max(value = 100000000, message = "Số tiền tối đa là 100.000.000VND")
    private String price;

    @NotNull(message = "Số lượng sản phẩm không được để trống!")
    @Min(value = 1, message = "Số lượng sản phẩm nhỏ nhất là 1!")
        @Max(value = 100, message = "Số lượng sản phẩm lớn nhất là 100!")
    private String quantity;
    private String description;


    private MultipartFile file;


    public Product toProduct(ProductAvatar productAvatar) {
        return new Product()
                .setId(id)
                .setTitle(title)
                .setPrice(BigDecimal.valueOf(Long.valueOf(price)))
                .setQuantity(Long.valueOf(quantity))
                .setDescription(description)
                .setProductAvatar(productAvatar);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductCreateDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductCreateDTO productCreateDTO = (ProductCreateDTO) target;

        String price = productCreateDTO.getPrice();

        if (price.isEmpty()) {
            errors.rejectValue("price", "","Giá sản phẩm không đuợc trống");
        } else if (!price.matches("(^\\d+$)")){
            errors.rejectValue("price", "","Giá sản phẩm phải là số");
        } else if (Long.valueOf(price) < 10000) {
            errors.rejectValue("price", "","Giá sản phẩm thấp nhất là 10.000 vnđ");
        } else if (Long.valueOf(price) > 999999999999l) {
            errors.rejectValue("price", "","Giá sản phẩm quá lớn");
        }

        String quantity = productCreateDTO.getQuantity();

        if (quantity.isEmpty()) {
            errors.rejectValue("quantity", "","Số lượng sản phẩm không đuợc trống");
        } else if (!quantity.matches("(^\\d+$)")){
            errors.rejectValue("quantity", "","Số lượng sản phẩm phải là số");
        } else if (Long.valueOf(quantity) < 1) {
            errors.rejectValue("quantity", "","Số lượng sản phẩm lớn hơn 0");
        } else if (Long.valueOf(quantity) > 100) {
            errors.rejectValue("quantity", "","Số lượng sản phẩm quá lớn");
        }

    }

}
