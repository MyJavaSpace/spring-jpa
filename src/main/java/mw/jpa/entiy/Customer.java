package mw.jpa.entiy;

import lombok.Data;
import org.hibernate.annotations.Generated;

import javax.persistence.*;

/**
 * Created by liuyu on 2017/12/1.
 */
@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String firstname;
    private Integer gender;
    private String lastname;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

}
