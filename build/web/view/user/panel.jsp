<%-- 
    Document   : page
    Created on : Aug 18, 2012, 5:53:11 PM
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
                <h2>Painel usuário <small></small></h2>
            </div>

            <s:if test="%{!message.isEmpty()}">
                <s:property escape="false" value="%{message}"/>
            </s:if>

            <ul id="myTab" class="nav nav-tabs">
                <li class="active"><a href="#home" data-toggle="tab">Cadastro</a></li>
                <li class=""><a href="#profile" data-toggle="tab">Pesquisar</a></li>
                <s:if test="%{#session.user != null}" >
                    <li class=""><a href="#trocar_senha" data-toggle="tab">Trocar senha</a></li>
                </s:if>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade active in" id="home">
                    <div class="page-header header-title">
                        <h2><small>Dados Pessoais </small></h2>
                    </div>
                    <s:if test="%{user == null || user.id == 0}" >
                        <form class="form-horizontal" action="sign" id="form_sign" method="post">
                        </s:if>
                        <s:else>
                            <form class="form-horizontal" action="edit" id="form_sign" method="post">
                            </s:else>

                            <div class="control-group">
                                <label class="control-label" >Nome Completo *</label>
                                <div class="controls">
                                    <input class="input-xlarge focused required" name="user.name" id="user_name" maxlength="60" value="<s:property value="%{user.name.trim()}" />" type="text">

                                    <s:hidden value="%{user.id}" name="user.id" />
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" >Email *</label>
                                <div class="controls">
                                    <input class="input-xlarge focused required email" name="user.email" id="user_email" maxlength="60" value="<s:property value="%{user.email.trim()}" />" type="text">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" >Nº CPF *</label>
                                <div class="controls">
                                    <input class="span2 focused required cpf" maxlength="14" name="user.cpf" id="user_cpf" value="<s:property value="%{user.cpf.trim()}" />" type="text">
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" >Telefone</label>
                                <div class="controls">
                                    <input class="span2 focused" maxlength="15" name="user.phone" id="user_phone" value="<s:property value="%{user.phone.trim()}" />" type="text">
                                </div>
                            </div>
                            <div class="page-header header-title">
                                <h2><small>Endereço </small></h2>
                            </div>
                            <div class="control-group">
                                <label class="control-label" >Estado *</label>
                                <div class="controls">

                                    <s:if test="%{user != null}">
                                        <s:select list="state" cssClass="required" onchange="city(this)" listKey="id"  value="%{user.address[0].city.state.id}" listValue="state"/>
                                    </s:if>
                                    <s:else>
                                        <s:select list="state" cssClass="required" onchange="city(this)" listKey="id"  value="" listValue="state" headerKey="" headerValue="-- Selecione --"/>
                                    </s:else>    
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" >Cidade *</label>
                                <div class="controls" id="combo_city">
                                    <s:if test="%{!city.isEmpty()}">
                                        <s:select list="city" cssClass="required" name="user.address[0].city.id" value="%{user.address[0].city.id}" listKey="id" listValue="city"/>
                                    </s:if >   
                                    <s:else>
                                        <select name="user.address[0].city.id" class="required">
                                            <option value="">-- Selectione --</option>
                                        </select>
                                    </s:else>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" >Complemento</label>
                                <div class="controls">
                                    <input class="input-xlarge focused" value="<s:property value="%{user.address[0].complement}" />" name="user.address[0].complement" type="text" maxlength="255">
                                    <input value="<s:property value="%{user.address[0].id}" />" name="user.address[0].id" type="hidden">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" >CEP</label>
                                <div class="controls">
                                    <input class="span2 focused" value="<s:property value="%{user.address[0].zipCode}" />" name="user.address[0].zipCode" id="user_address_zipcode" type="text">
                                </div>
                            </div>
                                <!--#session.user == null ||-->
                            <s:if test="%{ user == null || user.id == 0}" >
                                <div class="page-header header-title">
                                    <h2><small>Senha</small></h2>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" >Senha *</label>
                                    <div class="controls">
                                        <input class="span2 focused required npassword" name="user.password" id="npassword" type="password" maxlength="8">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" >Confirmar senha *</label>
                                    <div class="controls">
                                        <input class="span2 focused required cpassword" name="cpassword" id="cpassword" type="password" maxlength="8">
                                    </div>
                                </div>
                            </s:if>
                            <div class="page-header header-title">
                                <h2><small>Perfil </small></h2>
                            </div>
                            <div class="control-group">
                                <label class="control-label" >Perfil de acesso *</label>
                                <div class="controls">
                                    <s:select list="perfil" name="user.perfil.id" value="%{user.perfil.id}" cssClass="required" listKey="id" listValue="perfil" headerKey="" headerValue="-- Selecione --"/>
                                </div>
                            </div>
                            <div class="page-header header-title">
                                <h2><small>Conta </small></h2>
                            </div>
                            <div class="control-group">
                                <label class="control-label" ></label>
                                <div class="controls">
                                    <label class="checkbox">
                                        <s:if test="%{user != null}">
                                            <s:if test="%{user.status}">
                                                <input type="checkbox" checked="checked" value="1" id="user_status" name="status"> Conta está <span id="user_status_label" class="badge badge-success">Ativa</span>
                                            </s:if>
                                            <s:else>
                                                <input type="checkbox" value="0" id="user_status" name="status"> Conta está <span id="user_status_label" class="badge badge-important">Inativa</span>
                                            </s:else>
                                        </s:if>
                                        <s:else>
                                            <input type="checkbox" checked="checked" value="1" id="user_status" name="status"> Conta está <span id="user_status_label" class="badge badge-success">Ativa</span>
                                        </s:else>
                                    </label>
                                </div>
                            </div>
                            <div class="form-actions">
                                <button type="submit" class="btn btn-primary">Salvar</button>
                                <button class="btn">Cancelar</button>
                            </div>
                        </form>
                </div>
                <div class="tab-pane fade" id="profile">
                    <div id="grid_view_user">
                    </div>
                </div>
                <s:if test="%{#session.user != null}" >

                    <div class="tab-pane fade" id="trocar_senha">
                        <form class="form-horizontal" action="authenticate/changePassword" id="form_change_password" method="post">
                            <fieldset>
                                <div class="control-group">
                                    <label class="control-label">Senha atual *</label>
                                    <div class="controls">
                                        <input class="input focused required" id="apassword" maxlength="8"  name="apassword" type="password">
                                    </div>
                                </div>                    
                                <div class="control-group">
                                    <label class="control-label">Senha *</label>
                                    <div class="controls">
                                        <input class="input focused required npassword" maxlength="8" id="npassword" name="npassword" type="password">
                                    </div>
                                </div>                    
                                <div class="control-group">
                                    <label class="control-label">Confirmar senha * </label>
                                    <div class="controls">
                                        <input class="input focused required cpassword" maxlength="8" id="cpassword" name="cpassword" type="password">
                                    </div>
                                </div>                                                
                                <div class="form-actions">
                                    <button type="submit" class="btn btn-primary">Resetar senha</button>                               
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </s:if>
            </div>   
            <hr>
            <footer>
                <p>&copy; Company 2012</p>
            </footer>
        </div> 
    </body>    
    <script>
        <s:include value="../../view/user/user.js" />  
    </script>
</html>