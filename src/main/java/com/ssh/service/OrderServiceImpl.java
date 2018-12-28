package com.ssh.service;

import com.ssh.entity.*;
import com.ssh.respository.OrderdetailRepositoryImpl;
import com.ssh.respository.OrdermasterRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrdermasterRepositoryImpl ordermasterRepository;
    @Autowired
    OrderdetailRepositoryImpl orderdetailRepository;
    @Autowired
    DiscountServiceImpl discountService;

    @Override
    public Integer createOrder(String customerId, List<Object[]> list,int tot) {
        Ordermaster ordermaster = new Ordermaster();
        ordermaster.setCustomerId(customerId);
        ordermaster.setInvoiceNo("wtf");
        ordermaster.setOrderDate(new Timestamp(new Date().getTime()));
        ordermaster.setOrderStatus("remaining");
        ordermaster.setOrderSum(tot);
        ordermasterRepository.save(ordermaster);
        int orderId = ordermaster.getOrderId();
        list.forEach(objects ->  {
            Orderdetail orderdetail = new Orderdetail();
            orderdetail.setOrderId(orderId);
            orderdetail.setProductId(((Product)objects[0]).getProductId());
            orderdetail.setPrice(((Product)objects[0]).getProductPrice());
            orderdetail.setQuantity(((Cart) objects[1]).getQuantity());
            orderdetailRepository.save(orderdetail);
        });
        return orderId;
    }

    @Override
    public void changeStatus(Integer id, String status) {
        Ordermaster ordermaster = ordermasterRepository.get(id);
        ordermaster.setOrderStatus(status);
        ordermasterRepository.saveOrUpdate(ordermaster);
        if (status == "cancel"){
            discountService.returnDiscount(ordermaster.getCustomerId(),ordermaster.getOrderId());
        }
    }

    @Override
    public List<Object> getOrderdetail(Integer id) {
        List list = new ArrayList<>();
        Ordermaster orderMaster = ordermasterRepository.get(id);
        List<Object[]> orderDetailList = orderdetailRepository.getOrderdetailByOrderId(id);
        List<Discount> discountList = discountService.getDiscountUsedInOrder(id);
        list.add(orderMaster);
        list.add(orderDetailList);
        list.add(discountList);
        return list;
    }


}
