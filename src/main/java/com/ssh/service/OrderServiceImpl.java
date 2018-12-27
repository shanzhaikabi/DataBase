package com.ssh.service;

import com.ssh.entity.Cart;
import com.ssh.entity.Orderdetail;
import com.ssh.entity.Ordermaster;
import com.ssh.entity.Product;
import com.ssh.respository.OrderdetailRepositoryImpl;
import com.ssh.respository.OrdermasterRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService{
    @Autowired
    OrdermasterRepositoryImpl ordermasterRepository;
    @Autowired
    OrderdetailRepositoryImpl orderdetailRepository;

    @Override
    public void createOrder(String customerId, List<Object[]> list,int tot) {
        Ordermaster ordermaster = new Ordermaster();
        ordermaster.setCustomerId(customerId);
        ordermaster.setInvoiceNo("wtf");
        ordermaster.setOrderDate(new Timestamp(new Date().getTime()));
        ordermaster.setOrderStatus("remaining");
        ordermaster.setOrderSum(tot);
        Integer orderId = ordermasterRepository.save(ordermaster);
        list.forEach(objects -> {
            Orderdetail orderdetail = new Orderdetail();
            orderdetail.setOrderId(orderId);
            orderdetail.setProductId(((Product)objects[0]).getProductId());
            orderdetail.setPrice(((Product)objects[0]).getProductPrice());
            orderdetail.setQuantity(((Cart) objects[1]).getQuantity());
            orderdetailRepository.save(orderdetail);
        });
    }

    @Override
    public void changeStatus(Integer id, String status) {
        Ordermaster ordermaster = ordermasterRepository.get(id);
        ordermaster.setOrderStatus(status);
        ordermasterRepository.saveOrUpdate(ordermaster);
    }


}
