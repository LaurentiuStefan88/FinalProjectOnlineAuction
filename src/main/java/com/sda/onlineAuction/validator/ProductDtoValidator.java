package com.sda.onlineAuction.validator;

import com.sda.onlineAuction.dto.ProductDto;
import com.sda.onlineAuction.model.Category;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class ProductDtoValidator {

    public void validate(ProductDto productDto, BindingResult bindingResult) {
        validateProductName(productDto, bindingResult);
        validateDescription(productDto, bindingResult);
        validateStartBidingPrice(productDto, bindingResult);
        validateCategory(productDto, bindingResult);
        validateEndDateTime(productDto, bindingResult);
    }

    private void validateProductName(ProductDto productDto, BindingResult bindingResult) {
        String name = productDto.getName();
        if (NumberUtils.isCreatable(name)) {
            FieldError fieldError = new FieldError("productDto", "name", "The name must be a text, not a number.");
            bindingResult.addError(fieldError);
        }
        if (name.isEmpty() || name.length() < 3) {
            FieldError fieldError = new FieldError("productDto", "name", "The name must have at least 3 characters.");
            bindingResult.addError(fieldError);
        }
    }

    private void validateDescription(ProductDto productDto, BindingResult bindingResult) {
        String description = productDto.getDescription();
        if (NumberUtils.isCreatable(description)) {
            FieldError fieldError = new FieldError("productDto", "description", "Fill in a text, not a number.");
            bindingResult.addError(fieldError);
        }
        if (description.isEmpty() || StringUtils.isBlank(description)) {
            FieldError fieldError = new FieldError("productDto", "description", "Please fill in a description.");
            bindingResult.addError(fieldError);
        }
    }

    private void validateStartBidingPrice(ProductDto productDto, BindingResult bindingResult) {
        String priceAsString = productDto.getStartBidingPrice();
        try {
            Integer priceAsInteger = Integer.valueOf(priceAsString);
            if (priceAsInteger <= 0) {
                FieldError fieldError = new FieldError("productDto", "startBidingPrice", "The price must be positive.");
                bindingResult.addError(fieldError);
            }
        } catch (NumberFormatException numberFormatException) {
            FieldError fieldError = new FieldError("productDto", "startBidingPrice", "The price must be a number.");
            bindingResult.addError(fieldError);
        }
    }

    private void validateCategory(ProductDto productDto, BindingResult bindingResult) {
        String category = productDto.getCategory();
        if (NumberUtils.isCreatable(category) || category.isEmpty()) {
            FieldError fieldError = new FieldError("productDto", "category", "Select on option!");
            bindingResult.addError(fieldError);
        }
        if (!EnumUtils.isValidEnum(Category.class, category.toUpperCase())) {
            FieldError fieldError = new FieldError("productDto", "category", "Invalid option! Select again!.");
            bindingResult.addError(fieldError);
        }
    }

    private void validateEndDateTime(ProductDto productDto, BindingResult bindingResult) {
        String endDateTime = productDto.getEndDateTime();
        if (NumberUtils.isCreatable(endDateTime) || endDateTime.isEmpty() || StringUtils.isBlank(endDateTime) || !LocalDateTime.parse(endDateTime).isAfter(LocalDateTime.now().plusDays(1))) {
            FieldError fieldError = new FieldError("productDto", "endDateTime", "Select minim 24Hr bidding time!");
            bindingResult.addError(fieldError);
        }
    }

}