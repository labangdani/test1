if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', store)
} else {
    store()
}

function store(){
    var cartRowContents;
    
    var cartItems = document.getElementsByClassName('cart-items')[0]
    // recuperer le tableau dans le local storage
    var cartItemNames = JSON.parse(window.sessionStorage.getItem("table"))
    console.log("-----------",cartItemNames)
    for (var i = 0; i < cartItemNames.length; i++) {
        var cartRow = document.createElement('div')
        cartRow.classList.add('cart-row')
        cartRow.classList.add('row')
        cartRowContents = `
            <div class="col-6 cart-item cart-column">
                <img class="cart-item-image" src="${cartItemNames[i].image}" width="50" height="50">
                <span class="cart-item-title">${cartItemNames[i].namep}</span>
            </div>
            <span class="col-3 cart-price cart-column">${cartItemNames[i].prix}</span>
            <div class="col-3 cart-quantity cart-column">
                <input class="cart-quantity-input" type="number" value="${cartItemNames[i].qty}">
            </div>
        
`   
    cartRow.innerHTML = cartRowContents
    cartItems.append(cartRow)
        
    }
    updateCartTotal()
    updateSubCartTotal()

}


function updateCartTotal() {
    var cartItemContainer = document.getElementsByClassName('cart-items')[0]
    var cartRows = cartItemContainer.getElementsByClassName('cart-row')
    var total = 0
    for (var i = 0; i < cartRows.length; i++) {
        var cartRow = cartRows[i]
        var priceElement = cartRow.getElementsByClassName('cart-price')[0]
        var quantityElement = cartRow.getElementsByClassName('cart-quantity-input')[0]
        var price = parseFloat(priceElement.innerText.replace('Fcfa', ''))
        var quantity = quantityElement.value
        total = total + (price * quantity)
    }
    total = Math.round(total * 100) / 100
    document.getElementsByClassName('cart-total-price')[0].innerText = total + 'Fcfa'
}

function updateSubCartTotal() {
    var cartItemContainer = document.getElementsByClassName('cart-items')[0]
    var cartRows = cartItemContainer.getElementsByClassName('cart-row')
    var sub_total = 0
    for (var i = 0; i < cartRows.length; i++) {
        var cartRow = cartRows[i]
        var priceElement = cartRow.getElementsByClassName('cart-price')[0]
        var quantityElement = cartRow.getElementsByClassName('cart-quantity-input')[0]
        var price = parseFloat(priceElement.innerText.replace('Fcfa', ''))
        var quantity = quantityElement.value
        sub_total = sub_total + (price * quantity)
    }
    frais_livraison = document.getElementsByClassName('cart-fraislivraison-price')[0].innerText.replace('Fcfa', '');
    sub_total =(Math.round(sub_total * 100) / 100)  + parseFloat(frais_livraison);
    document.getElementsByClassName('cart-totalfinal-price')[0].innerText = sub_total + 'Fcfa';
    document.getElementsByClassName('mon-total')[0].innerText = sub_total + 'Fcfa';
}

function savedata(){
    console.log("hello world");

        var data = window.sessionStorage.getItem("table")
    $.post('http://localhost:8080/panier/save',
            {data:data},
            function(res){console.log(res,"##############################")}
            )
    console.log("##############################")
}