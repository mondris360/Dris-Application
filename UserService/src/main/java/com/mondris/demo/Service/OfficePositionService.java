package com.mondris.demo.Service;

import com.mondris.demo.Dto.OfficePositionReqDto;
import com.mondris.demo.Dto.UpdateOfficePositionReqDto;
import com.mondris.demo.Util.Api.Response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface OfficePositionService {

    ApiResponse createOfficePosition(OfficePositionReqDto request);

    ApiResponse getOfficePositionById(Long id);

    ApiResponse updateOfficePosition(UpdateOfficePositionReqDto request);

}
