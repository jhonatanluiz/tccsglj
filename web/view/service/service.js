$(function(){
    $("#grid_view_service").gridView({
        url : 'list',
        sidx : 'name',        
        gridHeader : ["Id", "Nome", "Descrição","Valor"],
        gridComboSearch : ["id", "name","description","price"],
        onClickRow : viewService
    });
    
    function viewService(id){
        $.post("view",{
            "service.id": id
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