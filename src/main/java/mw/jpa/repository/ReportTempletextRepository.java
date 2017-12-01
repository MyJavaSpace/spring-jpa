package mw.jpa.repository;

import mw.jpa.dto.ReporttempletextSummary;
import mw.jpa.entiy.Reporttempletext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

/**
 * Created by liuyu on 2017/11/30.
 */
//@RepositoryRestResource(excerptProjection = ReporttempletextSummary.class)
public interface ReportTempletextRepository extends BaseRepository<Reporttempletext, Long> {
    List<ReporttempletextSummary> findTop10By();
}
