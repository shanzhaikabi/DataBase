package com.ssh.utils;

import com.ssh.entity.*;

import java.util.Iterator;
import java.util.List;

public class OrderUtils {
    public static String confirm_order_product(List<Object[]>list)
    {
        String ans = "<fieldset><legend>订单确认</legend>";
        ans = ans + "<fieldset><legend>商品清单</legend>";
        Iterator<Object[]>it = list.iterator();
        Integer cnt = 0;
        while(it.hasNext())
        {
            cnt++;
            Object[] tmp = it.next();
            ans = ans + product_order(cnt,(Product)tmp[0],(Orderdetail) tmp[1]);
            ans = ans + "</fieldset>";
        }
        ans = ans + "</fieldset>";
        return ans;
    }
    public static String confirm_cart_product(List<Object[]>list)
    {
        String ans = "<fieldset><legend>订单确认</legend>";
        ans = ans + "<fieldset><legend>商品清单</legend>";
        Iterator<Object[]>it = list.iterator();
        Integer cnt = 0;
        while(it.hasNext())
        {
            cnt++;
            Object[] tmp = it.next();
            ans = ans + product_order(cnt,(Product)tmp[0],(Cart) tmp[1]);
            ans = ans + "</fieldset>";
        }
        ans = ans + "</fieldset>";
        return ans;
    }
    public static String confirm_order_discount(List<Discount>list)
    {
        String ans = "<fieldset><legend>使用的优惠券</legend>";
        Iterator<Discount>it = list.iterator();
        Integer cnt = 0;
        while(it.hasNext())
        {
            cnt++;
            ans = ans + DiscountUtils.discount_detail(it.next(),"");
        }
        ans = ans + "</fieldset>";
        return ans;
    }
    public static String confirm_order_invoice(Ordermaster o,String s)
    {
        String ans = "<fieldset><legend>订单小票</legend>";
        ans = ans + "订单编号：" + ((Integer)o.getOrderId()).toString();
        ans = ans + "<br>下单时间：" + (o.getOrderDate()).toString();
        if(o.getOrderStatus().equals("yes")) {
            ans = ans + "<br>订单状态：已完成";
            ans = ans + "<br>发票号：" + o.getInvoiceNo();
            ans = ans + "<br>实际支付：" + o.getOrderSum().toString();
            if(s.equals("all"))ans = ans + "<br><a href = \"showorder.do?id="+((Integer)o.getOrderId()).toString()+"\">查看订单详情</a><br>";
        }
        else if(o.getOrderStatus().equals("remaining"))
        {
            ans = ans + "<br>订单状态：待付款";
            //ans = ans + "<br>发票号：" + o.getInvoiceNo();
            ans = ans + "<br>应支付：" + o.getOrderSum().toString();
            if(s.equals("all"))ans = ans + "<br><a href = \"showorder.do?id="+((Integer)o.getOrderId()).toString()+"\">查看订单详情</a><br>";
            ans = ans + "<br><a href = \"payorder.do?id="+((Integer)o.getOrderId()).toString()+"\">立即支付</a>&nbsp&nbsp&nbsp&nbsp";
            ans = ans + "<a href = \"cancelorder.do?id="+((Integer)o.getOrderId()).toString()+"\">取消订单</a><br>";
        }
        else ans = ans + "<br>订单状态：已取消";
        ans = ans + "</fieldset>";
        return ans;
    }
    public static String confirm_order_total(Integer sum,Integer dis)
    {
        String ans = "</fieldset><fieldset><legend>结算页</legend>";
        ans = ans + "总价：" + ((Integer)(sum-dis)).toString();
        ans = ans + "<br>已节省：" + dis.toString();
        ans = ans + "<br><input type = \"submit\" value = \"确认无误，下单\">";
        ans = ans + "</fieldset>";
        return ans;
    }

    public static String product_order(Integer x, Product p, Orderdetail c)
    {
        String ans = "<fieldset><legend>商品"+x.toString()+"信息</legend>";
        ans = ans + "商品名称：" + p.getProductName() + "<br>";
        ans = ans + "数量：" + c.getQuantity().toString() + "&nbsp&nbsp&nbsp&nbsp";
        ans = ans + "单价：" + c.getPrice().toString() + "<br>";
        Integer sum = (c.getQuantity()*p.getProductPrice());
        ans = ans + "合计：" + sum.toString() + "<br>";
        return ans;
    }

    public static String product_order(Integer x, Product p, Cart c)
    {
        String ans = "<fieldset><legend>商品"+x.toString()+"信息</legend>";
        ans = ans + "商品名称：" + p.getProductName() + "<br>";
        ans = ans + "数量：" + c.getQuantity().toString() + "&nbsp&nbsp&nbsp&nbsp";
        ans = ans + "单价：" + p.getProductPrice().toString() + "<br>";
        Integer sum = (c.getQuantity()*p.getProductPrice());
        ans = ans + "总价：" + sum.toString() + "<br>";
        return ans;
    }
}
