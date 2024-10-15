package com.mobilebackendfinancials.financial.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;


@Slf4j
public class HeaderUtils {

    public static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }

        }
        return remoteAddr;
    }


    public static String getAffiliateValue(String affiliateCode, HttpServletRequest request) {

        return (HeaderUtils.stringIsBlank(request.getHeader("xAffiliate"))) ? "eng" : request.getHeader("xAffiliate");
    }


    public static boolean stringIsBlank(String text) {

        return text == null || text.isEmpty();
    }


    public static String getMessageFromOracleDb(String message) {

        if (stringIsBlank(message))
            return "Operation Failed!";

        if (!message.contains(":"))
            return message;

        return message.substring(message.lastIndexOf(":") + 1).trim();
    }


    public static boolean isValidDbResponse(String code) {

        return StringUtils.isBlank(code) || code.equalsIgnoreCase("0") || code.equalsIgnoreCase("00") || code.equalsIgnoreCase("000");
    }


}
