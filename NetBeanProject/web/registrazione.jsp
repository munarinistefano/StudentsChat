<%-- 
    Document   : registrazione
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
        <title>Registrazione</title>
        <script>
            function validate(form) {
                var elem = form.elements;
                if(elem.password.value != elem.confirm_password.value) {
                  alert('Attenzione! Le password inserite non corrispondono!');
                  return false;
                }
                if(elem.email.value != elem.confirm_email.value) {
                  alert('Attenzione! Le email inserite non corrispondono!');
                  return false;
                }
                
                return true;
              }
        </script>
    </head>
    <body>        
        <div class="container" style="margin: 20px">
        
        <div class="col-lg-1">
            <button class="btn btn-default" onclick="location.href='Home'" style="margin-right: 4px"> <span class="glyphicon glyphicon-chevron-left"/> Login </button>
        </div>
            
        <div class="col-lg-4 col-lg-offset-3" style="background-color: #F0F0F0; border-radius: 8px; padding-bottom: 20px">
            
            <h2 align="center" style="padding-top: 0"> Nuovo Account </h2>
        
            <div class="col-lg-6 col-lg-offset-3" >
        
                <form  role="form" action="RegistrazioneUtente" enctype='multipart/form-data' method="POST" onsubmit="return validate(this);">
                    
                    <div class="form-group">
                    <label>Username</label>
                            <input name="username" type="text" maxlength="30" pattern=".{4,}" title="Minimo 4 caratteri" required="required">
                    </div>
                    <div class="form-group">
                    <label>Password</label>
                            <input name="password" type="password" maxlength="30" pattern=".{5,}" title="Minimo 5 caratteri" required="required">
                    </div>
                    <div class="form-group">
                    <label>Conferma Password</label>
                            <input name="confirm_password" type="password" maxlength="30" required="required">
                    </div>
                    <div class="form-group">
                    <label>Email</label>
                            <input name="email" type="email" required="required" maxlength="30">
                    </div>
                    <div class="form-group">
                    <label>Conferma email</label>
                            <input name="confirm_email" type="email" maxlength="30" required="required">
                    </div>
                            
                    <label>Ruolo</label>
                    <div class="form-group">
                    <div class="radio">
                        <label>
                          <input type="radio" name="ruolo" value="utente" checked id="optionsRadios1">
                          Utente Normale
                        </label>
                    </div>
                    </div>
                    <div class="form-group">
                    <div class="radio">
                        <label>
                          <input type="radio" name="ruolo" value="admin" id="optionsRadios2">
                          Moderatore
                        </label>
                    </div>
                    </div>
                           
                    <div class="form-group">
                        <label>Avatar</label>
                        <input id="avatar" class="" type="file" accept="image/jpeg, image/png" name="avatar">
                    </div>
                           
                    <div class="form-group">
                        <center><button type="submit" class="btn btn-success">Conferma</button></center>
                    </div>
                </form>

            </div>
        </div>
        </div>
    </body>
</html>
