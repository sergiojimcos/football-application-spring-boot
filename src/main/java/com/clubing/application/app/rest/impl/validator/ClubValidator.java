package com.clubing.application.app.rest.impl.validator;

import com.clubing.application.app.rest.api.dto.ClubDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ClubValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ClubDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ClubDTO clubDTO = (ClubDTO) target;

        if (clubDTO.getPassword().length() < 8) {
            errors.rejectValue("password", "club.password.error", "Password should have " +
                    "more that 8 characters");
        }

        if(clubDTO.getFederation().length() < 8) {
            errors.rejectValue("federation", "club.federation.error", "Federation should have " +
                    "more that 8 characters");
        }
    }
}
