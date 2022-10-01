package com.istm10.regestration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokeRepository confirmationTokeRepository;

    public void saveConfirmationToke(ConfirmationToken token){

        confirmationTokeRepository.save(token);

    }
}
