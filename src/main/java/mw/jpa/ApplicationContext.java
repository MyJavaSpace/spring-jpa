package mw.jpa;

import mw.jpa.repository.CustomerRepository;
import mw.jpa.repository.impl.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by liuyu on 2017/11/27.
 */
@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class} )
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
class ApplicationContext {

    public  static  void main(String[] agrs){
        SpringApplication.run(ApplicationContext.class,agrs);
    }



}
