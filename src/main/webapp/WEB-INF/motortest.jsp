<%@ page import="model.DataRepo" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <div class="row">
            <div class="col-md-4"></div>

        <div class="col-md-4">
            <h2 class="text-center">Carport designside</h2>
            <h2 class="text-center">Motortest siger:</h2>

            <h1 class="text-center"> valgte parametre </h1>
                <h4>${sessionScope.length}</h4>
                <h4>${sessionScope.width}</h4>
                <h4>${sessionScope.roof} tag</h4>
            <br><br/>

            <h3>overfladeareal
            <br>
                ${sessionScope.surface}

            </h3>

            <br>
            <h3>antal stolper</h3>
            <h4>${sessionScope.woodenPostCount}</h4>

            <br>
            <h3>antal Rem</h3>


            <br>
            <h3>antal spær</h3>

            <br>


            <h1>Antal xxx</h1>

                    <div class="col-md-4"></div>










            <c:if test="${sessionScope.role == 'employee' }">
            <p style="font-size: larger">This is what you can do,
                since your are logged in as an employee</p>
            <p><a href="fc/employeepage">Employee Page</a>
                </c:if>

                <c:if test="${sessionScope.role == 'customer' }">
            <p style="font-size: larger">This is what you can do, since your
                are logged in as a customer</p>
            <p><a href="fc/customerpage">Customer Page</a>
                </c:if>

        </div>

    </jsp:body>
</t:genericpage>