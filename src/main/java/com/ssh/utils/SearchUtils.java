package com.ssh.utils;

import com.ssh.entity.Clazz;
import com.ssh.entity.Product;
import com.ssh.entity.Shop;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.List;

public class SearchUtils {
    //TODO:Translate list<class/product/shop> into html string.
    public static String search_result_product(List<Product> list)
    {
        String ans="";
        Iterator<Product> iterator = list.iterator();
        while(iterator.hasNext()) {
            Product product = iterator.next();
            ans = ans + "商品名：" + product.getProductName();
            ans = ans + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp价格：" + product.getProductPrice() + "<br>";
            //TODO:超链接到商品界面
            ans = ans + "<a href='product?id=" + product.getProductId() + "'>查看商品详情</a><br>";
        }
        return ans;
    }
    public static String search_result_shop(List<Shop> list)
    {
        String ans="";
        Iterator<Shop> iterator = list.iterator();
        while(iterator.hasNext()) {
            Shop shop = iterator.next();
            ans = ans + "商家名：" + shop.getShopName();
            ans = ans + "<br><a href='search.sp?id=" + shop.getShopId() + "'>查看该商家的所有商品</a><br>";
        }
        return ans;
    }
    public static String search_result_class(List<Clazz> list)
    {
        String ans="";
        Iterator<Clazz> iterator = list.iterator();
        while(iterator.hasNext()) {
            Clazz clazz = iterator.next();
            ans = ans + "商品种类名：" + clazz.getClassName();
            ans = ans + "<br><a href='search.cl?id=" + clazz.getClassId() + "'>查看该种类的所有商品</a><br>";
        }
        return ans;
    }
}
