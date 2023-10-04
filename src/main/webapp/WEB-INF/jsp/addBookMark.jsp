<%--
  Created by IntelliJ IDEA.
  User: 한욱
  Date: 2023-10-03
  Time: 오후 4:58
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Title</title>
</head>
<body>

<h1>북마크 그룹 추가</h1>

<a href="hello-servlet">홈</a> | <a href="/location_history">위치 히스토리 목록</a> | <a href="/getOpenApiWifiInfo">Open API 와이파이 정보 가져오기</a> | <a href="/bookMark">북마크 보기</a> | <a href="/bookMarkManagement">북마크 그룹 관리</a>
</br>
<form id="addBookMarkForm" name="addBookMarkForm" action="bookMarkManagement" method="post">
    <table id="customers">
        <tr>
            <th>북마크 이름</th>
            <td>
                <input type="text" id="bookMarkName" name="bookMarkName">
            </td>

        </tr>
        <tr>
            <th>순서</th>
            <td>
                <input type="number" id="bookMarkOrder" name="bookMarkOrder">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div align="center"><button type="submit">추가</button></div>
            </td>
        </tr>
    </table>

</form>
</body>
</html>
