package com.example.wifi_project;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "locationHistory", value = "/location_history")
public class locationHistory extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String key = request.getParameter("id");
//        System.out.println("&&&&&&&&&&&&&&&&&&&& : "+key);
        if(key != null){
            historyDto bean = new historyDto();
            wifiDao mdao = new wifiDao();

            bean.setID(Integer.parseInt(key));
            mdao.historyDelete(bean);
        }
        wifiDao mdao = new wifiDao();
        ArrayList<historyDto> list = mdao.getHistoryList();
        request.setAttribute("list",list);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/location_history.jsp"); // WebContent 안의 경로
        rd.forward(request, response);
    }
}

