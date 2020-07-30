package org.george.pm.controller.employee;

import org.george.pm.mapper.DepartmentMapper;
import org.george.pm.model.*;
import org.george.pm.service.*;
import org.george.pm.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/emp/basic/")
public class EmpBasicController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private NationService nationService;

    @Autowired
    private PoliticsStatusService politicsStatusService;

    @Autowired
    private JobLevelService jobLevelService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public RespPageBean getEmployeesByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, String keywords) {
        return employeeService.getEmployeesByPage(page, size, keywords);
    }

    @PostMapping("/")
    public RespBean addEmployee(@RequestBody Employee e) {
        if (employeeService.addEmployee(e) == 1)
            return RespBean.ok("添加成功");
        return RespBean.error("添加失败");
    }

    @GetMapping("/nation")
    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }

    @GetMapping("/politic")
    public List<PoliticsStatus> getAllPoliticsStatus() {
        return politicsStatusService.getAllPoliticsStatus();
    }

    @GetMapping("/job")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    @GetMapping("/pos")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @GetMapping("/workId")
    public RespBean generateWorkId() {
        return employeeService.generateWorkId();
    }

    @GetMapping("/dep")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData() {
        List<Employee> list = (List<Employee>) employeeService.getEmployeesByPage(null, null, null).getData();
        return POIUtils.employee2Excel(list);
    }

    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
        List<Employee> list = POIUtils.excel2Employee(file, nationService.getAllNations(), politicsStatusService.getAllPoliticsStatus(), departmentService.getAllDepartmentsNotRec(), positionService.getAllPositions(), jobLevelService.getAllJobLevels());
        if (employeeService.addEmps(list) == list.size()) {
            return RespBean.ok("上传成功");
        }
        return RespBean.error("上传失败");
    }
}
