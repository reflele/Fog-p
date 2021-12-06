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

            <div class="col-md-4"></div>

<div class="col-md-4">
    <form action="orderpage" method="post">
        <button type="submit" class="btn btn-primary btn-lg btn-block">Tilføj ny ordre</button>
    </form>
    <br><br/>
            <form action="fc/xxxxxxxx" method="post">
                <button type="submit" class="btn btn-primary btn-lg btn-block">Se ordrer</button>
        </form>

</div>
        <div class="col-md-4"></div>
        </div>
        <h1>Hello ${sessionScope.email} </h1>
        You are now logged in as a Customer of our wonderful site.
        Role: ${sessionScope.role}
    </jsp:body>

</t:genericpage>

