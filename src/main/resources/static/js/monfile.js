if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', ajax)
} else {
    ajax()
}

function ajax(){
    $('.js-data-example-ajax').select2({
        ajax: {
         url: '/panier/search',
            data: function (params) {
                var query = {
                    localisation: params.term,
                }
                // Query parameters will be ?search=[term]&type=public
                return query;
            },
            processResults: function (data) {
                 var values = {
                     'results': []
                 };

                for (var i = 0; i < data.length; i++){
                 values.results[i] = {'id': data[i].idL, 'text': data[i].nomL};
                }
                return values;
            },
            minimumInputlength:2,
            dataType: 'json',
            async: false,
            // Additional AJAX parameters go here; see the end of this chapter for the full code of this example
        }
    });
}



