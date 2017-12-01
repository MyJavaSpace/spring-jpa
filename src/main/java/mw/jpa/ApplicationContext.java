package mw.jpa;

import mw.jpa.repository.CustomerRepository;
import mw.jpa.repository.impl.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by liuyu on 2017/11/27.
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
class ApplicationContext {
    @Autowired
    CustomerRepository customers;
    public  static  void main(String[] agrs){
        SpringApplication.run(ApplicationContext.class,agrs);
    }

}
