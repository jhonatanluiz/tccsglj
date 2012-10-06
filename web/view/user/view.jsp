<%-- 
    Document   : view
    Created on : Aug 25, 2012, 5:06:22 PM
    Author     : jhonatan
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" class="modal hide fade" id="myModal" style="display: none">
    <div class="modal-header">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h3 id="myModalLabel">Visualizar</h3>
    </div>
    <div class="modal-body">
        <s:if test="%{user == null}">
            Usuário não encontrado.
        </s:if>
        <s:else>
            <h3 style="margin: 5px !important"><small>Dados Pessoais </small></h3>

            <table border="0" cellpadding="5">
                <tr>
                    <td align="right"><strong>Nome Completo</strong><strong></td>
                    <td style="color: #999999"><s:property value="%{user.name}"/></td>
                </tr>
                <tr>
                    <td align="right"><strong>E-mail</strong></td>
                    <td style="color: #999999"><s:property value="%{user.email}"/></td>
                </tr>
                <tr>
                    <td align="right"><strong>Nº CPF</strong></td>
                    <td style="color: #999999"><s:property value="%{user.cpf}"/></td>
                </tr>
                <tr>
                    <td align="right"><strong>Telefone</strong></td>
                    <td style="color: #999999"><s:property value="%{user.phone}"/></td>
                </tr>
            </table>

            <h3 style="margin: 5px !important"><small>Endereço </small></h3>
            <s:if test="%{user.address.isEmpty()}">
                <table border="0" cellpadding="5">
                    <tr>                    
                        <td align="center">Não Cadastrado no sistema.</td>
                    </tr>
                </table>
            </s:if>
            <s:else>
                <table border="0" cellpadding="5">
                    <tr>
                        <td align="right"><strong>Estado</strong><strong></td>
                        <td style="color: #999999"><s:property value="%{user.address[0].city.state.state}"/></td>
                    </tr>
                    <tr>
                        <td align="right"><strong>Cidade</strong></td>
                        <td style="color: #999999"><s:property value="%{user.address[0].city.city}"/></td>
                    </tr>
                    <tr>
                        <td align="right"><strong>Complemento</strong></td>
                        <td style="color: #999999"><s:property value="%{user.address[0].complement}"/></td>
                    </tr>
                    <tr>
                        <td align="right"><strong>Cep</strong></td>
                        <td style="color: #999999"><s:property value="%{user.address[0].zipCode}"/></td>
                    </tr>
                </table>
            </s:else>
            <h3 style="margin: 5px !important"><small>Conta </small></h3>
            <s:if test="%{user.status}">
                Conta está <span id="user_status_label" class="badge badge-success">Ativa</span>
                <s:if test="%{!user.logAccess.isEmpty()}">
                    , ultimo acesso em <s:property value="%{user.logAccess[user.logAccess.size() - 1].dateAccess}"/>
                </s:if>
            </s:if>
            <s:else>
                Conta está <span id="user_status_label" class="badge badge-important">Inativa</span>
            </s:else>
        </s:else>
    </div>
    <div class="modal-footer">
        <s:if test="%{#session.user.perfil.id == 3}" >
            <button class="btn btn-primary" onclick="javascript: location.href='user/panel?user.id=<s:property value="%{user.id}"/>'">Editar</button>
        </s:if>
        <s:elseif test="%{#session.user.perfil.id == 2 && (user.perfil.id == 1 || user.perfil.id == 2)}">
            <button class="btn btn-primary" onclick="javascript: location.href='user/panel?user.id=<s:property value="%{user.id}"/>'">Editar</button>
        </s:elseif>

        <button data-dismiss="modal" class="btn">Fechar</button>
    </div>
</div>