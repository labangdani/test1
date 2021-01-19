
    $(document).ready(function($){

        $.fn.myFunction = function(){
            alert('You have successfully defined the function!');
        }
        $(".efface)").click(function(){
            $.fn.myFunction();
        });

        $('.ajouter_plat').click(function(e){
            e.preventDefault();
            console.log($(this).parent());
            var copie = $(this).parent()
            var nom_plat = copie.find('.nom_plat').html();
            var prix_plat = copie.find('.prix_plat').html();

            var apend = 0;

            //alert(nom_plat);

            var copie_template = $('#line_cart_template').clone();
            //alert(copie_template);
            var nom_cart = copie_template.find('.nom').html(nom_plat);
            console.log(nom_cart);
            var prix_cart = copie_template.find('.prix').html(prix_plat);
            copie_template.addClass("line_present_cart");

            copie_template.css("display", "block");

            var all_line_cart = $('.line_present_cart');
            copie_template.find('.quantite').html();

            if(all_line_cart.length == 0) {
                copie_template.find('.quantite').html("1");
                $('#cart_line_container').append(copie_template);

            }

            else {

                for(var i = 0 ; i < all_line_cart.length ; i++){
                    //alert("entreeeeeeeeeee");
                    //alert(copie_template.find('.quantite').html());
                    if(all_line_cart[i] !='undefined') {
                        //alert("------------------");
                        var nom_p = $(all_line_cart[i]).find('.nom').html();
                        if(nom_p === nom_plat) {
                            //alert("dedannnnnnnnnnnnn");
                            var ancien_qty = $(all_line_cart[i]).find('.quantite').html();
                            ancien_qty++;

                            $(all_line_cart[i]).find('.quantite').html(ancien_qty);

                        } else {
                            //alert("---------ttttt---------");
                            apend = 1;
                            copie_template.find('.quantite').html("1");
                            $('#cart_line_container').append(copie_template);
                            console.log("tt111111d ",$('#cart_line_container .line_present_cart:last'));


                            //alert("####################");
                        }
                    }
                }
                /*if(all_line_cart.length >= 2){
                    $('#cart_line_container .line_present_cart:last').remove();
                }*/

           /*     if(apend==1){
                    $('#cart_line_container').append(copie_template);
                }
                if(all_line_cart.length >= 2){
                    $('#cart_line_container .line_present_cart:last').remove();
                }*/

            }



        });

    });

 /*   $(document).ready(function($){

        $(".effacer").click(function(){
            alert("hello world!");
            //$(this).hide();
        });

    });*/



