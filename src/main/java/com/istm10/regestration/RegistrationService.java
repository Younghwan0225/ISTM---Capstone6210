package com.istm10.regestration;

import com.istm10.appUser.AppUerRole;
import com.istm10.appUser.AppUser;
import com.istm10.appUser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AppUserService appUserService;


    public String register(RegistrationRequest request){
        boolean isValidEmail = emailValidator.test(request.getEmail() );
        if(!isValidEmail){
            throw new IllegalStateException("email no valid");
        }
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUerRole.USER
                )
        );
    }

}
