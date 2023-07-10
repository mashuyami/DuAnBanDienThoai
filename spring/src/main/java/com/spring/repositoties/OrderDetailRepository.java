package com.spring.repositoties;

import com.spring.entities.OrderDetail;
import com.spring.entities.ReportCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    @Query("select od from OrderDetail od where od.order.id=:orderId")
    List<OrderDetail> findByOrderId(@Param("orderId") Integer orderId);

//    @Query("select new ReportCategory(od.product.category, sum(od.price*od.quantity), sum(od.quantity)) from OrderDetail od group by od.product.category")
//    List<ReportCategory> reportByCategory();
@Query("select new ReportCategory(od.product.category, sum(od.price*od.quantity), sum(od.quantity)) from OrderDetail od group by od.product.category, od.product.category.id")
List<ReportCategory> reportByCategory();

    @Query("select new ReportCategory(od.product.category, sum(od.price*od.quantity), sum(od.quantity)) from OrderDetail od group by od.product.category")
    List<ReportCategory> reportByProduct();


}
