package com.cloud.service.jwt;

import com.google.gson.Gson;

public class JSONResult {

    public static String fillResultString(Integer status, String message, Object result){

        Gson gson = new Gson();
        ResultModel resultModel=new ResultModel();
        resultModel.setStatus(status);
        resultModel.setMessage(message);
        resultModel.setResult(result);

//        JSONObject jsonObject = new JSONObject(){{
//            this.put("status", status);
//            put("message", message);
//            put("result", result);
//        }};


        return gson.toJson(resultModel);
    }
}
