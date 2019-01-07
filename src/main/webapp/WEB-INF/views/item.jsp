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
    Добавить деталь:
</h1>

<c:set var="currentPage" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="currentQuery" value="&${pageContext.request.queryString}"/>
<table><tr>
    <td width="295">
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
        <tr>
            <c:if test="${!empty error}">
                <p style="color: red">${error}</p>
            </c:if>
        </tr>
    </table>
</form:form>
</td>
    <td width="275" style="vertical-align: top">
    <c:if test="${currentPage == '/items' || currentPage == '/item/search'}">
    <c:url var="searchAction" value="/item/search"></c:url>

    <form:form action="${searchAction}" modelAttribute="item">
        <table width="275">
            <tr><td align="right" style="vertical-align: top;">
                <input type="text" name="searchItem" id="searchItem"
                       placeholder="Введите наименование"/>

                <input type="submit"
                       value="<spring:message text="Поиск"/>"/>

                <c:if test="${currentPage == '/item/search'}">
                    <a href="/items" style="color: red;">отмена поиска</a>
                </c:if>

            </td></tr>
        </table>
    </form:form>
    </c:if>
        <h4>Фильтр:</h4>
        <a href="/item/filter">Все детали</a>
        <a href="/item/filter">Только необходимые</a>
</td></tr></table>



  <c:if test="${!empty listItem}">
      <h3>Список комплектующих:</h3>
      <table class="tg" border="1">
          <tr>
              <c:url value="${currentPage}" var="toSortByName">
                  <c:if test="${!empty page}"><c:param name="page" value="${page}"/></c:if>
                  <c:param name="sort" value="name"/>
                  <c:if test="${!empty searchItem}"><c:param name="searchItem" value="${searchItem}"/></c:if>
              </c:url>
              <th width="200"><a href="<c:out value="${toSortByName}"/>">Наименование</a></th>
              <c:url value="${currentPage}" var="toSortByReq">
                  <c:if test="${!empty page}"><c:param name="page" value="${page}"/></c:if>
                  <c:param name="sort" value="req"/>
                  <c:if test="${!empty searchItem}"><c:param name="searchItem" value="${searchItem}"/></c:if>
              </c:url>
              <th width="120"><a href="<c:out value="${toSortByReq}"/>">Необходимость</a></th>
              <c:url value="${currentPage}" var="toSortByQty">
                  <c:if test="${!empty page}"><c:param name="page" value="${page}"/></c:if>
                  <c:param name="sort" value="qty"/>
                  <c:if test="${!empty searchItem}"><c:param name="searchItem" value="${searchItem}"/></c:if>
              </c:url>
              <th width="80"><a href="<c:out value="${toSortByQty}"/>">Количество</a></th>
              <th width="60">Изменить</th>
              <th width="60">Удалить</th>
          </tr>
          <c:forEach items="${listItem}" var="item">
              <tr>
                 <%-- <td>${item.id}</td>--%>
                  <td>${item.name}</td>
                  <%--<td>${item.req}</td>--%>
                  <td align="center">
                      <c:choose>
                          <c:when test="${item.req== true}">Да</c:when>
                          <c:otherwise>Нет</c:otherwise>
                      </c:choose>
                     <%--<c:if test="${item.req == true}">V</c:if>--%>
                  </td>
                  <td align="center">${item.qty}</td>
                  <td align="center"><a href="<c:url value='/edit/${item.id}'/>">Изменить</a></td>
                  <td align="center"><a href="<c:url value='/remove/${item.id}'/>">Удалить</a></td>
              </tr>
          </c:forEach>
      </table>
  </c:if>
<c:if test="${maxPages>1}">
  <table width="532" align="right">
     <div id="pagination">Стр:
        <c:url value="${currentPage}" var="prev">
            <c:param name="page" value="${page-1}"/>
            <c:if test="${!empty sort}"><c:param name="sort" value="${sort}"/></c:if>
            <c:if test="${!empty searchItem}"><c:param name="searchItem" value="${searchItem}"/></c:if>
        </c:url>
        <c:if test="${page > 1}">
            <a href="<c:out value="${prev}" />" class="pn prev">Пред</a>
        </c:if>

        <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
            <c:choose>
                <c:when test="${page == i.index}">
                    <span>${i.index}</span>
                </c:when>
                <c:otherwise>
                    <c:url value="${currentPage}" var="url">
                        <c:param name="page" value="${i.index}"/>
                        <c:if test="${!empty sort}"><c:param name="sort" value="${sort}"/></c:if>
                        <c:if test="${!empty searchItem}"><c:param name="searchItem" value="${searchItem}"/></c:if>
                    </c:url>
                    <a href='<c:out value="${url}" />'>${i.index}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:url value="${currentPage}" var="next">
            <c:param name="page" value="${page + 1}"/>
            <c:if test="${!empty sort}"><c:param name="sort" value="${sort}"/></c:if>
            <c:if test="${!empty searchItem}"><c:param name="searchItem" value="${searchItem}"/></c:if>
        </c:url>
        <c:if test="${page + 1 <= maxPages}">
            <a href='<c:out value="${next}" />' class="pn next">След</a>

        </div>
        </table>
    </c:if>
</c:if>
<c:if test="${currentPage == '/items'}">
<table border="1" class="tg">
    <tr>
        <td width="330" align="right">Можно собрать:</td>
        <td width="80" align="center">${counter}</td>
        <td width="136" align="left">компьютеров</td>
    </tr>
</table>
</c:if>

<%--<table>--%>
    <%--<tr><td>currentQuery: </td><td>${currentQuery}</td></tr>--%>
    <%--<tr><td>requestScope</td><td>${requestScope['javax.servlet.forward.request_uri']}</td></tr>--%>
    <%--<tr><td>queryString</td><td>${pageContext.request.queryString}</td></tr>--%>
<%--</table>--%>

  </body>
</html>