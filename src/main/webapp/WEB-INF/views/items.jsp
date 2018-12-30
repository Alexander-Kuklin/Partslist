<%--
  Created by IntelliJ IDEA.
  User: Mac
  Date: 24.12.2018
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parts-list Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body>
<h1>
    Add a item
</h1>

<c:url var="addAction" value="/item/add" ></c:url>

<form:form action="${addAction}" modelAttribute="item">
    <table>
        <c:if test="${!empty item.name}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8"  disabled="true" />
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Наименование"/>
                </form:label>
            </td>
            <td>
                <form:input path="name" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="req">
                    <spring:message text="Необходимость"/>
                </form:label>
            </td>
            <td>
                <%--<form:input path="req"/>--%>
                <form:checkbox path="req"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="qty">
                    <spring:message text="Количество"/>
                </form:label>
            </td>
            <td>
                <form:input path="qty" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty item.name}">
                    <input type="submit"
                           value="<spring:message text="Изменить"/>" />
                </c:if>
                <c:if test="${empty item.name}">
                    <input type="submit"
                           value="<spring:message text="Добавить"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<br>


  <c:if test="${!empty listItem}">
      <h3>Parts - List</h3>
      <table class="tg" border="1">
          <tr>
   <%--           <th width="80">ID</th>--%>
              <th width="120">Наименование</th>
              <th width="120">Необходимость</th>
              <th width="80">Количество</th>
              <th width="60">Изменить</th>
              <th width="60">Удалить</th>
          </tr>
          <c:forEach items="${listItem}" var="item">
              <tr>
                 <%-- <td>${item.id}</td>--%>
                  <td>${item.name}</td>
                  <%--<td>${item.req}</td>--%>
                  <td align="center">
                     <c:if test="${item.req == true}">V</c:if>
                  </td>
                  <td>${item.qty}</td>
                  <td align="center"><a href="<c:url value='/edit/${item.id}'/>">Изменить</a></td>
                  <td align="center"><a href="<c:url value='/remove/${item.id}'/>">Удалить</a></td>
              </tr>
          </c:forEach>
      </table>
  </c:if>

<table width="532" align="right">
<div id="pagination">
    <c:url value="/items" var="prev">
        <c:param name="page" value="${page-1}"/>
    </c:url>
    <c:if test="${page > 1}">
        <a href="<c:out value="${prev}" />" class="pn prev">PREV</a>
    </c:if>

    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <c:choose>
            <c:when test="${page == i.index}">
                <span>${i.index}</span>
            </c:when>
            <c:otherwise>
                <c:url value="/items" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href='<c:out value="${url}" />'>${i.index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:url value="/items" var="next">
        <c:param name="page" value="${page + 1}"/>
    </c:url>
    <c:if test="${page + 1 <= maxPages}">
        <a href='<c:out value="${next}" />' class="pn next">NEXT</a>

</div>
</table>

<table border="1" class="tg">
    <tr>
        <td width="250" align="right">Можно собрать:</td>
        <td width="132" align="center">${counter}</td>
        <td width="130" align="left">компьютеров</td>
    </tr>
</table>
</c:if>
  </body>
</html>
