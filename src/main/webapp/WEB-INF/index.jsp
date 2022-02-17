<%@ page import="model.DataRepo" %>
<%@ page import="javax.xml.crypto.Data" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%!
    @Override
    public void jspInit() {
        DataRepo.initDataRepo();
        //her kan jeg skrive initialiseringskode, fx for at befolke datastrukturer, lister etc

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
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <h2 class="text-center">Carport designside</h2>

        <div style="margin-top: 3em;margin-bottom: 3em;">

        <div class="container">
        <div class="row">
        <div class="col-md-4"></div>

        <div class="col-md-4">


                <%--        <img src="images/olskercupcakes.png" class="img-fluid mb-4"/>--%>

            <div>
                <div>

                    <form action="fc/orderpage" method="post">

                        <div style="margin-top: 3em;margin-bottom: 3em;">
                            <h4>log på for at starte med at designe og bestille din helt egen carport.</h4>

                        </div>

                        <div class="row">
                            <div class="col-md-4"></div>

                            <div class="col-md-4">

                                <div class="container">

                                </div>
                            </div>

                            <a type="button" class="btn btn-primary btn-lg"
                               href="${pageContext.request.contextPath}/fc/loginpage">Log på</a>

                            <div class="col-md-4">

                                <br>
                                <br/>
                            </div>
                        </div>


                        Eller <a href="${pageContext.request.contextPath}/fc/registerpage">opret bruger</a>

<%--                        Eller <a href="${pageContext.request.contextPath}/fc/showsvg">test SVG</a>--%>

                        <br>
                    </form>
                </div>
            </div>
        </div>

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
            </div>

        </div>


    </jsp:body>
</t:genericpage>