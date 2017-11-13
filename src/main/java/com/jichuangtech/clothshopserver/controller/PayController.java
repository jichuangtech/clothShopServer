package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.Constant;
import com.jichuangtech.clothshopserver.constant.PayConstant;
import com.jichuangtech.clothshopserver.constant.ResponseCode;
import com.jichuangtech.clothshopserver.model.PayInfo;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.service.SessionService;
import com.jichuangtech.clothshopserver.utils.IpUtils;
import com.jichuangtech.clothshopserver.utils.StringUtils;
import com.jichuangtech.clothshopserver.weixin.PayUtil;
import com.jichuangtech.clothshopserver.weixin.WxPayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bingo on 2017/11/12.
 */

@RestController
@RequestMapping(PayConstant.API_PAY)
public class PayController {
    private Logger LOGGER = LoggerFactory.getLogger(Constant.MODULE_NAME);

    @Autowired
    private SessionService sessionService;

    /**
     * @Description: 发起微信支付
     * @param request
     */
    @RequestMapping(method = RequestMethod.GET)
    public Response<PayInfo> wxPay(@RequestHeader("access_token") String accessToken, HttpServletRequest request){
        String openid = sessionService.get(accessToken);
        Response<PayInfo> response = new Response<>();
        try{
            //生成的随机字符串
            String nonce_str = StringUtils.getRandomStringByLength(32);
            //商品名称
            String body = "金凤针织商品购买测试";
            //获取客户端的ip地址
            String spbill_create_ip = IpUtils.getIpAddr(request);
            String orderSn = "123456";
            //人民币 分为单位
            int totalFee = 1;

            //组装参数，用户生成统一下单接口的签名
            Map<String, String> packageParams = new HashMap<>();
            packageParams.put("appid", WxPayConfig.appid);
            packageParams.put("mch_id", WxPayConfig.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            packageParams.put("out_trade_no", orderSn);//商户订单号
            packageParams.put("total_fee", String.valueOf(totalFee));//支付金额，这边需要转成字符串类型，否则后面的签名会失败
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", WxPayConfig.NOTIFY_URL);//支付成功后的回调地址
            packageParams.put("trade_type", WxPayConfig.TRADE_TYPE);//支付方式
            packageParams.put("openid", openid);

            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = PayUtil.sign(prestr, WxPayConfig.key, "utf-8").toUpperCase();

            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml>" + "<appid>" + WxPayConfig.appid + "</appid>"
                    + "<body><![CDATA[" + body + "]]></body>"
                    + "<mch_id>" + WxPayConfig.mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + WxPayConfig.NOTIFY_URL + "</notify_url>"
                    + "<openid>" + openid + "</openid>"
                    + "<out_trade_no>" + orderSn + "</out_trade_no>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + totalFee + "</total_fee>"
                    + "<trade_type>" + WxPayConfig.TRADE_TYPE + "</trade_type>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";

            System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);

            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(WxPayConfig.PAY_URL, "POST", xml);

            System.out.println("调试模式_统一下单接口 返回XML数据：" + result);

            // 将解析结果存储在HashMap中
            Map map = PayUtil.doXMLParse(result);

            String return_code = (String) map.get("return_code");//返回状态码
            String return_msg = (String) map.get("return_msg");//返回状态信息
            LOGGER.info("pref_pay result return_code: " + return_code + ", return_msg: " + return_msg);
            //返回给小程序端需要的参数
            if("SUCCESS".equals(return_code)){
                PayInfo payInfo = new PayInfo();
                response.data = payInfo;
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
                payInfo.nonceStr = nonce_str;
                payInfo.packageStr = "prepay_id=" + prepay_id;
                Long timeStamp = System.currentTimeMillis() / 1000;
                //这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
                payInfo.timeStamp = String.valueOf(timeStamp);
                //拼接签名需要的参数
                String stringSignTemp = "appId=" + WxPayConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id
                        + "&signType=" + WxPayConfig.SIGN_TYPE + "&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtil.sign(stringSignTemp, WxPayConfig.key, "utf-8").toUpperCase();
                payInfo.paySign = paySign;
            } else if("FAIL".equals(return_code)) {
                response.setStatusCode(ResponseCode.CODE_PREF_PAY_ERROR);
                response.msg = response.msg + "_" + return_msg;
            }
        }catch(Exception e){
            response.setStatusCode(ResponseCode.CODE_PREF_PAY_ERROR);
            LOGGER.error(" pref pay error msg: " + e.getMessage());
            e.printStackTrace();
        } finally {
            LOGGER.error(" pref pay response: " + response);
        }

        return response;
    }

    /**
     * @Description:微信支付成功回调信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value= PayConstant.NOTIFY)
    @ResponseBody
    public void wxNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine()) != null){
            sb.append(line);
        }
        //sb为微信返回的xml
        String notifyXml = sb.toString();
        String resXml = "";
        LOGGER.info("接收到的报文：" + notifyXml);

        Map map = PayUtil.doXMLParse(notifyXml);

        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            //验证签名是否正确
            if(PayUtil.verify(PayUtil.createLinkString(map), (String)map.get("sign"), WxPayConfig.key, "utf-8")){
                /**此处添加自己的业务逻辑代码start**/
                // TODO: 2017/11/12

                /**此处添加自己的业务逻辑代码end**/
                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }
        }else{
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        LOGGER.info("wxNotify: " + resXml);
        LOGGER.info("wxNotify:  微信支付回调数据结束");


        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }

}
