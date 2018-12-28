package com.ssh.utils;

import com.ssh.entity.Clazz;
import com.ssh.entity.Discount;
import com.ssh.entity.Product;

import java.util.Iterator;
import java.util.List;

public class ShopUtils {
    public static String shop_discount(List<Discount>list)
    {
        String ans = "<fieldset>";
        ans = ans + "<a href=\"editdiscount?id=\">添加新优惠券</a><br>";
        Iterator<Discount>it = list.iterator();
        while(it.hasNext())
        {
            ans = ans + DiscountUtils.shop_discount_simple(it.next());
        }
        ans = ans + "</fieldset>";
        return ans;
    }
    public static String shop_product(List<Object[]>list)
    {
        String ans = "<fieldset>";
        ans = ans + "<a href=\"editproduct?id=\">添加新商品</a><br>";
        Iterator<Object[]>it = list.iterator();
        while(it.hasNext())
        {
            Object[] tmp = it.next();
            ans = ans + ProductUtils.shop_product_simple((Product) tmp[0],(Clazz)tmp[1]);
        }
        ans = ans + "</fieldset>";
        return ans;
    }
    public  static String shop_class(List<Clazz>list)
    {
        String ans = "商品类别：<select name=classId>";
        Iterator<Clazz> it = list.iterator();
        while(it.hasNext())
        {
            Clazz tmp = it.next();
            ans = ans + "<option value=\"" + tmp.getClassId() + "\">" + tmp.getClassName() + "</option>";
        }
        ans = ans + "</select><br><input type = \"submit\" value = \"提交\">";
        return ans;
    }
    public static String shop_one_product(Product p)
    {
        String ans = "";
        if (p.getProductName() != null)
            ans = ans + "商品名称：" + "<input type=\"text\" name=\"productName\" value=\"" + p.getProductName() +"\">" ;
        else{
            ans = ans + "商品名称：" + "<input type=\"text\" name=\"productName\">" ;
        }
        ans = ans + "<input type=\"hidden\" name=\"productId\" value=\"" + p.getProductId() +"\"><br>" ;
        if (p.getProductPrice() != null)
            ans = ans + "商品价格：" + "<input type=\"text\" name=\"productPrice\" value=\"" + p.getProductPrice().toString() +"\">" ;
        else{
            ans = ans + "商品价格：" + "<input type=\"text\" name=\"productPrice\">" ;
        }
        ans = ans + "<input type=\"hidden\" name=\"shopId\" value=\"" + p.getShopId() +"\"><br>" ;
        if (p.getProductStock() != null)
            ans = ans + "商品库存：" + "<input type=\"text\" name=\"productStock\" value=\"" + p.getProductStock().toString() +"\"><br>" ;
        else{
            ans = ans + "商品库存：" + "<input type=\"text\" name=\"productStock\"><br>" ;
        }
        return ans;
    }
}
