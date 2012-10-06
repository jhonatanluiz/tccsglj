
(function($){
    $.fn.gridView = function(options){
        
        var defaults = {
            url : '',
            total : '',
            page : 1,
            records : '',
            rows : 2,
            sidx : '',
            sord : 'asc',
            data: [],
            field : '',
            search : '',
            onClickRow: null,
            gridStyle : 'table-hover',
            gridHeader : [],
            gridSearch : true,
            gridComboSearch : []
        }
        var settings = $.extend(defaults, options);
        
        var control_panel = null;        
        var control_table = null;        
        var control_pagination = null;
         
        var methods = {
            init : function(object){
                this.renderPanel(object);
            },
            renderPanel: function(object){
                control_panel = document.createElement('div');
                $(control_panel).appendTo($(object));
                $(control_panel).attr("id", "control_panel");
                if(settings.gridSearch) {
                  
                    if(settings.gridHeader.length > 0) {
                        var select = document.createElement('select');
                        var options = "<option value=''> -- selecione -- </option>";
                        $.each(settings.gridHeader, function(index, value){
                            options += "<option value='" + settings.gridComboSearch[index] + "'>" + value + "</option>";
                        });               
                        $(select).html(options);                       
                        $(select).attr("name", "field");
                    }
                    
                    var input = "&nbsp<input type='text' name='search' class='input-large'>&nbsp";
                    var button = "<button class='btn btn-pesquisar' type='button'>Pesquisar</button>";
                    var form  = document.createElement('form');
                    $(select).appendTo($(form));
                    
                    $(form).html($(form).html() + input + button);
                    
                    
                    $(form).appendTo($(control_panel));      
                    $(form).addClass("form-inline");
                    
                    $(".btn-pesquisar").click(this.ajaxRequest);
                }
                
                control_table = document.createElement('div');
                $(control_table).appendTo($(object));
                $(control_table).attr("id", "control_table");
                
                control_pagination = document.createElement('div');
                $(control_pagination).appendTo($(object));
                $(control_pagination).attr("id", "control_pagination");

            },
            info : function(){
                visualizando = settings.data.length != 0 ? settings.page * settings.rows - settings.rows + 1 : 0;
                total = settings.page * settings.rows - (settings.rows - settings.data.length) 
                return "<p style=\"float: right\">visualizando <span class=\"label label-info\">" + visualizando + " - " + total + "</span> de <span class=\"label label-info\">" + settings.records + "</span> registros encontrados</p><br>";
                
            },
            renderTable: function(){
                var table = '<table class="table table-bordered table-hover table-condensed"><thead><tr>';
                $.each(settings.gridHeader, function(index,value){
                    table += "<th>" + value + "</th>";
                });
                table += "</tr></thead><tbody>";

                $.each(settings.data, function(index,data){
                    table += "<tr id='" + data[0] + "' class='tr-selected'>";
                    for( i = 0; i < data.length; i++) {
                        table += "<td>" + data[i] + "</td>";
                    }
                    table += "</tr>";
                });
                             
                table += "</tbody></table>";
                
                $(control_table).html(methods.info() + table);
                
                if(settings.onClickRow) {
                    $(".tr-selected").click(function(){
                        settings.onClickRow($(this).attr("id"));
                    });
                }                
                this.paginator();
            },
            paginator : function(){
                var pag = "<center><div class=\"pagination\">";
                pag += "<ul><li><a style=\"cursor: pointer\" class=\"previousPage\">«</a></li>";
                for(i = 1; i <= settings.total; i++) {
                    
                    css = settings.page == i ? "class=\"active\"" : ""; 
                    
                    pag += "<li "  + css + "><a style=\"cursor: pointer\" class=\"currentPage\">" + i + "</a></li>";
                }
                pag += "<li><a style=\"cursor: pointer\"  class=\"nextPage\">»</a></li></ul></div></center>";
                
                $("#control_pagination").html(pag);
                
                $(".currentPage").click(function(){
                    settings.page = $(this).html();
                    methods.ajaxRequest();
                });
                
                $(".previousPage").click(function(){
                    page = $(".pagination li.active").find(".currentPage").html();
                    if (--page > 0) {
                        settings.page = page;
                        methods.ajaxRequest();
                    }
                });
                
                $(".nextPage").click(function(){
                    page = $(".pagination li.active").find(".currentPage").html();
                    if (++page <=  settings.total) {
                        settings.page = page;
                        methods.ajaxRequest();
                    }
                });
            },
            reset : function() {
                settings.field = null;
                settings.search = null;
                settings.page = 1;
            },
            ajaxRequest: function(){
                $.ajax({
                    url: settings.url,
                    dataType: "JSON",
                    type: "POST",
                    data : {
                        "paginator.total" : settings.total,
                        "paginator.page" : settings.page,
                        "paginator.records" : settings.records,
                        "paginator.rows" : settings.rows,
                        "paginator.sidx" : settings.sidx,
                        "paginator.sord" : settings.sord,
                        "paginator.field" : $("select[name='field']").val() != "" ? $("select[name='field']").val() : settings.field,
                        "paginator.search" : $("input[name='search']").val() != "" ? $("input[name='search']").val() : settings.search
                    },
                    success: function(data) {                     
                        $.extend(settings, data.paginator);
                        methods.renderTable();
                        methods.reset();
                    }
                });
            }
        }
        
        return this.each(function(){            
            methods.init(this);
        });
    }
})(jQuery);