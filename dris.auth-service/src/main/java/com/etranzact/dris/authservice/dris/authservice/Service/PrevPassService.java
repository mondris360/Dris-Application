package com.etranzact.dris.authservice.dris.authservice.Service;

import com.etranzact.dris.authservice.dris.authservice.Dto.SignUpRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PrevPassService {
    public ResponseEntity<?> changePassword(SignUpRequestDto requestDto);

}
