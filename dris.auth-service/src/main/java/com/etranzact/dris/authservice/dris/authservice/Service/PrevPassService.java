package com.etranzact.dris.authservice.dris.authservice.Service;

import com.etranzact.dris.authservice.dris.authservice.Dto.ChangePassRequestDto;
import com.etranzact.dris.authservice.dris.authservice.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PrevPassService {
    public ResponseEntity<ApiResponse> changePassword(ChangePassRequestDto requestDto);

}
