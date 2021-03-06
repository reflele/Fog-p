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
         Alle ordrer
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
<%--        <div class="container">--%>

        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">Forespørgsel_id</th>
                <th scope="col">Længde</th>
                <th scope="col">Bredde</th>
                <th scope="col">Højde</th>
                <th scope="col">Dato for oprettelse</th>
                <th scope="col">Status</th>
                <c:if test="${sessionScope.user.role == 'customer'}">
                    <th scope="col">Ordre</th>
                </c:if>
            </tr>
            <tbody>
            <tr>
                <c:if test="${sessionScope.user.role == 'customer'}">
                <c:if test="${sessionScope.requestsList == null}">
                <h1 class="text-center"> Du har ikke sendt nogen forespørgsler</h1>
                </c:if>
                <c:if test="${sessionScope.message != null}">
                <h5 class="text-center">${sessionScope.message}</h5>
                <br>
                </c:if>
                <c:if test="${sessionScope.requestsList != null}">

                <c:forEach var="reqlist" items="${sessionScope.requestsList}">
            <tr>
                <td>${reqlist.id}</td>
                <td> ${reqlist.length}</td>
                <td>${reqlist.width}</td>
                <td> 210</td>
                <td> ${reqlist.dateTime}</td>
                <td>
                    <c:if test="${reqlist.status == 1}">
                        Ordre gennemført.
                    </c:if>
                    <c:if test="${reqlist.price == 0}">
                        afventer svar fra sælger.
                    </c:if>
                    <c:if test="${reqlist.price != 0}">
                        <c:if test="${reqlist.status != 1}">
                            Du har fået et tilbud på ${reqlist.price} kr
                        </c:if>
                    </c:if>
                </td>

                <td>
                    <c:if test="${reqlist.status == 1}">
                        <form action="fullshowmore" method="post">
                            <button type="submit" class="btn btn-primary btn-block">Stykliste & ordrebek.</button>
                            <input type="hidden" name="reqid" value="${reqlist.id}"/>
                        </form>
                    </c:if>
                    <c:if test="${reqlist.status == 0}">
                        <form action="limitedshowmore" method="post">
                            <button type="submit" class="btn btn-primary btn-block">vis mere</button>
                            <input type="hidden" name="reqid" value="${reqlist.id}"/>
                        </form>
                    </c:if>

                </td>
            </tr>
            </c:forEach>
            </c:if>
            </c:if>


            <c:if test="${sessionScope.user.role == 'employee'}">
                <c:if test="${sessionScope.requestsList == null}">
                    <h1 class="text-center"> Der er ingen forespørgsler at vise</h1>
                </c:if>
                <c:if test="${sessionScope.requestsList != null}">

                    <c:forEach var="reqlist" items="${sessionScope.requestsList}">
                        <tr>
                            <td>${reqlist.id}</td>
                            <td> ${reqlist.length}</td>
                            <td>${reqlist.width}</td>
                            <td> ${reqlist.height}</td>
                            <td> ${reqlist.dateTime}</td>
                            <td>
                                <c:if test="${reqlist.status == 1}">
                                    Ordre gennemført
                                </c:if>
                                <c:if test="${reqlist.price == 0}">
                                    <form action="editorder" method="post">
                                        <button type="submit" class="btn btn-primary btn-block">Opret tilbud</button>
                                        <input type="hidden" name="reqid" value="${reqlist.id}"/>
                                    </form>
                                </c:if>
                                <c:if test="${reqlist.price != 0 && reqlist.status != 1}">


                                    <form action="editorder" method="post">
                                        Tilbud afgivet:
                                        <button class="mobu" type="submit"> ${reqlist.price} kr</button>
                                        <input type="hidden" name="reqid" value="${reqlist.id}"/>
                                    </form>
                                    Afventer kundesvar
                                </c:if>

                            </td>

                                <%--                            <td>--%>
                                <%--                                <form action="showorder" method="post">--%>
                                <%--                                    <button type="submit" class="btn btn-primary btn-block">Vis ordre</button>--%>
                                <%--                                    <input type="hidden" name="reqid" value="${reqlist.id}"/>--%>
                                <%--                                </form>--%>
                                <%--                            </td>--%>
                        </tr>

                    </c:forEach>

                </c:if>
            </c:if>


            </tr>
            </tbody>
        </table>


    </jsp:body>

</t:genericpage>

