<%@ page import="model.DataRepo" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%!
    @Override
    public void jspInit() {
        //her kan jeg skrive initialiseringskode, fx for at befolke datastrukturer, lister etc
        DataRepo.initDataRepo();
//        System.out.println("asdasdasd");

//        ServletContext application = getServletContext();
    }
%>

<%
    application.setAttribute("heights", DataRepo.getHeights());
    application.setAttribute("widths", DataRepo.getWidths());
    application.setAttribute("lengths", DataRepo.getLengths());
    application.setAttribute("rooftypes", DataRepo.getRoofTypes());
%>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>


        <h2 class="text-center">Carport designside</h2>

        <div style="margin-top: 3em;margin-bottom: 3em;">

            <div>
                <form action="fc/xxxxxxxx" method="post">


                <div class="row">
                    <div class="col-3 mb-3"></div>

                    <div class="col-3">

                        <div class="container mb-3">


                            <a>Vælg længde</a>
                            <select class="form-select" aria-label="Default select example">
                                <c:forEach var="lengths" items="${applicationScope.lengths}">
                                <option value="length" name="length" class="dropdown-item">
                                        ${lengths.dimension}
                                     </option></c:forEach>
                            </select>

                            </select>
                            <br>
                            <a>Vælg højde</a>
                                    <select class="form-select" aria-label="Default select example">
                                        <c:forEach var="heights" items="${applicationScope.heights}">
                                            <option value="height" name="height" class="dropdown-item">
                                                    ${heights.dimension}
                                            </option></c:forEach>
                                    </select>
                            <br>
                            <a>Vælg bredde</a>
                                    <select class="form-select" aria-label="Default select example">
                                        <c:forEach var="widths" items="${applicationScope.widths}">
                                            <option value="width" name="width" class="dropdown-item">
                                                    ${widths.dimension}
                                            </option></c:forEach>
                                    </select>
                            <br>
                            <a>Vælg Trætype</a>
                            <select class="form-select" aria-label="Default select example">
                                <option selected>Open this select menu</option>
                                <option value="1">One</option>
                                <option value="2">Two</option>
                                <option value="3">Three</option>
                            </select>
                            <br>
                            <a>Vælg Beklædning</a>
                            <select class="form-select" aria-label="Default select example">
                                <option selected>Open this select menu</option>
                                <option value="1">One</option>
                                <option value="2">Two</option>
                                <option value="3">Three</option>
                            </select>


                        </div>
                    </div>
                    <div class="col-3 mb-3">

                        <a>Vælg antal sider</a>
                        <select class="form-select" aria-label="Default select example">
                            <option selected>Open this select menu</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>

                        <br>
                        <a>Vælg lakering</a>
                        <select class="form-select" aria-label="Default select example">
                            <option selected>Open this select menu</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>

                        <br>
                        <a>Vælg tagtype</a>
                        <select class="form-select" aria-label="Default select example">
                            <c:forEach var="roofs" items="${applicationScope.rooftypes}">
                                <option value="roofs" name="${roofs.id}" class="dropdown-item">
                                        ${roofs.name}
                                </option></c:forEach>
                        </select>
                        <br><br/>


                        <div id="skurvalg">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="skur" id="skur" value="skur">
                                <label class="form-check-label" for="skur">
                                    Skur
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="skur" id="intet skur"
                                       value="intet skur">
                                <label class="form-check-label" for="intet skur">
                                    Intet skur
                                </label>
                            </div>
<%--                           <script>--%>
<%--                           </script>--%>
<%--                                //                                 function loadDoc() {--%>
<%--                                //                                      document.getElementById("skurvalg").innerHTML = <h1>asdasdasdas</h1>;--%>

<%--                                //  function loadDoc(){--%>
<%--                                //     document.getElementById("skurvalg").innerHTML = "dgfhfgh";--%>

<%--                                // lengthdrop = document.createElement()--%>

<%--                                //  document.getElementById("skurvalg").appendChild()--%>
<%--                                /* When the user clicks on the button,--%>
<%--                          toggle between hiding and showing the dropdown content */--%>
<%--                        --%>
<%--        --%>
<%-- //                           --%>

<%--                        </div>--%>

<%--                                                        <div id="demo">--%>
<%--                                                            <h1>The XMLHttpRequest Object</h1>--%>
<%--                                                            <button type="button" onclick="loadDoc()">Change Content</button>--%>
<%--                                                        </div>--%>

<%--                                                        <script>--%>
<%--                                                            function loadDoc() {--%>
<%--                                                                document.getElementById("demo").innerHTML = 5 + 6;--%>
<%--                                                            }--%>
<%--                                                        </script>--%>


                    </div>


                    <div class="col-3 mb-3"></div>


                </div>
                    <button type="submit" class="btn btn-primary btn-lg btn-block">Send forespørgsel</button>
                </div>
                </form>


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