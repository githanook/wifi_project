<%--
  Created by IntelliJ IDEA.
  User: 한욱
  Date: 2023-09-24
  Time: 오후 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<head>
    <style>
        #customers {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #customers tr:nth-child(even){background-color: #f2f2f2;}

        #customers tr:hover {background-color: #ddd;}

        #customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>


<h1>위치 히스토리 목록</h1>

<a href="hello-servlet">홈</a> | <a href="/location_history">위치 히스토리 목록</a> | <a href="/getOpenApiWifiInfo">Open API 와이파이 정보 가져오기</a> | <a href="/bookMark">북마크 보기</a> | <a href="/bookMarkManagement">북마크 그룹 관리</a>
</br>

<form name="formInfo" method="post" action="hello-servlet" >
    LAT: <input type="number" readonly id="LAT" name="LAT" value="${Lat}">, LNT: <input type="number" readonly id="LNT" name="LNT" value="${Lnt}"> <button type="button" name="myLocation" id="myLocation" onclick="getLocation()">내 위치 가져오기</button>  <button type="button" onclick="nearWifiInfo()">근처 WIFI 정보 보기</button>
</form>
<table id="customers">
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>

    <c:choose>
        <c:when test="${empty list}">
            <tr>
                <td colspan="4" ><div align="center">위치 히스토리가 존재하지 않습니다.</div></td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="bean" items="${list }">
                <tr>
                    <td>${bean.ID}</td>
                    <td>${bean.LAT}</td>
                    <td>${bean.LNT}</td>
                    <td>${bean.TIME}</td>
                    <form id="historyDelete" action="/location_history" method="post">
                        <td>
                            <input type="hidden" id="id" name="id" value="${bean.ID}">
                            <div align="center"><input type="submit" value="삭제"></div>
                        </td>

                    </form>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>




</table>

</body>
</html>


