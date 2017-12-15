package mw.jpa.service.impl;

import mw.jpa.dto.CustomerExcerpt;
import mw.jpa.entiy.Customer;
import mw.jpa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Created by liuyu on 2017/12/13.
 */
@Service
public class CustomerServiceImpl {
    @Autowired
    private CustomerRepository customerRepository;
    @Transactional(readOnly = true,rollbackFor = Throwable.class)
    public  void  find(){
        customerRepository.findOne((long)1);
    }
    @Transactional(readOnly = false,rollbackFor = Throwable.class)
    public  void  insert(){
        Customer customer =new Customer();
        customer.setFirstname("liuyu"+ Calendar.getInstance().getTime().toString());
        customer.setGender(1);
        customer.setLastname("jiongsi");
        customerRepository.save(customer);
    }
    @Transactional(readOnly = true,rollbackFor = Throwable.class )
    public List<CustomerExcerpt> findAllBy(){
        return customerRepository.findAllBy();
    }
}
