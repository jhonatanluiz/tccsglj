<%-- 
    Document   : resetPassword
    Created on : Aug 18, 2012, 1:57:13 PM
    Author     : jhonatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Bootstrap, from Twitter</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <s:include value="../layout/css.jsp" />
        <s:include value="../layout/javascript.jsp" />  
        <style type="text/css">
            body {
                padding-top: 60px;
                padding-bottom: 40px;
            }
        </style>
    </head>
    <body>

        <s:include value="../layout/topbar.jsp" />  
        <div class="container">


            <div class="page-header"> 
                <h1>Resetar senha <small>/Para resetar sua senha, preencha o campo senha e confirmar senha.</small></h1>
            </div>

            <s:if test="%{!message.isEmpty()}">
                <s:property escape="false" value="%{message}"/>
            </s:if>

            <form class="form-horizontal" action="resetPassword" id="form_reset_password" method="post">
                <fieldset>
                    <div class="control-group">
                        <label class="control-label">Senha</label>
                        <div class="controls">
                            <input class="input focused required npassword" id="npassword" name="npassword" type="password">
                        </div>
                    </div>                    
                    <div class="control-group">
                        <label class="control-label">Confirmar senha</label>
                        <div class="controls">
                            <input class="input focused required cpassword" id="cpassword" name="cpassword" type="password">
                        </div>
                    </div>                    
                    <input type="hidden" name="key" value="<s:property value="%{key}"/>">
                    <input type="hidden" name="action" value="password">
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Resetar senha</button>                               
                    </div>
                </fieldset>
            </form>
            <hr>
            <footer>
                <p>&copy; Company 2012</p>
            </footer>
        </div> 
    </body>    
    <script>
        <s:include value="../../view/authenticate/authenticate.js" />  
    </script>
</html>