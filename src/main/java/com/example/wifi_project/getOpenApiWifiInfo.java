package com.example.wifi_project;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;

import okhttp3.OkHttpClient;
import com.google.gson.*;
import com.google.gson.JsonParser;
import okhttp3.Request;
import okhttp3.Response;

@WebServlet(name = "getOpenApiWifiInfo", value = "/getOpenApiWifiInfo")
public class getOpenApiWifiInfo extends HttpServlet {
    private String message;
    static int TOTALCNT;
    String url = "http://openapi.seoul.go.kr:8088/71445a4d4968616e3133367344645563/json/TbPublicWifiInfo";
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        request.setAttribute("totalCount",AddWifi());;


        RequestDispatcher rr = request.getRequestDispatcher("/WEB-INF/jsp/getOpenApiWifiInfo.jsp"); // WebContent 안의 경로
        rr.forward(request, response);

    }
    public static int totalCnt() throws JsonParseException {
        URL url = null;
        HttpURLConnection con= null;
        JsonObject result = null;
        StringBuilder sb = new StringBuilder();
        int start = 1;
        int end = 1;
        String baseUrl = "http://openapi.seoul.go.kr:8088/" + "71445a4d4968616e3133367344645563" + "/" +
                "json/TbPublicWifiInfo/"+ start + "/"+end+"/";

        try {
            url = new URL(baseUrl);
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            con.setRequestProperty("Content-type", "application/json");

            con.setDoOutput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            while(br.ready()) {
                sb.append(br.readLine());
            }
            con.disconnect();
        }catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
        result = (JsonObject) new JsonParser().parse(sb.toString());

        StringBuilder out = new StringBuilder();

        JsonObject data = (JsonObject) result.get("TbPublicWifiInfo");
        System.out.println(data);
        JsonArray jsonArray = data.getAsJsonArray("row");
        for(JsonElement j :jsonArray){
//            out.println(j.getAsJsonObject().get("X_SWIFI_WRDOFC"));
            System.out.println(j.getAsJsonObject().get("X_SWIFI_WRDOFC"));
        }
        int totalGet = Integer.parseInt( data.get("list_total_count").toString());
        System.out.println("(((((((((((("+totalGet);

        return totalGet;
    }
    public static int AddWifi() throws NullPointerException{
        int start = 0;
        int end = 0;
        int total = 0;

        TOTALCNT = totalCnt();
        int pageEnd = TOTALCNT / 1000;
        int pageEndRemain = TOTALCNT % 1000;

        for (int k = 0; k <= pageEnd; k++) {
            start = (int) Math.pow(10, 3) * k + 1;

            if(k == pageEnd){
                end = start + pageEndRemain - 1;
            }
            else{
                end = (int) Math.pow(10, 3) * (k+1) ;
            }

            String baseUrl = "http://openapi.seoul.go.kr:8088/" + "71445a4d4968616e3133367344645563" + "/" +
                    "json/TbPublicWifiInfo/";

            baseUrl = baseUrl + start + "/" + end + "/";

            URL url = null;
            HttpURLConnection con= null;
            JsonObject result = null;
            StringBuilder sb = new StringBuilder();


            try {
                url = new URL(baseUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-type", "application/json");
                con.setDoOutput(true);


                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                while(br.ready()) {
                    sb.append(br.readLine());
                }
                con.disconnect();
            }catch(Exception e) {
                e.printStackTrace();
            }

            result = (JsonObject) new JsonParser().parse(sb.toString());
//            System.out.println("################"+result);
            JsonObject data = (JsonObject) result.get("TbPublicWifiInfo");
            JsonArray array = (JsonArray) data.get("row");
//            System.out.println("!!!!!!! :"+array.size());

            JsonObject tmp;

            for(int i = 0; i<array.size(); i++) {
                tmp = (JsonObject) array.get(i);

                wifiDto bean = new wifiDto();
                wifiDao mdao = new wifiDao();
                bean.setX_SWIFI_MGR_NO(tmp.get("X_SWIFI_MGR_NO").toString());
                bean.setX_SWIFI_WRDOFC(tmp.get("X_SWIFI_WRDOFC").toString());
                bean.setX_SWIFI_MAIN_NM(tmp.get("X_SWIFI_MAIN_NM").toString());
                bean.setX_SWIFI_ADRES1(tmp.get("X_SWIFI_ADRES1").toString());
                bean.setX_SWIFI_ADRES2(tmp.get("X_SWIFI_ADRES2").toString());
                bean.setX_SWIFI_INSTL_FLOOR(tmp.get("X_SWIFI_INSTL_FLOOR").toString());
                bean.setX_SWIFI_INSTL_TY(tmp.get("X_SWIFI_INSTL_TY").toString());
                bean.setX_SWIFI_INSTL_MBY(tmp.get("X_SWIFI_INSTL_MBY").toString());
                bean.setX_SWIFI_SVC_SE(tmp.get("X_SWIFI_SVC_SE").toString());
                bean.setX_SWIFI_CMCWR(tmp.get("X_SWIFI_CMCWR").toString());
                bean.setX_SWIFI_MGR_NO(tmp.get("X_SWIFI_MGR_NO").toString());
                bean.setX_SWIFI_CNSTC_YEAR(tmp.get("X_SWIFI_CNSTC_YEAR").toString());
                bean.setX_SWIFI_INOUT_DOOR(tmp.get("X_SWIFI_INOUT_DOOR").toString());
                bean.setX_SWIFI_REMARS3(tmp.get("X_SWIFI_REMARS3").toString());
                bean.setLAT(tmp.get("LAT").getAsDouble());
                bean.setLNT(tmp.get("LNT").getAsDouble());
                bean.setWORK_DTTM(tmp.get("WORK_DTTM").toString());

                mdao.wifiInsert(bean);
//                System.out.println("@@@@@"+tmp.get("X_SWIFI_MGR_NO").toString()); // 가져오고자 하는 인자를 작성하면 됨.

                total++;
            }
        }
        return total;
    }
}





