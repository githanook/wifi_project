package com.example.wifi_project;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "wifiDetail", value = "/wifiDetail")
public class wifiDetail extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String detailId = request.getParameter("detailId");
        String bookmarkGroupid = request.getParameter("bookmarkGroupid");
        System.out.println(detailId);
        System.out.println(bookmarkGroupid);

        if(bookmarkGroupid != null){
            wifiDao mdao = new wifiDao();
            bookMarkDto bean = new bookMarkDto();
            bean.setWifidetailid(Integer.parseInt(detailId));
            bean.setID(Integer.parseInt(bookmarkGroupid));
            int a = mdao.updatebookmarkwifidetail(bean);
            if(a>0){
                System.out.println("북마크 추가 완료");
            }else{
                System.out.println("북마크 추가 실패");
            }
        }


        wifiDto bean = new wifiDto();
        wifiDao mdao = new wifiDao();
            wifiDto data = mdao.wifiDetail(Integer.parseInt(detailId));
            request.setAttribute("data",data);
        ArrayList<bookMarkDto> list = mdao.getBookMarkList();
        request.setAttribute("list",list);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailWifi.jsp"); // WebContent 안의 경로
        rd.forward(request, response);
    }
}
