package mw.jpa.entiy;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by liuyu on 2017/11/27.
 */
public class ReporttempletextPK implements Serializable {
    private String fsReportId;
    private String fsReportTempletId;
    private String fsShopGuid;

    @Column(name = "fsReportId")
    @Id
    public String getFsReportId() {
        return fsReportId;
    }

    public void setFsReportId(String fsReportId) {
        this.fsReportId = fsReportId;
    }

    @Column(name = "fsReportTempletId")
    @Id
    public String getFsReportTempletId() {
        return fsReportTempletId;
    }

    public void setFsReportTempletId(String fsReportTempletId) {
        this.fsReportTempletId = fsReportTempletId;
    }

    @Column(name = "fsShopGUID")
    @Id
    public String getFsShopGuid() {
        return fsShopGuid;
    }

    public void setFsShopGuid(String fsShopGuid) {
        this.fsShopGuid = fsShopGuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReporttempletextPK that = (ReporttempletextPK) o;

        if (fsReportId != null ? !fsReportId.equals(that.fsReportId) : that.fsReportId != null) return false;
        if (fsReportTempletId != null ? !fsReportTempletId.equals(that.fsReportTempletId) : that.fsReportTempletId != null)
            return false;
        if (fsShopGuid != null ? !fsShopGuid.equals(that.fsShopGuid) : that.fsShopGuid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fsReportId != null ? fsReportId.hashCode() : 0;
        result = 31 * result + (fsReportTempletId != null ? fsReportTempletId.hashCode() : 0);
        result = 31 * result + (fsShopGuid != null ? fsShopGuid.hashCode() : 0);
        return result;
    }
}
