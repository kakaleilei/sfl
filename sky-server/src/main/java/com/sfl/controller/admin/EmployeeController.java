package com.sfl.controller.admin;

import com.sfl.constant.JwtClaimsConstant;
import com.sfl.dto.EmployeeDTO;
import com.sfl.dto.EmployeeLoginDTO;
import com.sfl.dto.EmployeePageQueryDTO;
import com.sfl.entity.Employee;
import com.sfl.properties.JwtProperties;
import com.sfl.result.PageResult;
import com.sfl.result.Result;
import com.sfl.service.EmployeeService;
import com.sfl.utils.JwtUtil;
import com.sfl.vo.EmployeeLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Tag(name = "员工相关接口")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @Operation(summary = "员工登录")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        // 登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    @Operation(summary = "员工退出登录")
    @PostMapping("/logout")
    public Result<String> logout() {
        log.info("员工退出登录");
        return Result.success();
    }

    @Operation(summary = "新增员工")
    @PostMapping
    public Result<String> save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工：{}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询员工")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("分页查询员工：{} {} {}", employeePageQueryDTO.getName(), employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    @Operation(summary ="启用禁用员工账号")
    public Result startOrStop(@PathVariable("status") Integer status, Long id){
        log.info("启用禁用员工账号{},{}", status, id);
        employeeService.startOrStop(status,id);
        return Result.success();
    }
}
