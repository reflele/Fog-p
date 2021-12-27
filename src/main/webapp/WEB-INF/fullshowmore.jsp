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
        <div class="container">

            <div class="row">

                <div class="col-md-4"></div>
            </div>
        </div>

        <%--<div class="col-md-4">--%>
        <h2 class="text-left">
            Ordre ${sessionScope.reqid}
        </h2>


        <br>


        <div class="container">

            <div class="row">
                <h5>Ordredetaljer</h5>
                <br>
                <div class="col-md-4">

                    <table class="table table-hover">
                        <thead>
                            <%--                <tr>--%>
                            <%--                    <th scope="col">#</th>--%>
                            <%--                    <th scope="col">First</th>--%>
                            <%--                    <th scope="col">Last</th>--%>
                            <%--                    <th scope="col">Handle</th>--%>
                            <%--               </tr>--%>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">Levering</th>
                            <td>Afhentning</td>
                        </tr>
                        <tr>
                            <th scope="row">Navn</th>
                            <td>${sessionScope.user.firstName} ${sessionScope.user.lastName}</td>
                        </tr>
                        <tr>
                            <th scope="row">Telefonnummer</th>
                            <td>${sessionScope.user.phoneNumber}</td>
                        </tr>
                        <tr>
                            <th scope="row">Email</th>
                            <td> ${sessionScope.user.email}</td>
                        </tr>

                        </tbody>
                    </table>


                </div>
                <br>
                <br>
                <br><br>
                <br>
                <br>
                <br>


                <div class="col-md-4">
                    <h5></h5>
                    <table class="table table-hover">
                        <thead>
                            <%--                <tr>--%>
                            <%--                    <th scope="col">#</th>--%>
                            <%--                    <th scope="col">First</th>--%>
                            <%--                    <th scope="col">Last</th>--%>
                            <%--                    <th scope="col">Handle</th>--%>
                            <%--                </tr>--%>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">Bestilt</th>
                            <td>${sessionScope.carportrequest.dateTime}</td>
                        </tr>
                        <tr>
                            <th scope="row">Betalingsform</th>
                            <td>Online</td>
                        </tr>
                        <tr>
                            <th scope="row">Pris</th>
                            <td>${sessionScope.carportrequest.price} kr</td>
                        </tr>
                        <tr>
                            <th scope="row">Betalingsstatus</th>
                            <td>betalt</td>
                        </tr>
                        <tr>
                            <th scope="row">Betalingsmetode</th>
                            <td>betalt online</td>
                        </tr>

                        </tbody>
                    </table>


                </div>

                <div class="col-md-4">

                    <h4>
                        kundeønske:
                    </h4>
                    <h6>
                            ${sessionScope.description}
                            <%--        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus at erat nec libero malesuada efficitur nec nec diam. Nulla leo urna, porttitor quis varius quis, volutpat eget ex. Nullam nibh arcu, tristique eget molestie sed, dapibus in dui. In fermentum velit dolor, ut iaculis augue mollis in. Aenean id nisi risus. In dui erat, tempus nec augue vitae, dapibus vulputate nisl. Maecenas libero turpis, semper in purus id, fringilla tristique leo.--%>
                    </h6>
                </div>
            </div>
        </div>

<br></br>
        <h2 class="text-left">Stykliste</h2>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">Materialetype</th>
                <th scope="col">Beskrivelse</th>
                <th scope="col">Længde</th>
                <th scope="col">Antal</th>
                <th scope="col">Enhed</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="m" items="${sessionScope.materiallist}" varStatus="status">
                <td>${m.category}</td>
                <td>${m.height}x${m.width}mm ${m.description}</td>
                <td>${m.length} cm</td>
                <td>${sessionScope.bommaterials.get(0).count}</td>
<%--                <td>${sessionScope.bommaterials.toString()}</td>--%>

                <td>${m.unit}</td>

            </tr>
            </c:forEach>


            </tbody>
        </table>




        <br>

        <h3>
            SVG
        </h3>

        ${requestScope.svgdrawing}

        <%--</div>--%>
        <%--        <div class="col-md-4"></div>--%>
        <%--        </div>--%>
    </jsp:body>

</t:genericpage>

