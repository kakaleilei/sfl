package com.sfl.service;

import com.sfl.dto.EmployeeDTO;
import com.sfl.dto.EmployeeLoginDTO;
import com.sfl.entity.Employee;
import org.springframework.stereotype.Service;


public interface EmployeeService {
    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);
}
