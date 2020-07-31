package org.george.pm.service;

import org.george.pm.mapper.CurrentWorkSequenceMapper;
import org.george.pm.mapper.EmployeeMapper;
import org.george.pm.model.Employee;
import org.george.pm.model.RespBean;
import org.george.pm.model.RespPageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private CurrentWorkSequenceMapper currentWorkSequenceMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    public RespPageBean getEmployeesByPage(Integer page, Integer size, String keywords) {
        Integer offset = null;
        if (page != null && size != null) {
            offset = (page - 1) * size;
        }
        Long total = employeeMapper.getTotal(keywords);
        List<Employee> employees = employeeMapper.getEmployeesByPage(offset, size, keywords);
        RespPageBean bean = new RespPageBean();
        bean.setData(employees);
        bean.setTotal(total);
        return bean;
    }

    public Integer addEmployee(Employee e) {
        Date beginContract = e.getBeginContract();
        Date endContract = e.getEndContract();
        double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 +
                (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
        if(month < 0) return -1;
        int result = employeeMapper.insertSelective(e);
        if(result == 1){
            e = employeeMapper.getEmployeeById(e.getId());
            logger.info(e.toString());
            rabbitTemplate.convertAndSend("george.mail.welcome", e);
        }
        return result;
    }

    @Transactional
    public RespBean generateWorkId() {
        Integer workId = currentWorkSequenceMapper.getCurrentMaxWorkId();
        String incId = String.format("%08d", workId + 1);
        if (currentWorkSequenceMapper.incWorkId(incId) == 1) {
            return RespBean.build()
                    .setStatus(200)
                    .setObj(incId);
        }
        return RespBean.error("生成工号失败");
    }

    public Integer addEmps(List<Employee> list) {
        return employeeMapper.addEmps(list);
    }

    public Integer deleteEmpById(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }
}
