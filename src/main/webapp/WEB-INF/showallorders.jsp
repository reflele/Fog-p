<%@ page import="model.DataRepo" %>
<%@ page import="business.persistence.RequestMapper" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%!
    @Override
    public void jspInit() {
        //her kan jeg skrive initialiseringskode, fx for at befolke datastrukturer, lister etc
        DataRepo.initDataRepo();


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
         Demo Page for Customer Roles
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div class="container">

            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Forespørgsel_id</th>
                    <th scope="col">Længde</th>
                    <th scope="col">Bredde</th>
                    <th scope="col">Højde</th>
                    <th scope="col">Dato for oprettelse</th>
                    <th scope="col">Status</th>
                </tr>
                <tbody>
                <tr>
                    <c:if test="${sessionScope.requestsList == null}">
                    <h1 class="text-center"> Du har ikke sendt nogen forespørgsler</h1>
                        </c:if>
                        <c:if test="${sessionScope.requestsList != null}">

                    <c:forEach var="reqlist" items="${sessionScope.requestsList}">
                <tr>
                    <td>${reqlist.id}</td>
                    <td> ${reqlist.length}</td>
                    <td>${reqlist.width}</td>
                    <td> 210</td>
                    <td> ${reqlist.dateTime}</td>
                    <td> Afventer godkendelse</td>
                </tr>
                </c:forEach>
                </c:if>

                </tr>
                </tbody>
            </table>

    </jsp:body>

</t:genericpage>

