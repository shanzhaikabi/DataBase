package com.ssh.utils;

import com.ssh.entity.Clazz;
import com.ssh.entity.Product;

import java.util.Iterator;
import java.util.List;

public class ShopUtils {
    public static String shop_product(List<Object[]>list)
    {
        String ans = "<fieldset>";
        Iterator<Object[]>it = list.iterator();
        while(it.hasNext())
        {
            Object[] tmp = it.next();
            ans = ans + ProductUtils.shop_product_simple((Product) tmp[0],(Clazz)tmp[1]);
        }
        ans = ans + "</fieldset>";
        return ans;
    }
    public static  String edit_product(Product p,String s)
    {
        String ans = "<form action=\"editproduct.do\" method=\"POST\"><fieldset><legend>修改商品信息</legend>";
        ans = ans + shop_one_product(p);
        ans = ans + s;
        ans = ans + "<input type = \"submit\" value =\"修改\">";
        ans = ans + "</fieldset></form>";
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
        ans = ans + "</select>";
        return ans;
    }
    public static String shop_one_product(Product p)
    {
        String ans ="商品名称：" + "<input type=\"text\" name=\"productName\" value=\"" + p.getProductName() +"\">" ;
        ans = ans + "<input type=\"hidden\" name=\"productId\" value=\"" + p.getProductId() +"\"><br>" ;
        ans = ans + "商品价格：" + "<input type=\"text\" name=\"productPrice\" value=\"" + p.getProductPrice().toString() +"\">" ;
        ans = ans + "<input type=\"hidden\" name=\"shopId\" value=\"" + p.getShopId() +"\"><br>" ;
        ans = ans + "商品库存：" + "<input type=\"text\" name=\"productName\" value=\"" + p.getProductStock().toString() +"\"><br>" ;
        return ans;
    }
}
