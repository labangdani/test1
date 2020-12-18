$(document).ready(function() {

    function listerestaurant(restaurant) {
        localStorage.removeItem("restaurant");
        localStorage.setItem("restaurant", JSON.stringify(restaurant));
        localStorage.getItem("restaurant");
    };
});
