<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Vis ordre
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>

        <div class="row">
        <div class="col-md-4"></div>

        <div class="col-md-4">

        <div class="container">


            <form action="showallorders" method="post">
                <div class="mb-3">
                    <h2 class="text-center">Angiv salgspris</h2>
<%--                    <h6>Du er ved at angive en salgspris for ordrenummer ${sessionScope.reqid}.</h6>--%>
                    <br>
<%--                    <h6>Indkøbsprisen for styklisten til denne carport er: ${sessionScope.purchasePrice} kr.</h6>--%>

                    <h6>Anbefalet salgspris: </h6>
<%--                    <h6>${sessionScope.salesPrice}</h6>--%>
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Din salgspris:</label>
                    <input type="text" class="form-control" id="price" name="price" placeholder="${sessionScope.salesPrice}">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">giv tilbud</button>
                </div>
            </form>

        </div>
            <div class="col-md-4"></div>

        </div>

        </div>
    </jsp:body>
</t:genericpage>
