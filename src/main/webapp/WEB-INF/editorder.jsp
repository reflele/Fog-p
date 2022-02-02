<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
        Ordreredigeringsside
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>


        <div class="row">
            <div class="col-md-4"></div>

            <div class="col-md-4">

                <div class="container">

                    <form action="redirect" method="post">
                        <div class="mb-3">
                            <h2 class="text-center">Angiv salgspris</h2>
                            <h6>Du er ved at angive en salgspris for ordrenummer ${sessionScope.reqid}.</h6>
                            <br>
                            <h6>
                                Kundeønske:
                            </h6>
                                ${sessionScope.request.description}

                            <br>
                            <h6>
                                Carport længde:
                            </h6>
                                ${sessionScope.request.length} cm
                            <br>
                            <h6>
                                Carport bredde:
                            </h6>
                                ${sessionScope.request.width} cm
                            <br><br>


                            <h6>Indkøbsprisen for styklisten til denne carport er: ${sessionScope.purchasePrice}
                                kr.</h6>

                            <h6>Anbefalet salgspris: </h6>
                            <h6>${sessionScope.salesPrice} kr</h6>
                        </div>
                        <div class="mb-3">
                            <label for="price" class="form-label">Din salgspris:</label>
                            <input type="text" class="form-control" id="price" name="price"
                                   placeholder="${sessionScope.salesPrice} kr">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">giv tilbud</button>
                            <input type="hidden" name="reqid" value="${sessionScope.reqid}"/>
                            <input type="hidden" name="updateprice" value="1"/>
                            <input type="hidden" name="destination" value="showallorders"/>
                        </div>
                    </form>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>


        <form action="editorder" method="post">


            <h2 class="text-left">Stykliste</h2>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Materialetype</th>
                    <th scope="col">Beskrivelse</th>
                    <th scope="col">Længde</th>
                    <th scope="col">Antal</th>
                    <th scope="col">Enhed</th>
                    <th scope="col">Pris</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="m" items="${sessionScope.materiallist}" varStatus="status">
                    <td>${m.category}</td>
                    <td><c:if test="${m.category != 'tagplade'}">${m.height}x${m.width}mm ${m.description}</c:if>
                        <c:if test="${m.category == 'tagplade'}">${m.height}mm ${m.description}</c:if>
                    </td>
                    <td>
                        <c:if test="${m.length == 0 && m.category != 'tagplade'}">se beskr.</c:if>
                        <c:if test="${m.length != 0 && m.category != 'tagplade'}">${m.length} cm</c:if>


                        <c:if test="${m.category == 'tagplade'}">${m.width}x${m.length} cm</c:if>
                    </td>
                    <td>
                        <div class="qty mt-7">
                            <input type="number" class="count countwidth border-0" name="${status.index}"
                                   value="${sessionScope.bommaterials.get(status.index).count}">
                        </div>
                    </td>

                    <td>${m.unit}</td>

                    <td>${m.price * sessionScope.bommaterials.get(status.index).count} kr</td>

                    </tr>
                </c:forEach>


                </tbody>
            </table>

            <div class="text-center">
                <button type="submit" class="btn btn-primary">opdater priser og stykliste</button>
                <input type="hidden" name="reqid" value="${sessionScope.reqid}"/>
                <input type="hidden" name="updatebom" value="1"/>
                <input type="hidden" name="destination" value="editorder"/>
            </div>
        </form>

        <br></br>

        <button class="btn btn-primary" onclick="myFunction()">Generer carport</button>
        <div id="svg" class="hide">
            <h3 class="text-left">
                Tegning af carport
            </h3>
                ${requestScope.svgdrawing}
        </div>


        <button onclick="myFunction0()" class="btn btn-primary">Liste over spær</button>
        <div id="hide0" class="hide">


            <h2 class="text-left">Materialeliste</h2>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Materialetype</th>
                    <th scope="col">Beskrivelse</th>
                    <th scope="col">Længde</th>
                    <th scope="col">Antal</th>
                    <th scope="col">Enhed</th>
                    <th scope="col">Pris pr enhed</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="m" items="${sessionScope.spær}" varStatus="status">
                    <td>${m.category}</td>
                    <td><c:if
                            test="${m.category != 'tagplade'}">${m.height}x${m.width}mm ${m.description}</c:if>
                        <c:if test="${m.category == 'tagplade'}">${m.height}mm ${m.description}</c:if>
                    </td>
                    <td>
                        <c:if test="${m.length == 0 && m.category != 'tagplade'}">se beskr.</c:if>
                        <c:if test="${m.length != 0 && m.category != 'tagplade'}">${m.length} cm</c:if>


                        <c:if test="${m.category == 'tagplade'}">${m.width}x${m.length} cm</c:if>
                    </td>
                    <td>
                        <form action="editorder" method="post">

                            <div class="qty mt-7">
                                <input type="number" class="count countwidth border-0" name="count"
                                       value="0">
                            </div>

                    </td>


                    <td>${m.unit}</td>

                    <td>${m.price} kr</td>

                    <td>


                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Erstat nuværende</button>
                            <input type="hidden" name="reqid" value="${sessionScope.reqid}"/>
                            <input type="hidden" name="materialid" value="${m.material_id}"/>
                        </div>


                    </td>
                    </form>
                    </tr>
                </c:forEach>


                </tbody>
            </table>

        </div>  <%--spær--%>


        <button onclick="myFunction1()" class="btn btn-primary">Liste over stolper</button>
        <div id="hide1" class="hide">


            <h2 class="text-left">Materialeliste</h2>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Materialetype</th>
                    <th scope="col">Beskrivelse</th>
                    <th scope="col">Længde</th>
                    <th scope="col">Antal</th>
                    <th scope="col">Enhed</th>
                    <th scope="col">Pris pr enhed</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="m" items="${sessionScope.stolpe}" varStatus="status">
                    <td>${m.category}</td>
                    <td><c:if
                            test="${m.category != 'tagplade'}">${m.height}x${m.width}mm ${m.description}</c:if>
                        <c:if test="${m.category == 'tagplade'}">${m.height}mm ${m.description}</c:if>
                    </td>
                    <td>
                        <c:if test="${m.length == 0 && m.category != 'tagplade'}">se beskr.</c:if>
                        <c:if test="${m.length != 0 && m.category != 'tagplade'}">${m.length} cm</c:if>


                        <c:if test="${m.category == 'tagplade'}">${m.width}x${m.length} cm</c:if>
                    </td>
                    <td>
                        <form action="editorder" method="post">

                            <div class="qty mt-7">
                                <input type="number" class="count countwidth border-0" name="count"
                                       value="0">
                            </div>

                    </td>


                    <td>${m.unit}</td>

                    <td>${m.price} kr</td>

                    <td>


                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Erstat nuværende</button>
                            <input type="hidden" name="reqid" value="${sessionScope.reqid}"/>
                            <input type="hidden" name="materialid" value="${m.material_id}"/>
                        </div>


                    </td>
                    </form>
                    </tr>
                </c:forEach>


                </tbody>
            </table>

        </div>

        <button onclick="myFunction2()" class="btn btn-primary">Liste over rem</button>
        <div id="hide2" class="hide">


            <h2 class="text-left">Materialeliste</h2>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Materialetype</th>
                    <th scope="col">Beskrivelse</th>
                    <th scope="col">Længde</th>
                    <th scope="col">Antal</th>
                    <th scope="col">Enhed</th>
                    <th scope="col">Pris pr enhed</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="m" items="${sessionScope.rem}" varStatus="status">
                    <td>${m.category}</td>
                    <td><c:if
                            test="${m.category != 'tagplade'}">${m.height}x${m.width}mm ${m.description}</c:if>
                        <c:if test="${m.category == 'tagplade'}">${m.height}mm ${m.description}</c:if>
                    </td>
                    <td>
                        <c:if test="${m.length == 0 && m.category != 'tagplade'}">se beskr.</c:if>
                        <c:if test="${m.length != 0 && m.category != 'tagplade'}">${m.length} cm</c:if>


                        <c:if test="${m.category == 'tagplade'}">${m.width}x${m.length} cm</c:if>
                    </td>
                    <td>
                        <form action="editorder" method="post">

                            <div class="qty mt-7">
                                <input type="number" class="count countwidth border-0" name="count"
                                       value="0">
                            </div>

                    </td>


                    <td>${m.unit}</td>

                    <td>${m.price} kr</td>

                    <td>


                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Erstat nuværende</button>
                            <input type="hidden" name="reqid" value="${sessionScope.reqid}"/>
                            <input type="hidden" name="materialid" value="${m.material_id}"/>
                        </div>


                    </td>
                    </form>
                    </tr>
                </c:forEach>


                </tbody>
            </table>

        </div>


        <script>
            function myFunction() {
                var x = document.getElementById("svg");
                if (x.style.display === "block") {
                    x.style.display = "none";
                } else {
                    x.style.display = "block";
                }
            }
        </script>

        <script>
            function myFunction0() {
                var x = document.getElementById("hide0");
                if (x.style.display === "block") {
                    x.style.display = "none";
                } else {
                    x.style.display = "block";
                }
            }
        </script>
        <script>
            function myFunction1() {
                var x = document.getElementById("hide1");
                if (x.style.display === "block") {
                    x.style.display = "none";
                } else {
                    x.style.display = "block";
                }
            }
        </script>
        <script>
            function myFunction2() {
                var x = document.getElementById("hide2");
                if (x.style.display === "block") {
                    x.style.display = "none";
                } else {
                    x.style.display = "block";
                }
            }
        </script>
        <script>
            function myFunction3() {
                var x = document.getElementById("hide3");
                if (x.style.display === "block") {
                    x.style.display = "none";
                } else {
                    x.style.display = "block";
                }
            }
        </script>
        <script>
            function myFunction4() {
                var x = document.getElementById("hide4");
                if (x.style.display === "block") {
                    x.style.display = "none";
                } else {
                    x.style.display = "block";
                }
            }
        </script>
        <script>
            function myFunction5() {
                var x = document.getElementById("hide5");
                if (x.style.display === "block") {
                    x.style.display = "none";
                } else {
                    x.style.display = "block";
                }
            }
        </script>
        <script>
            function myFunction6() {
                var x = document.getElementById("hide6");
                if (x.style.display === "block") {
                    x.style.display = "none";
                } else {
                    x.style.display = "block";
                }
            }
        </script>
        <script>
            function myFunction7() {
                var x = document.getElementById("hide7");
                if (x.style.display === "block") {
                    x.style.display = "none";
                } else {
                    x.style.display = "block";
                }
            }
        </script>
        <script>
            function myFunction8() {
                var x = document.getElementById("hide8");
                if (x.style.display === "block") {
                    x.style.display = "none";
                } else {
                    x.style.display = "block";
                }
            }
        </script>


    </jsp:body>
</t:genericpage>
