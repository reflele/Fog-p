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

<%--            <div class="col-md-4"></div>--%>

<%--<div class="col-md-4">--%>
    <h1 class="text-center">Kundens begrænsede "se mere" side</h1>
        <h4>
kundeønske:
        </h4>
    <h6>
        ${sessionScope.description}
<%--        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus at erat nec libero malesuada efficitur nec nec diam. Nulla leo urna, porttitor quis varius quis, volutpat eget ex. Nullam nibh arcu, tristique eget molestie sed, dapibus in dui. In fermentum velit dolor, ut iaculis augue mollis in. Aenean id nisi risus. In dui erat, tempus nec augue vitae, dapibus vulputate nisl. Maecenas libero turpis, semper in purus id, fringilla tristique leo.--%>
    </h6>

        <h4>
            dimensioner kan tilføjes herunder.
        </h4>
        <br><br><br><br><br><br><br><br><br><br>


<%--        <h3 class="text-center">--%>
<%--            "accepter og betal" knap. evt en "afvis" knap--%>
<%--        </h3>--%>
        <h1>
        </h1>
<%--        <c:if test="${sessionScope.}"--%>
        <c:if test="${sessionScope.price != 0}">
        <form action="redirect" method="post">
            <button type="submit" class="btn btn-primary btn-block">Accepter ordre</button>
            <input type="hidden" name="reqid" value="${sessionScope.reqid}"/>
            <input type="hidden" name="updatestatus" value="1" />
            <input type="hidden" name="destination" value="showallorders" />
        </form>
        </c:if>

    </jsp:body>

</t:genericpage>

