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
        <s:if test="%{service == null}">
            Serviço não encontrado.
        </s:if>
        <s:else>
            <h3 style="margin: 5px !important"><small>Dados do Serviço </small></h3>

            <table border="0" cellpadding="5">
                <tr>
                    <td align="right"><strong>Service</strong><strong></td>
                    <td style="color: #999999"><s:property value="%{service.name}"/></td>
                </tr>
                <tr>
                    <td align="right"><strong>Descrição</strong></td>
                    <td style="color: #999999"><s:property value="%{service.description}"/></td>
                </tr>
                <tr>
                    <td align="right"><strong>Valor</strong></td>
                    <td style="color: #999999"><s:property value="%{service.price}"/></td>
                </tr>                
            </table>
        </s:else>
    </div>
    <div class="modal-footer">
        <s:if test="%{#session.user != null}" >
            <button class="btn btn-primary" onclick="javascript: location.href='panel?service.id=<s:property value="%{service.id}"/>'">Editar</button>
        </s:if>
        <button data-dismiss="modal" class="btn">Fechar</button>
    </div>
</div>