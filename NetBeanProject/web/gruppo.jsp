<%-- 
    Document   : gruppo
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
        <title>${gruppo.nome}</title>
        <script>
            function validate(form) {
                var elem = form.elements;
                if(elem.testo.value == '') {
                  alert('Per creare un post, aggiungi del testo!');
                  return false;
                }
                return true;
              }
        </script>
    </head>
    <body>
        <c:set var="ctx" value="${pageContext.request.contextPath}" />
        
        <c:if test="${sessionScope.utente != null}">
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
        </c:if>
        
        <div class="container">
            
            <c:choose>
                <c:when test="${sessionScope.utente != null}">
                    <div class="btn-group btn-group-justified" style="padding-bottom: 20px">
                        <a class="btn btn-default btn-info" role="button" href="${ctx}/Home">Home</a>
                        <a class="btn btn-default btn-warning" role="button" href="${ctx}/Gruppi">Miei Gruppi</a>
                        <c:if test="${sessionScope.utente.tipo_utente == 0}">
                            <a class="btn btn-default btn-info" role="button" href="${ctx}/GruppiModeratore">Pagina Moderatore</a>
                        </c:if>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="btn-group btn-group-justified" style="padding: 20px">
                        <a class="btn btn-default btn-info" role="button" href="${ctx}/index.jsp">Iscriviti a Student's chat per inserire i tui post!</a>
                    </div>
                </c:otherwise>
            </c:choose>

        <div class="row">
            <div class="col-lg-3 col-lg-offset-5" style="text-align: center">
                <h3>${gruppo.nome}</h3>
            </div>
            <div class="col-lg-2 col-lg-offset-1">
                <div class='row'>
                    <c:if test="${sessionScope.utente != null}">
                        <c:choose>
                            <c:when test="${iscrittoAlGruppo == false}">
                <!--                <h6>Iscriviti a questo gruppo per ricevere gli aggiornamenti nella tua home.</h6>-->
                                <button onclick="location.href='IscrizioneGruppoPubblico?id=${gruppo.ID}'" class="btn btn-xs btn-success"> Iscriviti al Gruppo </button>
                            </c:when>
                            <c:otherwise>
                                <button onclick="location.href='CancellaIscrizioneGruppo?id=${gruppo.ID}'" class="btn btn-xs btn-warning"> Cancella Iscrizione </button>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </div>
                <div class="row">
                        <c:if test="${sessionScope.utente.tipo_utente == 0}">
                        <form action="CambiaStatoGruppo?id=${gruppo.ID}&stato=${gruppo.stato}" method='POST'>
                            <c:if test="${gruppo.stato == 1}">
                                <input type="submit" value="RIAPRI GRUPPO" class="btn btn-success btn-xs">
                            </c:if>
                            <c:if test="${gruppo.stato == 0}">
                                <input type="submit" value="CHIUDI GRUPPO" class="btn btn-danger btn-xs">
                            </c:if>
                        </form>
                        </c:if>
                </div>
                <div class="row">
                    <c:if test="${gruppo.ID_proprietario == sessionScope.utente.id}">
                        <c:if test="${gruppo.stato == 0}">
                                <button onclick="location.href='ModificaGruppo?id=${gruppo.ID}'" class="btn btn-xs"> Modifica gruppo </button>
                        </c:if>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-3 col-lg-offset-5">
                <c:if test="${gruppo.flag == 0}">
                    <span class="label label-primary">GRUPPO PUBBLICO</span>
                </c:if>

                <c:if test="${gruppo.flag == 1}">
                    <span class="label label-warning">GRUPPO PRIVATO</span>
                </c:if>

                <c:if test="${gruppo.stato == 1}">
                    <span class="label label-danger">GRUPPO CHIUSO</span>
                </c:if>
                <c:if test="${gruppo.stato == 0}">
                    <span class="label label-success">GRUPPO APERTO</span>
                </c:if>
            </div>
        </div>
        
        <c:if test="${gruppo.stato == 0 && sessionScope.utente != null && iscrittoAlGruppo == true}">
            <div class="row">
                <div class="col-lg-6 col-lg-offset-3">
                    <h3>Nuovo post</h3>
                </div>
            </div>
            <div class="row">
                
                <div class="col-lg-6 col-lg-offset-3" style="background-color: #F0F0F0; border-radius: 8px; padding: 20px">
                    
                    <form role="form" method="POST" enctype='multipart/form-data' action="InserisciPost?id=${gruppo.ID}" onsubmit="return validate(this);">
                        <div class="form-group">
                          <label for="exampleInputEmail1">Testo</label>
                          <input type="text" name="testo" maxlength="450" class="form-control" id="exampleInputEmail1">
                        </div>
                        <div class="form-group">
                          <label for="exampleInputFile">File:</label>
                          <input type="file" name="file1" id="exampleInputFile">
                        </div>
                        <center><button type="submit" value="Inserisci" class="btn btn-success">Inserisci</button></center>
                    </form>
                </div>
            </div>

        </c:if>
            
        <div class="row">
            <div class="col-lg-6 col-lg-offset-3">
                <h3>Post precedenti</h3>
                <c:if test="${fn:length(posts) == 0 }">
                    <p>Nessun messaggio precedente</p>
                </c:if>
            </div>
        </div>
            
        <c:forEach var="post" items="${posts}">
            <div class="row" >
                <div class="col-lg-6 col-lg-offset-3" style="border-top: 1px solid black">
                    <div class="row">
                        <div class="col-lg-6">
                            <c:if test="${post.avatar_utente != null}">
                                    <h6 align="left"><img src="${ctx}/UploadedAvatar/${post.avatar_utente}" alt="avatar" height="100" width="100" class="img-thumbnail"/></h6>
                                </c:if>
                                    Autore: <b>${post.nome_utente} </b>
                                    <p>il <fmt:formatDate type="date" dateStyle="long" value="${post.date_time}"/></p> 
                                    <p> alle <fmt:formatDate type="time" dateStyle="short" value="${post.date_time}"/></p>
                                    <br>
                        </div>
                        <div class="col-lg-6" style="padding-top: 10px">
                            <p style="overflow: hidden ">${post.testo}</p>
                            <c:if test="${post.file != null}">
                                <p><b><i>Anteprima allegato:</i></b></p><a href="${ctx}/Resources/File/${gruppo.ID}/${post.file}">
                                    <img src="${ctx}/Resources/File/${gruppo.ID}/${post.file}" alt="image" height="100" width="100" style="max-height:100px; max-width:100px;" class="img-thumbnail"/>
                                    ${post.file}
                                </a>
                            </c:if>
                        </div>
                    </div>
                    
                </div>
            </div>
        </c:forEach>
        </div>
    </body>
</html>
