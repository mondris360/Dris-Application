package com.mondris.demo.Service;

import com.mondris.demo.Dto.DepartmentHeadReqDto;
import com.mondris.demo.Model.DepartmentHead;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentHeadService {
     DepartmentHead createDepartmentHead(DepartmentHeadReqDto request);
}
