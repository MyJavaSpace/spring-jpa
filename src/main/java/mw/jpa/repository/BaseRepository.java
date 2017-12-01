package mw.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by liuyu on 2017/11/27.
 */
@NoRepositoryBean
public interface BaseRepository<M,I  extends Serializable> extends JpaRepository<M,I> {
    long customMethod();
}
