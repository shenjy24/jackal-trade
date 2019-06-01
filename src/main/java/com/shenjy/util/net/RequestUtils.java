package com.shenjy.util.net;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * 请求工具类
 *
 * @author shenjy 2019/03/22
 */
public class RequestUtils {

    /**
     * 从request中读取数据
     *
     * @param request
     * @return
     */
    public static String getBodyByRequest(HttpServletRequest request) {
        InputStream inputStream;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            inputStream = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String str = "";
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
        } catch (IOException e) {
            //
        }
        return stringBuffer.toString();
    }

    /**
     * 从request中读取参数
     *
     * @param request
     * @return
     */
    public static Map<String, String> getParamByRequest(HttpServletRequest request) {
        Map<String, String> data = Maps.newHashMap();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue()[0];
            data.put(key, value);
        }
        return data;
    }
}
