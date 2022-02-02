<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Velkommen til din kundeside
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div class="container">

            <div class="row">

                <div class="col-md-4"></div>

                <div class="col-md-4">

                    <br>

                    <form action="orderpage" method="post">
                        <button type="submit" class="btn btn-primary btn-lg btn-block custom">Tilføj ny ordre</button>
                    </form>

                    <br>

                    <form action="showallorders" method="post">
                       <p><button type="submit" class="btn btn-primary btn-lg btn-block custom">Se ordrer</button></p>
                    </form>

                </div>
                <div class="col-md-4"></div>
            </div>

        </div>

    </jsp:body>

</t:genericpage>

