<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Demo Page for Customer Roles
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
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus at erat nec libero malesuada efficitur nec nec diam. Nulla leo urna, porttitor quis varius quis, volutpat eget ex. Nullam nibh arcu, tristique eget molestie sed, dapibus in dui. In fermentum velit dolor, ut iaculis augue mollis in. Aenean id nisi risus. In dui erat, tempus nec augue vitae, dapibus vulputate nisl. Maecenas libero turpis, semper in purus id, fringilla tristique leo.
    </h6>

        <h4>
            dimensioner kan tilføjes herunder.
        </h4>
        <br><br><br><br><br><br><br><br><br><br>
        <h3 class="text-center">
            "accepter og betal" knap. evt en "afvis" knap
        </h3>
        <form action="showallorders?target=redirect&destination=showallorders" method="post">
            <button type="submit" class="btn btn-primary btn-block">vis mere</button>
            <input type="hidden" name="reqid" value="${reqlist.id}"/>
            <input type="hidden" name="price" value="${reqlist.id}"/>
            <input type="hidden" name="updateprice" value="1" />
        </form>

<%--</div>--%>
<%--        <div class="col-md-4"></div>--%>
<%--        </div>--%>
    </jsp:body>

</t:genericpage>

