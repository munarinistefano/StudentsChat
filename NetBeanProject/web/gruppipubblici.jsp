<%-- 
    Document   : gruppipubblici
    Created on : 22-dic-2013, 14.50.56
    Author     : ©povodev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href= "css/bootstrap.css" media="screen">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="ctx" value="${pageContext.request.contextPath}" />
        
        <div class="container" style="margin: 20px">
            <div class='row'>
            <div class="col-lg-1">
                <button class="btn btn-default" onclick="location.href='index.jsp'" style="margin-right: 4px"> <span class="glyphicon glyphicon-chevron-left"/>Home</button>
            </div>
            </div>
            
            <div class='row'>
            <h1 align="center">Gruppi pubblici:</h1>
            <div class="row">
                <div class="col-lg-6 col-lg-offset-3">
                    <c:if test="${fn:length(gruppipubblici) > 0 }">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>Stato</th>
                                    <th>Gruppo</th>
                                    <th>Proprietario</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="gruppopubblico" items="${gruppipubblici}">
                                <tr>
                                    <td width="10%">
                                        <c:if test="${gruppopubblico.stato == 0}">
                                            <center><span class="glyphicon glyphicon-ok-sign"/></center>
                                        </c:if>
                                        <c:if test="${gruppopubblico.stato == 1}">
                                            <center><span class="glyphicon glyphicon-minus-sign"/></center>
                                        </c:if>
                                    </td>
                                    <td width="45%"><a href="Gruppo?id=${gruppopubblico.ID}">${gruppopubblico.nome}</a></td> 
                                    <td width="45%">${gruppopubblico.nome_proprietario}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${fn:length(gruppipubblici) == 0 }">
                        <p>Non è presente alcun gruppo pubblico</p>
                    </c:if>
                </div>
            </div>
            </div>
        </div>
    </body>
</html>
