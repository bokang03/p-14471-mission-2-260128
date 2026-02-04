package com.ll;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Rq {

    private String action;
    private Map<String, String> paramMap;

    public Rq(String cmd){

        paramMap = new HashMap<>();

        String[] cmdBits =cmd.split("\\?"); // 문자열 "?"를 사용할 때 \\를 사용해야함
        action = cmdBits[0];
        String params = cmdBits.length > 1 ? cmdBits[1] : "";

        String[] paramsBits = params.split(("&"));
//        for (String param : paramsBits){
//            String[] paramBits = param.split("=");
//            String key = paramBits[0];
//            String value = paramBits.length > 1 ? paramBits[1] : "";
//
//            paramMap.put(key, value);
//        }

        paramMap = Arrays.stream(paramsBits) // ["id=1", "name=aaa", "age=20"]
                .map((param)-> param.split("=")) // "id=1"
                .filter((paramBits) -> paramBits.length == 2
                        && paramBits[0] != null && paramBits[1] != null)  // ["id", "1"]
                .collect(Collectors.toMap(
                        bits -> bits[0],
                        bits -> bits[1]
                ));

    }

    public String getAction(){
        return action;
    }

    public String getParam(String key){
        return paramMap.get(key);
    }

    public int getParamAsInt(String key, int defaltvalue){
        try {
            return Integer.parseInt(paramMap.get(key));
        }   catch (NumberFormatException e){
            return defaltvalue;
        }
    }
}
