<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 한욱
  Date: 2023-10-03
  Time: 오후 3:11
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

<h1>와이파이 정보 구하기</h1>

<a href="hello-servlet">홈</a> | <a href="/location_history">위치 히스토리 목록</a> | <a href="/getOpenApiWifiInfo">Open API 와이파이 정보 가져오기</a> | <a href="/bookMark">북마크 보기</a> | <a href="/bookMarkManagement">북마크 그룹 관리</a>
</br>
<form id="wifiBookmarkForm" name="wifiBookmarkForm" method="post" action="/wifiDetail">
    <input type="hidden" id="detailId" name="detailId" value="${data.ID}">

<select name="bookmarkGroupid" id="bookmarkGroupid">
    <option value="">북마크 그룹 이름 선택</option>
    <c:forEach var="dd" items="${list}">
        <option value="${dd.ID}">${dd.NAME}</option>
    </c:forEach>
</select> <button type="submit">북마크 추가하기</button>
</form>
<table id="customers">
    <tr>
        <th>관리번호</th>
        <td>${data.x_SWIFI_MGR_NO}</td>
    </tr>
    <tr>
        <th>자치구</th>
        <td>${data.x_SWIFI_WRDOFC}</td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td>${data.x_SWIFI_MAIN_NM}</td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td>${data.x_SWIFI_ADRES1}</td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td>${data.x_SWIFI_ADRES2}</td>
    </tr>
    <tr>
        <th>설치위치(층)</th>
        <td>${data.x_SWIFI_INSTL_FLOOR}</td>
    </tr>
    <tr>
        <th>설치유형</th>
        <td>${data.x_SWIFI_INSTL_TY}</td>
    </tr>
    <tr>
        <th>설치기관</th>
        <td>${data.x_SWIFI_INSTL_MBY}</td>
    </tr>
    <tr>
        <th>서비스구분</th>
        <td>${data.x_SWIFI_SVC_SE}</td>
    </tr>
    <tr>
        <th>망종류</th>
        <td>${data.x_SWIFI_CMCWR}</td>
    </tr>
    <tr>
        <th>설치년도</th>
        <td>${data.x_SWIFI_CNSTC_YEAR}</td>
    </tr>
    <tr>
        <th>실내외구분</th>
        <td>${data.x_SWIFI_INOUT_DOOR}</td>
    </tr>
    <tr>
        <th>접속환경</th>
        <td>${data.x_SWIFI_REMARS3}</td>
    </tr>
    <tr>
        <th>X좌표</th>
        <td>${data.LAT}</td>
    </tr>
    <tr>
        <th>Y좌표</th>
        <td>${data.LNT}</td>
    </tr>
    <tr>
        <th>작업일자</th>
        <td>${data.WORK_DTTM}</td>
    </tr>



</table>
</body>
</html>
