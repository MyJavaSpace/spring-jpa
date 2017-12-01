package mw.jpa.controller;

import mw.jpa.dto.CustomerExcerpt;
import mw.jpa.dto.ReporttempletextSummary;
import mw.jpa.entiy.ReportTemplet;
import mw.jpa.entiy.Reporttempletext;
import mw.jpa.repository.CustomerRepository;
import mw.jpa.service.ReportTempletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * Created by liuyu on 2017/11/27.
 */
@RestController
@RequestMapping("/reporttemplet")
public class ReportTempletController {

    @Autowired private ReportTempletService reportTempletService;
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
        return customerRepository.findAllBy();
    }
}
