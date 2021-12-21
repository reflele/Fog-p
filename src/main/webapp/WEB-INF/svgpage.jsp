<%@ page import="model.DataRepo" %>
<%@ page import="javax.xml.crypto.Data" %>
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

        <div>
            <h2 class="text-center">Vis svg tegning</h2>

            <p>her indsættes svg tegning:</p>

                ${requestScope.svgdrawing}

        </div>


    </jsp:body>
</t:genericpage>