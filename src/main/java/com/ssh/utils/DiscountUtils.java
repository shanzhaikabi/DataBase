package com.ssh.utils;

import com.ssh.entity.Discount;
import com.ssh.entity.Discountdetail;
import com.ssh.entity.Product;

import java.util.Iterator;
import java.util.List;

public class DiscountUtils {
    public static String mydiscount_haveList(List<Object[]>list)
    {
        String ans = "<fieldset>";
        ans = ans + "<legend>可用优惠券</legend>";
        Iterator<Object[]> iterator = list.iterator();
        while(iterator.hasNext()) {
            ans = ans + discount_detail((Discount)iterator.next()[0],"have");
        }
        ans = ans + "</fieldset>";
        return ans;
    }
    public static String mydiscount_used(List<Object[]>list)
    {
        String ans = "<fieldset>";
        ans = ans + "<legend>已使用优惠券</legend>";
        Iterator<Object[]> iterator = list.iterator();
        while(iterator.hasNext()) {
            Object[] tmp = iterator.next();
            ans = ans + discount_detail((Discount) tmp[0],"used");
            ans = ans + "使用日期：" + ((Discountdetail)tmp[1]).getDiscountDate() + "<br>";
            ans = ans + "使用状态：" + ((Discountdetail)tmp[1]).getDiscountStatus() + "<br>";
            ans = ans + "</fieldset>";
        }
        ans = ans + "</fieldset>";
        return ans;
    }
    public static String discount_for_product(List<Discount> candis, List<Discount>havedis)
    {
        //candis
        String ans = "<fieldset>";
        ans = ans + "<legend>可用优惠券</legend>";
        String status;
        if(havedis==null)status="login";
        else status = "no";
        Iterator<Discount> iterator = candis.iterator();
        while(iterator.hasNext()) {
            ans = ans + discount_detail(iterator.next(),status);
        }
        ans = ans + "</fieldset>";
        //havedis
        ans = ans + "<fieldset>";
        ans = ans + "<legend>已有优惠券</legend>";
        if(havedis==null){
            ans = ans + "请登录后查看已拥有的优惠券";
        }
        else {
            iterator = havedis.iterator();
            while (iterator.hasNext()) {
                ans = ans +discount_detail(iterator.next(),"");
            }
        }
        ans = ans + "</fieldset>";
        return ans;
    }

    public static  String discount_detail(Discount discount, String str){
        String ans = "<fieldset><legend>优惠券信息</legend>";
        ans = ans + "优惠券类型：";
        String rule = discount.getDiscountRule();
        if(rule.equals("shop"))ans = ans + "商店专用优惠券";
        else if(rule.equals("class"))ans = ans + "种类专用优惠券";
        else ans = ans + "商品专用优惠券";
        ans = ans + "<br>";
        if(discount.getDiscountLeast()==0)
            ans = ans + "无门槛优惠券：" + discount.getDiscountPrice().toString() + "&nbsp元<br>";
        else
            ans = ans + "满&nbsp" + discount.getDiscountLeast().toString() +
                    "&nbsp减&nbsp" + discount.getDiscountPrice().toString() + "&nbsp元<br>";
        if(str.equals("yes"))ans = ans + "已拥有该优惠券<br>";
        //TODO:超链接到 领优惠券 和 登 录
        else if(str.equals("no"))ans = ans + "<a href = \"receivediscount?id=" + discount.getDiscountType() +
                "\">立即领取</a><br>"+ "</fieldset>";
        else if(str.equals("login"))ans = ans + "请先登录<br>"+ "</fieldset>";
        else if(str.equals("used"));
        else if(str.equals("have")) ans = ans +"</fieldset>";
        else ans = ans + "</fieldset>";
        return ans;
    }
}
