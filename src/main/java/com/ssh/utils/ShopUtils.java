package com.ssh.utils;

import com.ssh.entity.Clazz;
import com.ssh.entity.Discount;
import com.ssh.entity.Product;

import java.util.Iterator;
import java.util.List;

public class ShopUtils {
    public static String shop_discount_shop(List<Discount>list)
    {
        String ans = "<fieldset><legend>商店优惠券</legend>";
        ans = ans + "<a href=\"editdiscount?id=-1\">添加新商店优惠券</a><br><br>";
        Iterator<Discount>it = list.iterator();
        while(it.hasNext())
        {
            ans = ans + DiscountUtils.shop_discount_simple(it.next());
        }
        ans = ans + "</fieldset>";
        return ans;
    }
    public static String shop_discount_set_product(List<Object[]>list)
    {
        String ans = "<fieldset><legend>商品优惠券</legend>";
        ans = ans + "<a href=\"editdiscount?id=-2\">添加新商品优惠券</a><br><br>";
        Iterator<Object[]>it = list.iterator();
        while(it.hasNext())
        {
            Object[] tmp = it.next();
            ans = ans + DiscountUtils.shop_discount_simple((Discount) tmp[0],(List<Product>)tmp[1]);
        }
        ans = ans + "</fieldset>";
        return ans;
    }
    public static String shop_one_discount(Discount d)
    {
        String ans = "<fieldset>";
        if(d.getDiscountPrice()!=null)
        {
            ans = ans + "满减金额：" + "<input type=\"text\" name=\"discountPrice\" value=\"" + d.getDiscountPrice().toString() +"\">";
        }
        else
        {
            ans = ans + "满减金额：" + "<input type=\"text\" name=\"discountPrice\">";
        }
        ans = ans + "<input type=\"hidden\" name=\"discountType\" value=\"" + d.getDiscountType() +"\"><br>" ;
        if(d.getDiscountLeast()!=null)
        {
            ans = ans + "满减上限：" + "<input type=\"text\" name=\"discountLeast\" value=\"" + d.getDiscountLeast().toString() +"\">";
        }
        else
        {
            ans = ans + "满减上限：" + "<input type=\"text\" name=\"discountLeast\">";
        }
        ans = ans + "<input type=\"hidden\" name=\"discountRule\" value=\"" + d.getDiscountRule() +"\"><br>" ;
        if(d.getDiscountRule().equals("shop"))
            ans = ans + "<br><input type = \"submit\" value = \"提交\"></fieldset>";
        return ans;
    }
    public static String shop_discount_product(List<Product>list,List<Product> haveList)
    {
        String ans = "商品类别：";
        Iterator<Product> it = list.iterator();
        while(it.hasNext())
        {
            Product tmp = it.next();
            ans = ans + "<input type=\"checkbox\" name=\"productId\"  value=\""
                    + tmp.getProductId();
            if (haveList.contains(tmp)) ans = ans + "\"checked=\"checked";
            ans = ans + "\">" + tmp.getProductName() + "</input>";
        }
        ans = ans + "<br><input type = \"submit\" value = \"提交\"></fieldset>";
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
        ans = ans + "</select><br><input type = \"submit\" value = \"提交\"></fieldset>";
        return ans;
    }
    public static String shop_one_product(Product p)
    {
        String ans = "<fieldset>";
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
