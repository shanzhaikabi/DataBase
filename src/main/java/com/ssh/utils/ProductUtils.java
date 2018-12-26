package com.ssh.utils;

import com.ssh.entity.Clazz;
import com.ssh.entity.Discount;
import com.ssh.entity.Product;
import com.ssh.entity.Shop;

import java.util.Iterator;
import java.util.List;
import static com.ssh.utils.DiscountUtils.discount_detail;

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
}
