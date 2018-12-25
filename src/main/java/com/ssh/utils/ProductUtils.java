package com.ssh.utils;

import com.ssh.entity.Clazz;
import com.ssh.entity.Discount;
import com.ssh.entity.Product;
import com.ssh.entity.Shop;

import java.util.Iterator;
import java.util.List;

public class ProductUtils {
    public static String detail_result_product(Product product, Clazz clazz, Shop shop,
                                               List<Discount> candis, List<Discount>havedis){
        //detail
        String ans = "<fieldset>";
        ans = ans + "<legend>商品详情</legend>";
        ans = ans + "商品名称：" + product.getProductName() + "<br>";
        ans = ans + "商家：" + "<a href='search.sp?id=" + shop.getShopId() + "'>" + shop.getShopName() + "</a><br>";
        ans = ans + "商品类别：" + "<a href='search.cl?id=" + clazz.getClassId() + "'>"+ clazz.getClassName() +"</a><br>";
        ans = ans + "价格：" + product.getProductPrice() + "<br>";
        ans = ans + "库存：" + product.getProductStock() + "<br>";
        ans = ans + "</fieldset>";
        //candis
        ans = ans + "<fieldset>";
        ans = ans + "<legend>可用优惠券</legend>";
        System.out.println(candis);
        Iterator<Discount> iterator = candis.iterator();
        while(iterator.hasNext()) {
            ans = ans + "<fieldset>";
            Discount tmp = iterator.next();
            if(tmp.getDiscountLeast()==0)
                ans = ans + "无门槛优惠券：" + tmp.getDiscountPrice().toString() + "&nbsp元<br>";
            else
                ans = ans + "满&nbsp" + tmp.getDiscountLeast().toString() +
                        "&nbsp减&nbsp" + tmp.getDiscountPrice().toString() + "&nbsp元<br>";
            //TODO:搜索优惠券适用的所有商品
            if(tmp.getDiscountType().equals("shop"))
            {

            }
            else if(tmp.getDiscountType().equals("class"))
            {

            }
            else
            {

            }
            //TODO:领取优惠券
            ans = ans + "<input type='submit' value='领取优惠券'>";
            ans = ans + "</fieldset>";
        }
        ans = ans + "</fieldset>";
        //havedis
        ans = ans + "<fieldset>";
        ans = ans + "<legend>已拥有优惠券</legend>";
        if(havedis==null){
            ans = ans + "请登录后查看已拥有的优惠券";
        }
        else {
            iterator = havedis.iterator();
            while (iterator.hasNext()) {
                ans = ans + "<fieldset>";
                Discount tmp = iterator.next();
                if (tmp.getDiscountLeast() == 0)
                    ans = ans + "无门槛优惠券：" + tmp.getDiscountPrice().toString() + "&nbsp元<br>";
                else
                    ans = ans + "满&nbsp" + tmp.getDiscountLeast().toString() +
                            "&nbsp减&nbsp" + tmp.getDiscountPrice().toString() + "&nbsp元<br>";
                //TODO:搜索优惠券适用的所有商品
                if (tmp.getDiscountType().equals("shop")) {

                } else if (tmp.getDiscountType().equals("class")) {

                } else {

                }
                ans = ans + "</fieldset>";
            }
        }
        ans = ans + "</fieldset>";
        ans = ans + "<input type=\"submit\" value=\"加入购物车\">";
        return ans;
    }
}
