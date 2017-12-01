package mw.jpa.repository;

import mw.jpa.entiy.ReportTemplet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Created by liuyu on 2017/11/27.
 */
public interface ReportTempletRepository extends BaseRepository<ReportTemplet,String>,QueryByExampleExecutor<ReportTemplet> {

}
