/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
   

    $.each(validations, function(index, data){
        $.validator.addMethod(
            data.name,
            data.func,
            data.message);
    });
    
    $("#form_sign").validate();
    
    $("#user_phone").mask("(99) 9999-9999");
    $("#user_cpf").mask("999.999.999-99");
    $("#user_address_zipcode").mask("99.999-999");
    
    $("#user_status").click(function(){
        if($("#user_status").is(":checked")) {              
            $("#user_status_label").removeClass("badge-important");
            $("#user_status_label").addClass("badge-success");
            $("#user_status_label").html("Ativa");
            $(this).val(1);
        } else {
            $("#user_status_label").removeClass("badge-success");
            $("#user_status_label").addClass("badge-important");
            $("#user_status_label").html("Inativa");            
            $(this).val(0);
        }
    });
    
    $("#grid_view_user").gridView({
        url : 'list',
        sidx : 'name',        
        gridHeader : ["Id", "Nome", "Email","CPF", "Telefone"],
        gridComboSearch : ["id", "name","email","cpf","phone"],
        onClickRow : viewUser
    });
   
    $("#form_change_password").validate();
    
    function viewUser(id){
        $.post("view",{
            "user.id": id
        },function(response){
            var div_modal = document.createElement("div");
                    
            $(div_modal).attr("id", "div_modal");
            $(div_modal).appendTo("body");
            $(div_modal).html(response);
                    
            $("#myModal").modal("show");
            $('#myModal').on('hidden', function () {
                $(div_modal).remove();
            })
        });
    }
});

function city(obj) {
    if($(obj).val() != "") {
        $.post("city/city",{
            'state.id' : $(obj).val()
        }, function(response){
            $("#combo_city").html(response);
            $("#combo_city").html($("#user_address_0__city_id"));
        });
    } else {
        $("#user_address_0__city_id").val("");
    }
}