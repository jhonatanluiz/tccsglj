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
    
    $("#form_request_password").validate();
    $("#form_login").validate();
    $("#form_reset_password").validate();
    
});
