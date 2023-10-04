package com.example.wifi_project;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "bookmark", value = "/bookMark")
public class bookmark extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {

        wifiDao mdao = new wifiDao();
        String key = request.getParameter("deletebuttonId");
        System.out.println(key);
        if(key != null){
            bookmarkjoinDto bean = new bookmarkjoinDto();
            int a = mdao.deletebookmark(Integer.parseInt(key));
            if(a > 0){
                System.out.println("삭제 성공");
            }else{
                System.out.println("삭제 실패");
            }
        }
        ArrayList<bookmarkjoinDto> list = mdao.bookmarklist();
        request.setAttribute("list",list);

        RequestDispatcher rr = request.getRequestDispatcher("/WEB-INF/jsp/bookmark.jsp"); // WebContent 안의 경로
        rr.forward(request, response);
    }
}
