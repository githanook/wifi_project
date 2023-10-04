<%--
  Created by IntelliJ IDEA.
  User: 한욱
  Date: 2023-09-24
  Time: 오후 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.example.wifi_project.wifiDto" %>

<%--<%--%>
<%--    ArrayList<wifiDto> list = (ArrayList<wifiDto>) session.getAttribute("list");--%>
<%--%>--%>


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


<h1>와이파이 정보 구하기</h1>

<a href="hello-servlet">홈</a> | <a href="/location_history">위치 히스토리 목록</a> | <a href="/getOpenApiWifiInfo">Open API 와이파이 정보 가져오기</a> | <a href="/bookMark">북마크 보기</a> | <a href="/bookMarkManagement">북마크 그룹 관리</a>
</br>

<form name="formInfo" method="post" action="hello-servlet" >
LAT: <input type="number" readonly id="LAT" name="LAT" value="${Lat}">, LNT: <input type="number" readonly id="LNT" name="LNT" value="${Lnt}"> <button type="button" name="myLocation" id="myLocation" onclick="getLocation()">내 위치 가져오기</button>  <button type="button" onclick="nearWifiInfo()">근처 WIFI 정보 보기</button>
</form>
<table id="customers">
    <tr>
        <th>거리 (Km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>

    <c:choose>
        <c:when test="${empty list}">
            <tr>
                <td colspan="17" ><div align="center">위치 정보를 입력한 후에 조회해 주세요.</div></td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="bean" items="${list}">
                <tr>
                    <td>${bean.DISTANCE}</td>
                    <td>${bean.x_SWIFI_MGR_NO}</td>
                    <td>${bean.x_SWIFI_WRDOFC}</td>
                    <td>
                     <form id="detailForm" name="detailForm" method="post" action="wifiDetail" >
                        <input type="hidden" id="detailId" name="detailId" value="${bean.ID}">
<%--                        <a href="#" onclick="document.getElementById('detailForm').submit();">${bean.x_SWIFI_MAIN_NM}</a>--%>
<%--                         <a href="#" onclick="submit();">${bean.x_SWIFI_MAIN_NM}</a>--%>
                        <button type="submit">${bean.x_SWIFI_MAIN_NM}</button>
<%--                         <a href="javascript:detailf('${bean.ID}')" >${bean.x_SWIFI_MAIN_NM}</a>--%>
                     </form>
                    </td>
                    <td>${bean.x_SWIFI_ADRES1}</td>
                    <td>${bean.x_SWIFI_ADRES2}</td>
                    <td>${bean.x_SWIFI_INSTL_FLOOR}</td>
                    <td>${bean.x_SWIFI_INSTL_TY}</td>
                    <td>${bean.x_SWIFI_INSTL_MBY}</td>
                    <td>${bean.x_SWIFI_SVC_SE}</td>
                    <td>${bean.x_SWIFI_CMCWR}</td>
                    <td>${bean.x_SWIFI_CNSTC_YEAR}</td>
                    <td>${bean.x_SWIFI_INOUT_DOOR}</td>
                    <td>${bean.x_SWIFI_REMARS3}</td>
                    <td>${bean.LAT}</td>
                    <td>${bean.LNT}</td>
                    <td>${bean.WORK_DTTM}</td>
<%--                    <td>${bean.ID}</td>--%>
                </tr>
<%--            <%--%>
<%--                }--%>
<%--                }--%>
<%--            %>--%>
            </c:forEach>
        </c:otherwise>
    </c:choose>


</table>
<script>
function getLocation(){
    if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition(showPosition);
    }else{
        alert("위치를 얻을 수 없습니다.");
    }
}
function showPosition(position){
    document.getElementById("LAT").value = position.coords.latitude;
    document.getElementById("LNT").value = position.coords.longitude;
}

function nearWifiInfo(){
    if(document.getElementById("LAT").value == ""){
        alert("내 위치 가져오기 먼저 해주세요.");
    }else{
        // var formInfoSubmit = document.formInfo;
        var formInfoSubmit = document.formInfo;
        formInfoSubmit.submit();
    }

}

// function detailf(key){
//     var sss = document.detailForm;
//     sss.submit();
// }


// function wifiDetailClick(){
//
//     var detailFormSubmit = document.detailForm;
//     detailFormSubmit.submit();
//
// }
</script>
</body>
</html>


