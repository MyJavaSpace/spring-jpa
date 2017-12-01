package mw.jpa.dto;

import mw.jpa.entiy.ReportTemplet;
import mw.jpa.entiy.Reporttempletext;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.beans.factory.annotation.Value;
/**
 * Created by liuyu on 2017/11/30.
 */
//@Projection(name = "excerpt", types = Reporttempletext.class)
public interface ReporttempletextSummary {
    String getFsReportId();

    ReportTempletSummary getTbreporttemplet();
    interface  ReportTempletSummary{
        String getFsRptFile();
    }
}
