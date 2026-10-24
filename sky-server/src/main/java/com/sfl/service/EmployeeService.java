package com.sfl.service;

import com.sfl.dto.EmployeeDTO;
import com.sfl.dto.EmployeeLoginDTO;
import com.sfl.dto.EmployeePageQueryDTO;
import com.sfl.entity.Employee;
import com.sfl.result.PageResult;
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

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用禁用员工账号
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
