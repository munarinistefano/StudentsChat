<%-- 
    Document   : gruppi
    Created on : 22-dic-2013, 14.50.56
    Author     : ©povodev
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href= "css/bootstrap.css" media="screen">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
        <title>I tuoi gruppi</title>
    </head>
    <body>
        <c:set var="ctx" value="${pageContext.request.contextPath}" />
        
        <div class="jumbotron" style="padding: 5px">
            <div class="row">
                <div class="col-lg-1">
                    <c:if test="${utente.avatar != null}">
                        <img src="${ctx}/UploadedAvatar/${sessionScope.utente.avatar}" alt="avatar" class="img-rounded" style="height: 100px; width: 100px"/>
                    </c:if>
                </div>
                <div class="col-lg-7" >            
                    <h4> Ciao ${sessionScope.utente.username}! </h4>
                    <h5><a href="${ctx}/modificaDati.jsp">Modifica profilo</a></h5>
                    <c:if test="${utente.date_time != null}">
                        <h5> Il tuo ultimo accesso e' stato il <fmt:formatDate type="date" dateStyle="long" value="${sessionScope.utente.date_time}"/> alle <fmt:formatDate type="time" timeStyle="short" value="${sessionScope.utente.date_time}"/></h5>
                    </c:if>
                </div>
                <div class="col-lg-1 col-lg-offset-3">
                    <button class="btn btn-danger" onclick="location.href='Logout'">Logout</button>
                </div>
            </div>  
        </div>
        
        <div class="container">
            
            <div class="btn-group btn-group-justified" style="padding: 0">
                <a class="btn btn-default btn-info" role="button" href="${ctx}/Home">Home</a>
                <a class="btn btn-default btn-warning" role="button" href="${ctx}/CreaGruppo">Crea Gruppo</a>
                <c:if test="${sessionScope.utente.tipo_utente == 0}">
                    <a class="btn btn-default btn-info" role="button" href="${ctx}/GruppiModeratore">Pagina Moderatore</a>
                </c:if>
            </div>
            
            <h1 align="center">Gruppi a cui sei iscritto:</h1>
            
            <div class="row">
                <div class="col-lg-6 col-lg-offset-3">
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>Stato</th>
                                <th>Gruppo</th>
                                <th>Proprietario</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:if test="${fn:length(gruppipubblici) > 0 }">
                            <c:forEach var="gruppo" items="${gruppi}">
                                <tr>
                                    <td width="10%">
                                        <c:if test="${gruppo.stato == 0}">
                                            <center><span class="glyphicon glyphicon-ok-sign"/></center>
                                        </c:if>
                                        <c:if test="${gruppo.stato == 1}">
                                            <center><span class="glyphicon glyphicon-minus-sign"/></center>
                                        </c:if>
                                    </td>
                                    <td width="45%"><a href="Gruppo?id=${gruppo.ID}">${gruppo.nome}</a></td>
                                    <td width="45%">${gruppo.nome_proprietario}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>

                    <c:if test="${fn:length(gruppi) == 0 }">
                        <p>Non sei iscritto ad alcun gruppo</p>
                    </c:if>
                </div>
            </div>
        
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
    </body>
</html>
