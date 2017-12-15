package mw.jpa.controller;

import mw.jpa.dto.CustomerExcerpt;
import mw.jpa.dto.ReporttempletextSummary;
import mw.jpa.entiy.*;
import mw.jpa.repository.CustomerRepository;
import mw.jpa.rwdb.DynamicDataSourceGlobal;
import mw.jpa.rwdb.DynamicDataSourceHolder;
import mw.jpa.service.ReportTempletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by liuyu on 2017/11/27.
 */
@RestController
@RequestMapping("/reporttemplet")
public class ReportTempletController {
    private Logger log = LoggerFactory.getLogger(ReportTempletController.class);

    @Autowired private ReportTempletService reportTempletService;
    @Autowired
    private UserOuterService userOuterService;

    @GetMapping("all")
    public List<ReportTemplet> getAll(){
        return  reportTempletService.selectAll();
    }
    @GetMapping("alltext")
    public List<Reporttempletext> getAllText(){
        return  reportTempletService.selectReporttempletextAll();
    }
    @GetMapping("nameonly")
    public List<ReporttempletextSummary> getAllTextName(){
        return  reportTempletService.selectReporttempletextAllName();
    }
    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("get")
    public List<CustomerExcerpt> get(){
        DynamicDataSourceHolder.putDataSource(DynamicDataSourceGlobal.READ);
        customerRepository.findOne((long)1);
        DynamicDataSourceHolder.putDataSource(DynamicDataSourceGlobal.WRITE);
        Customer customer =new Customer();
        customer.setFirstname("liuyu"+ Calendar.getInstance().getTime().toString());
        customer.setGender(1);
        customer.setLastname("jiongsi");
        customerRepository.save(customer);
        DynamicDataSourceHolder.putDataSource(DynamicDataSourceGlobal.READ);
        return customerRepository.findAllBy();
    }
    @GetMapping("getall")
    public List<CustomerExcerpt> getall(){
        return customerRepository.findAllBy();
    }
    @GetMapping("add")
//    @Transactional(readOnly = true)
    public Customer add(){
        Customer customer =new Customer();
        customer.setFirstname("liuyu");
        customer.setGender(1);
        customer.setLastname("jiongsi");
        return customerRepository.save(customer);
    }
    @GetMapping("test")
    public void saveWrite() throws Exception {
        User newUserFromRead = userOuterService.findByIdRead(1);
        User newUser = new User();
        newUser.setName("New User");

        userOuterService.save(newUser);
        log.info("User saved : {}", newUser);

        newUserFromRead = userOuterService.findByIdRead(newUser.getId());
//        assertThat(newUserFromRead).as("New user is saved to write db. So read db must not have the user.").isNull();

        User newUserFromWrite = userOuterService.findByIdWrite(newUser.getId());
//        assertThat(newUserFromWrite).as("New user is saved to write db. So write db must have the user.").isNotNull().isEqualTo(newUser);
    }
}
