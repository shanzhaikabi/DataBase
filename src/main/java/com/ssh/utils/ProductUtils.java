package com.ssh.utils;

import com.ssh.entity.Clazz;
import com.ssh.entity.Product;
import com.ssh.entity.Shop;


public class ProductUtils {
    public static String product_detail(Product product, Clazz clazz, Shop shop)
    {
        String ans = "<fieldset>";
        ans = ans + "<legend>商品详情</legend>";
        ans = ans + "商品名称：" + product.getProductName() + "<br>";
        ans = ans + "商家：" + "<a class=\"tooltip\" href='search.sp?id=" + shop.getShopId() + "'>" + shop.getShopName()
                +"<span class=\"tooltiptext\">搜索该商家的所有商品</span>" + "</a><br>";
        ans = ans + "商品类别：" + "<a class=\"tooltip\" href='search.cl?id=" + clazz.getClassId() + "'>"+ clazz.getClassName()
                + " <span class=\"tooltiptext\">搜索该类别的所有商品</span> " + "</a><br>";
        ans = ans + "价格：" + product.getProductPrice() + "<br>";
        ans = ans + "库存：" + product.getProductStock() + "<br>";
        ans = ans + "</fieldset>";
        return ans;
    }
    public static String shop_product_simple(Product p,Clazz c)
    {
        String ans = "<fieldset>";
        ans = ans + "商品名称：" + p.getProductName() + "<br>";
        ans = ans + "商品类别：" + c.getClassName() + "<br>";
        ans = ans + "价格：" + p.getProductPrice() + "<br>";
        ans = ans + "库存：" + p.getProductStock() + "<br>";
        ans = ans + "<a href='editproduct?id=\"" + p.getProductId() + "\">修改商品信息</a><br>";
        ans = ans + "</fieldset>";
        return ans;
    }
    public static String product_into_cart(Product product)
    {
        String ans;
        if(product.getProductStock()==0)ans = "该商品已售罄！";
        else ans =  "<input type=\"number\" name=\"quantity\" value=\"1\" min=\"1\" max=\"" +
                (product.getProductStock()).toString() + "\" />"+
                "<input type=\"submit\" value=\"加入购物车\">";
        return ans+"<input type=\"hidden\" name=\"productId\" value=\""+ product.getProductId() + "\">";
    }
}
