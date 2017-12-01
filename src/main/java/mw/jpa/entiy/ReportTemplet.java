package mw.jpa.entiy;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by liuyu on 2017/11/27.
 */
@Data
@Entity(name = "tbreporttemplet")
@IdClass(ReporttempletextPK.class)
public class ReportTemplet {
    @Id
    private String fsReportId;
    @Id
    private String fsReportTempletId;
    @Id
    private String fsShopGuid;
    private String fsReportTempletName;
    private String fsReportTempletDesc;
    private Integer fiPaperSize;
    private String fsRptFile;
    private Integer fiStatus;
    private String fsUpdateTime;
    private String fsUpdateUserId;
    private String fsUpdateUserName;
    private String fsFoodTradeId;


}
