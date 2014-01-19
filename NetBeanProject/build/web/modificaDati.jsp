<%-- 
    Document   : modifcaDati
    Created on : 22-dic-2013, 14.50.56
    Author     : ©povodev
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href= "css/bootstrap.css" media="screen">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
        <title>Modifica Dati</title>
        <script>
            function validate(form,password) {
                var elem = form.elements;
                var ret = true;
                if(elem.vecchia_password.value !== ''){
                    if(elem.vecchia_password.value !== password) {
                      alert('Attenzione! La vecchia password non è corretta!');
                      ret = false;
                    }
                    if(elem.password.value !== elem.confirm_password.value) {
                      alert('Attenzione! Le password inserite non corrispondono!');
                      ret = false;
                    }
                }
                return ret;
              }
        </script>
    </head>
    <body>
                   
        <div class="container" style="margin: 20px">
            
        <div class="col-lg-1">
            <button class="btn btn-default" onclick="location.href='Home'" style="margin-right: 4px"> <span class="glyphicon glyphicon-chevron-left"/>Home</button>
        </div>
            
        <div class="col-lg-6 col-lg-offset-2" style="background-color: #F0F0F0; border-radius: 8px; padding-bottom: 20px">
            <h2 align="center" style="padding-top: 0"> Modifica i tuoi dati </h2>
        
            <div class="col-lg-6 col-lg-offset-3" >
        
                <form  role="form" action="ModificaDati" enctype='multipart/form-data' method="POST" onsubmit="return validate(this,'${sessionScope.utente.password}');">
                    <div class="form-group">
                      <label>Vecchia Password</label>
                      <input name="vecchia_password" type="password" required="required" maxlength="30" pattern=".{4,}" class="form-control">
                    </div>
                    <div class="form-group">
                      <label>Nuova Password</label>
                      <input name="password" type="password" maxlength="30" pattern=".{4,}" title="Minimo 5 caratteri" class="form-control">
                    </div>
                    <div class="form-group">
                      <label>Conferma Nuova Password</label>
                      <input name="confirm_password" type="password" maxlength="30" pattern=".{4,}" title="Minimo 5 caratteri" class="form-control">
                    </div>
                    <div class="form-group">
                      <label>Avatar</label>
                      <input id="avatar" type="file" accept="image/jpeg, image/png" name="avatar">
                    </div>
                    <center><button type="submit" class="btn btn-success">Conferma</button></center>
                </form>
            </div>
        </div>
        </div>
    </body>
</html>
