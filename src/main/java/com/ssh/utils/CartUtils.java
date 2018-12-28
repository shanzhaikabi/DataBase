package com.ssh.utils;

import com.ssh.entity.Cart;
import com.ssh.entity.Product;

import java.util.Iterator;
import java.util.List;

public class CartUtils {
    public static String onecart(Product p, Cart c,Integer x)
    {
        String ans = OrderUtils.product_order(x,p,c);
        ans = ans + "<a href = \"editcart.do?cartId="+((Integer)c.getId()).toString()+ "&quantity=" +
                ((Integer)(c.getQuantity()+1)).toString() + "\">+1</a>&nbsp&nbsp&nbsp&nbsp";
        ans = ans + "<a href = \"editcart.do?cartId="+((Integer)c.getId()).toString()+ "&quantity=" +
                ((Integer)(c.getQuantity()-1)).toString() + "\">-1</a>";
        ans = ans + "<br><input type=\"checkbox\" name=\"cartId\" value =\" " + ((Integer)c.getId()).toString();
        if(c.getStatus().equals("yes"))
            ans = ans + "\"checked=\"checked";
        ans = ans + "\"/>选中商品";
        ans = ans + "</fieldset>";
        return ans;
    }

    public static String showsum(Integer sum,Integer discount)
    {
        String ans = "<fieldset>";
        ans = ans + "总价：" + sum.toString();
        ans = ans + "<br>已节省：" + discount.toString();
        ans = ans + "</fieldset>";
        return ans;
    }
    public static String showcart(List<Object[]> list)
    {
        String ans =  "<form action=\"commitcart.do\" method=\"POST\">";
        ans = ans + "<fieldset><legend>购物车信息</legend>";
        Integer sum = 0,cnt = 0;
        Iterator<Object[]>it = list.iterator();
        while(it.hasNext())
        {
            Object[] tmp = it.next();
            Product tmp1 = (Product)tmp[0];
            Cart tmp2 = (Cart)tmp[1];
            cnt++;
            ans = ans + onecart(tmp1,tmp2,cnt);
            if(tmp2.getStatus().equals("yes"))
                sum += tmp1.getProductPrice() * tmp2.getQuantity();
        }
        ans = ans + "</fieldset>";
        ans = ans + "<input type = \"submit\" value = \"下单\"/><br>";
        return ans;
    }
}
