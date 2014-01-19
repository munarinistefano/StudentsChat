<%-- 
    Document   : home
    Created on : 22-dic-2013, 14.50.56
    Author     : ©povodev
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href= "css/bootstrap.css" media="screen">
        <title>Homepage</title>
        <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
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
                <a class="btn btn-default btn-info" role="button" href="${ctx}/Gruppi">I Miei Gruppi</a>
                <a class="btn btn-default btn-warning" role="button" href="${ctx}/CreaGruppo">Crea Gruppo</a>
                <!--<a class="btn btn-default btn-info" role="button" href="${ctx}/modificaDati.jsp">Modifica Dati</a>-->
                <c:if test="${sessionScope.utente.tipo_utente == 0}">
                    <a class="btn btn-default btn-info" role="button" href="${ctx}/GruppiModeratore">Pagina Moderatore</a>
                </c:if>
            </div>
            <div class="row">
                <div class="col-md-6 col-md-offset-2" style="overflow: auto;">
                    <h3 align="center">Timeline</h3>
                    <c:if test="${fn:length(aggiornamenti) == 0 }">
                        <div style="text-align: center">Nessun nuovo aggiornamento</div>
                    </c:if>
                    <table class="table table-striped table-bordered">
                        <c:forEach var="update" items="${aggiornamenti}">
                            <tr>
                                <td>
                                    <center><strong>${update.nome_utente}</strong> 
                                    ha pubblicato qualcosa in 
                                    <a href="${ctx}/Gruppo?id=${update.id_gruppo}">
                                    <strong>${update.nome_gruppo}</strong></a> alle <fmt:formatDate type="time" timeStyle="short" value="${update.date_time}"/> del <fmt:formatDate type="date" dateStyle="long" value="${update.date_time}"/></center>
                                    <center><p id="textpost"><em>${update.testo}</em></p></center>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-3 col-md-offset-1">
                    <h3 align="center">Inviti</h3>
                    <div>        
                        <c:if test="${fn:length(inviti) == 0 }">
                            <div style="text-align: right;">Nessuna richiesta d'iscrizione a gruppi</div>
                        </c:if>
                        <table class="table table-striped table-bordered">
                            <c:forEach var="invito" items="${inviti}">
                            <tr>
                                <td>
                                <center>
                                    <c:if test="${invito.stato == 0 }">
                                        <p>${invito.nome_gruppo}</p>
                                        <form name="input" action="ModificaStatoInviti" method="POST">
                                            <input name="${invito.id_gruppo}" type="submit" value="accept" class="btn btn-success btn-sm">
                                            <input name="${invito.id_gruppo}" type="submit" value="refuse" class="btn btn-danger btn-sm">
                                        </form>
                                    </c:if>
                                </center>
                                </td>
                            </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
           </div>        
        </div>  
    </body>
</html>
