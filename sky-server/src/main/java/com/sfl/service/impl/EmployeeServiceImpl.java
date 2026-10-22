package com.sfl.service.impl;

import com.sfl.dto.EmployeeLoginDTO;
import com.sfl.entity.Employee;
import com.sfl.mapper.EmployeeMapper;
import com.sfl.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        return null;
    }
}
