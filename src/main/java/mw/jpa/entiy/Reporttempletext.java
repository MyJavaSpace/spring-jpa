package mw.jpa.entiy;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by liuyu on 2017/11/27.
 */
@Entity(name = "tbreporttempletext")
@IdClass(ReporttempletextPK.class)
@Data
public class Reporttempletext {
    @Id
    private String fsReportId;
    @Id
    private String fsReportTempletId;
    @Id
    private String fsShopGuid;
    private String fsHtmlContect;
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumns( {@JoinColumn(name = "fsReportId",  referencedColumnName = "fsReportId", nullable = false,insertable = false,updatable = false), @JoinColumn(name = "fsReportTempletId", referencedColumnName = "fsReportTempletId", nullable = false,insertable = false,updatable = false), @JoinColumn(name = "fsShopGUID", referencedColumnName = "fsShopGUID", nullable = false,insertable = false,updatable = false) })
    @org.hibernate.annotations.ForeignKey(name="FK_Ref_ReportId_ReportTempletId_ShopGUID")
    private ReportTemplet tbreporttemplet;

}
