<%-- 
    Document   : modificagruppo
    Created on : 22-dic-2013, 14.50.56
    Author     : ©povodev
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href= "css/bootstrap.css" media="screen">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
        <title>Crea gruppo</title>
    </head>
    <body>
        <div class="container" style="margin: 20px">
            <div class="col-lg-1">
                <button class="btn btn-default" onclick="location.href='Home'" style="margin-right: 4px"> <span class="glyphicon glyphicon-chevron-left"/> Home </button>
            </div>
            
            <div class="col-lg-6 col-lg-offset-2" style="background-color: #F0F0F0; border-radius: 8px; padding-bottom: 20px">
                <h1 align="center" style="padding-top: 0">Modifica dati del Gruppo</h1>
                
                
                <form role="form" action="ModificaDatiGruppo?id=${gruppo.ID}" method='POST'>

                    <div class="form-group">
                        <label>Nuovo nome del gruppo:</label>
                        <input id="username" maxlength="25" name="groupname" type="text" placeholder="${gruppo.nome}" maxlength="30" pattern=".{4,}" title="Minimo 4 caratteri"/>
                    </div>

                    
                    
                    <fieldset>
                <legend> Modifica Privacy gruppo: </legend>
                <c:if test="${gruppo.flag == 0}">
                    Privato <input type="radio" name="privacy" value="privato"/>
                    Pubblico <input type="radio" name="privacy" value="pubblico" checked/>
                </c:if>
                <c:if test="${gruppo.flag == 1}">
                    Privato <input type="radio" name="privacy" value="privato" checked/>
                    Pubblico <input type="radio" name="privacy" value="pubblico"/>
                </c:if>
                
            </fieldset>
                    
                    <h1 align="center" style="padding-top: 0">Invita altri utenti</h1>
    
                    <c:if test="${fn:length(utenti) > 0 }">
                        <c:forEach var="utente" items="${utenti}">
                            <c:if test="${utente.id != sessionScope.utente.id}">
                                <input type="checkbox" name="${utente.id}">${utente.username}
                            <br>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${fn:length(utenti) == 0 }">
                        <p>Tutti gli utenti hanno ricevuto l'invito ad unirsi al tuo gruppo!</p>
                    </c:if>
                <center><button type="submit" class="btn btn-success">Modifica Gruppo</button></center>
                </form>
            </div>
        </div>
       
    </body>
</html>