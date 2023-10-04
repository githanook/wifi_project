package com.example.wifi_project;

import com.google.gson.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    static final String KEY = "";
    static int TOCALCNT;
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int v1 = (int) (Math.random() * 100) + 1;
//        int v2 = (int) (Math.random() * 100) + 1;
//        int result = v1 + v2;
//
//        request.setAttribute("v1", v1);
//        request.setAttribute("v2", v2);
//        request.setAttribute("result", result);

        // 위도 경도 정보 안찍혔을때

        String lat = request.getParameter("LAT");
        String lnt = request.getParameter("LNT");
        System.out.println(lat);
        System.out.println(lnt);

        if(lat != null){
            //히스토리 테이블에 insert 하기
            historyDto bean = new historyDto();
            wifiDao mdao = new wifiDao();

            bean.setLAT(Double.parseDouble(lat));
            bean.setLNT(Double.parseDouble(lnt));
            mdao.historyInsert(bean);

            //jsp에 리스트 보내기
            ArrayList<wifiDto> list = mdao.getDistanceList(Double.parseDouble(lat),Double.parseDouble(lnt));
            System.out.println(list);
            request.setAttribute("list",list);
             request.setAttribute("Lat",lat);
             request.setAttribute("Lnt",lnt);
        }
        // 위도 경도 정보 있을때


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp"); // WebContent 안의 경로
        rd.forward(request, response);
    }
}

