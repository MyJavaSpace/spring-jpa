package mw.jpa.service;

import mw.jpa.dto.ReporttempletextSummary;
import mw.jpa.entiy.ReportTemplet;
import mw.jpa.entiy.Reporttempletext;

import java.util.Collection;
import java.util.List;

/**
 * Created by liuyu on 2017/11/27.
 */
public interface ReportTempletService {
      List<ReportTemplet> selectAll();
      List<Reporttempletext> selectReporttempletextAll();

    List<ReporttempletextSummary> selectReporttempletextAllName();
}
