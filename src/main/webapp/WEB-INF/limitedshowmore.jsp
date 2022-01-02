<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Se mere siden
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div class="container">

        <div class="row">

        <h1 class="text-center">Min bestilling ordre #${sessionScope.reqid}</h1>
        <c:if test="${sessionScope.price == 0}">
        <h3 class="text-center">afventer tilbud fra sælger</h3>
        </c:if>
        <c:if test="${sessionScope.price != 0}">
            <h3 class="text-center">Rul ned for at besvare tilbuddet</h3>
        </c:if>
<c:if test="${sessionScope.request.description != null}">
        <h4>
            Kundeønske
        </h4>
        <h7>
                ${sessionScope.request.description}
        </h7>
        <br><br><br><br/>
</c:if>
        <h6><strong> Du har valgt en carport med følgende dimensioner:</strong></h6>
        <br>
        <h6>
            Carport længde
        </h6>
        <h7>
                ${sessionScope.request.length} cm
        </h7>

        <h6>
            Carport bredde
        </h6>
        <h7>
                ${sessionScope.request.width} cm
        </h7>

        <br>

        <%--        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus at erat nec libero malesuada efficitur nec nec diam. Nulla leo urna, porttitor quis varius quis, volutpat eget ex. Nullam nibh arcu, tristique eget molestie sed, dapibus in dui. In fermentum velit dolor, ut iaculis augue mollis in. Aenean id nisi risus. In dui erat, tempus nec augue vitae, dapibus vulputate nisl. Maecenas libero turpis, semper in purus id, fringilla tristique leo.--%>
        <h3>
Tegning af carport
        </h3>
        ${requestScope.svgdrawing}
        <%--        <h3 class="text-center">--%>
        <%--            "accepter og betal" knap. evt en "afvis" knap--%>
        <%--        </h3>--%>

        <%--        <c:if test="${sessionScope.}"--%>
        <c:if test="${sessionScope.price != 0}">
            <form action="redirect" method="post">
                <button type="submit" class="btn btn-primary btn-block">Accepter og betal for ordre</button>
                <input type="hidden" name="reqid" value="${sessionScope.reqid}"/>
                <input type="hidden" name="updatestatus" value="1"/>
                <input type="hidden" name="destination" value="showallorders"/>
            </form>
        </c:if>

    </jsp:body>

</t:genericpage>

