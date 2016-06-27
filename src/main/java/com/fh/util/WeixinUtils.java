package com.fh.util;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSON;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class WeixinUtils {
    private static Logger logger = Logger.getLogger(WeixinUtils.class);

   



    public static String nonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private static String generatePaySign(String pkg, String nonceStr, long timestamp) {
        Map<String, String> signDict = Maps.newHashMap();
        signDict.put("appId", Const.WX_APP_ID);
        signDict.put("timeStamp", String.valueOf(timestamp));
        signDict.put("nonceStr", nonceStr);
        signDict.put("package", pkg);
        signDict.put("signType", "MD5");

        List<String> keys = Lists.newArrayList(signDict.keySet());
        Collections.sort(keys);

        List<String> kv = Lists.newArrayList();
        for (String key : keys) {
            kv.add(key + "=" + signDict.get(key));
        }
        String str = Joiner.on("&").join(kv);
        String signTmp = str + "&key=" + Const.WX_PARTNER_KEY;
        return DigestUtils.md5Hex(signTmp);
    }
    private static String generateBillSign(String billDate, String nonceStr,String billType) {
        Map<String, String> signDict = Maps.newHashMap();
        signDict.put("appId", Const.WX_APP_ID);
        signDict.put("bill_date", billDate);
        signDict.put("bill_type", billType);
        signDict.put("mch_id", Const.WX_MCH_ID);
        signDict.put("nonce_str", nonceStr);


        List<String> keys = Lists.newArrayList(signDict.keySet());
        Collections.sort(keys);

        List<String> kv = Lists.newArrayList();
        for (String key : keys) {
            kv.add(key + "=" + signDict.get(key));
        }
        String str = Joiner.on("&").join(kv);
        String signTmp = str + "&key=" + Const.WX_PARTNER_KEY;
        return DigestUtils.md5Hex(signTmp);
    }

    private static String generateBillPackage(String billDate,  String nonceStr,String billType ) throws DocumentException {
        Map<String, String> pkg = Maps.newHashMap();
        pkg.put("appid", Const.WX_APP_ID);
        pkg.put("bill_date", billDate);
        pkg.put("bill_type", billType);
        pkg.put("mch_id", Const.WX_MCH_ID);
        pkg.put("nonce_str",nonceStr);

        List<String> keys = Lists.newArrayList(pkg.keySet());
        Collections.sort(keys);

        List<String> kv = Lists.newArrayList();
        for (String key : keys) {
            kv.add(key + "=" + pkg.get(key));
        }
        String str = Joiner.on("&").join(kv);
        String signTmp = str + "&key=" + Const.WX_PARTNER_KEY;
        String sign = DigestUtils.md5Hex(signTmp);
        pkg.put("sign", sign);

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("xml");

        root.addElement("appid").addText(pkg.get("appid"));
        root.addElement("bill_date").addText(pkg.get("bill_date"));
        root.addElement("bill_type").addText(pkg.get("bill_type"));
        root.addElement("mch_id").addText(pkg.get("mch_id"));
        root.addElement("nonce_str").addText(pkg.get("nonce_str"));
        root.addElement("sign").addText(pkg.get("sign"));

        String body = HttpRequest.post(Const.WX_BILL_URL).send(document.asXML()).body();
        logger.info(body);
 
        return null;
    }

    /**
     * Give body, parse result to `result` map
     *
     * @param body
     * @param result
     * @throws DocumentException
     */
    public static void parseWxMessage(String body, Map<String, String> result) throws DocumentException {
        Element root = DocumentHelper.parseText(body).getRootElement();
        for (Iterator i = root.elementIterator(); i.hasNext(); ) {
            Element element = (Element) i.next();
            result.put(element.getName(), element.getText());
        }
    }
	public static void main(String[] args) throws UnsupportedEncodingException, DocumentException {
		generateBillPackage("20160421",nonceStr(),"ALL");
	}


}
