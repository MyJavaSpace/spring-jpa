package mw.jpa.service.impl;

import mw.jpa.dto.ReporttempletextSummary;
import mw.jpa.entiy.ReportTemplet;
import mw.jpa.entiy.Reporttempletext;
import mw.jpa.repository.ReportTempletRepository;
import mw.jpa.repository.ReportTempletextRepository;
import mw.jpa.service.ReportTempletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Created by liuyu on 2017/11/27.
 */
@Service
public class ReportTempletServiceImpl implements ReportTempletService {
//    @Autowired
//    private ReportTempletRepository reportTempletRepository;
    @Autowired
    private ReportTempletRepository reportTempletRepository;
    @Autowired
    private ReportTempletextRepository reportTempletextRepository;
    @Override
    public List<ReportTemplet> selectAll() {
        //创建查询条件数据对象
        ReportTemplet customer = new ReportTemplet();
        customer.setFsReportTempletId("R01_1_58");
        Example<ReportTemplet> ex = Example.of(customer);
        return  reportTempletRepository.findAll(ex);
    }
    public List<Reporttempletext> selectReporttempletextAll(){
        Reporttempletext reporttempletext=new Reporttempletext();
        reporttempletext.setFsReportTempletId("R01_2_80");
        reporttempletext.setFsShopGuid("25854");
        reporttempletext.setFsReportId("R01");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("fsHtmlContect");
        Example<Reporttempletext> ex = Example.of(reporttempletext,matcher);
        return null;
        //return reportTempletextRepository.findAll(ex);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporttempletextSummary> selectReporttempletextAllName() {
        return reportTempletextRepository.findTop10By();
    }
}
