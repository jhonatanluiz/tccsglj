var validations = [{
    name : "npassword",
    func: function(npassword){
        if($("#cpassword").val() != ""){                
            if($("#cpassword").val() != npassword) {
                return false;
            }
        }
        return true;
    },
    message : "Senha digitadas não conferem."
},{
    name : "cpassword",
    func : function(cpassword){
        if($("#npassword").val() != cpassword) {
            return false;
        }
        return true;
    },
    message : "Senha digitadas não conferem."
}];

$(function(){
    $(".alert").alert();
    

})

