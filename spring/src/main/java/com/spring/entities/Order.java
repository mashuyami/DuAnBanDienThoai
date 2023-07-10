package com.spring.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Account account;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "address", nullable = false)
    private String address;
//    @Column(name = "order_status", nullable = false)
//    private int orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

}
