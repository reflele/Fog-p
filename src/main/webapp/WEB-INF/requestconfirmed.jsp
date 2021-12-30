<%@ page import="model.DataRepo" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <h1 class="text-center">Tak for din forespørgsel!</h1>

<%--        <div class="row">--%>
<%--            <div class="col-md-4"></div>--%>

<%--        <div class="col-md-4">--%>
<%--            <h2 class="text-center">Carport designside</h2>--%>
<%--            <h2 class="text-center">Motortest siger:</h2>--%>

<%--            <h1 class="text-center"> valgte parametre </h1>--%>
<%--                <h4>længde: ${sessionScope.length}</h4>--%>
<%--                <h4>bredde: ${sessionScope.width}</h4>--%>
<%--                <h4>type: ${sessionScope.roof} tag</h4>--%>
<%--            <br><br/>--%>

<%--            <h3>overfladeareal--%>
<%--            <br>--%>
<%--                ${sessionScope.surface}--%>

<%--            </h3>--%>

<%--            <br>--%>
<%--            <h3>antal stolper</h3>--%>
<%--            <h4>${sessionScope.woodenPostCount}</h4>--%>

<%--            <br>--%>
<%--            <h3>Rem længde</h3>--%>
<%--            <h4>${sessionScope.beamsLength} cm</h4>--%>

<%--            <br>--%>
<%--            <h3>spærlængde</h3>--%>
<%--            <h4>${sessionScope.raftersLength} cm</h4>--%>

<%--            <br>--%>
<%--            <h3>roofing</h3>--%>
<%--            <h4>${sessionScope.amountOfSheets} sheets</h4>--%>


<%--            <h1>Antal xxx</h1>--%>

<%--                    <div class="col-md-4"></div>--%>


            <br>
        <div class="text-center">
            <a href="orderpage">Send en forespørgsel</a> mere,
             eller<c:if test="${sessionScope.role == 'customer'}">
            <a href="customerpage">gå til forsiden</a>
        </c:if>
        <c:if test="${sessionScope.role == 'employee'}">
            <a href="employeepage">gå til forsiden</a>
        </c:if>
        </div>

        </div>

    </jsp:body>
</t:genericpage>