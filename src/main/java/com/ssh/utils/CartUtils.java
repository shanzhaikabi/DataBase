package com.ssh.utils;

import com.ssh.entity.Cart;
import com.ssh.entity.Product;

import java.util.Iterator;
import java.util.List;

public class CartUtils {
    public static String onecart(Product p, Cart c,Integer x)
    {
        String ans = OrderUtils.product_order(x,p,c);
        ans = ans + "<form action = \"editcart.do\" method=\"GET\">" +
                "<input type=\"number\" name=\"quantity\" value=\"" +
            c.getQuantity().toString() + "\" min=\"0\" max=\"" +(p.getProductStock()).toString() + "\" />";
        ans = ans + "<input type = \"submit\" value = \"修改\"/>" +
                "<input type=\"hidden\" name=\"cartId\" value=\""+ ((Integer)c.getId()).toString() + "\">"
                +"</form>";
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
        String ans = "<fieldset><legend>购物车信息</legend>";
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
        ans = ans + "<br>" +
                "<form action=\"commitcart.do\" method=\"POST\"><input type = \"submit\" value = \"下单\"/>";
        return ans;
    }
}
