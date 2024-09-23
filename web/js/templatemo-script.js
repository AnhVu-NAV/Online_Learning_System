<<<<<<< HEAD
jQuery(document).ready(function() {
  "use strict";
    $('.gallery-slider').slick({
        slidesToShow: 5,
        slidesToScroll: 3,
        autoplay: false,
        dots: true,
        autoplaySpeed: 2000,
        arrows: false,
        responsive: [
          {
            breakpoint: 1024,
            settings: {
              slidesToShow: 4,
              slidesToScroll: 4,
              infinite: true,
              dots: true
            }
          },
          {
            breakpoint: 767,
            settings: {
              slidesToShow: 3,
              slidesToScroll: 3
            }
          },
          {
            breakpoint: 575,
            settings: {
              slidesToShow: 2,
              slidesToScroll: 2
            }
          }
          // You can unslick at a given breakpoint now by adding:
          // settings: "unslick"
          // instead of a settings object
        ]
    });
    
    $(".navbar-button").click(function(e){
        e.stopPropagation();
        $(".header").toggleClass("open");
        $(".navbar-button").toggleClass("collapsed");
    });

    function closeMenu() {
      $(".header").removeClass("open");
      $(".navbar-button").addClass("collapsed"); 
    }

    $(".navbar .navbar-nav > .nav-item > a.nav-link").click(function(e){
      e.stopPropagation();
      closeMenu();     
    });

    $("html").click(function(e) {
      closeMenu();
    });

    $('.single-page-nav').singlePageNav({
        filter: ':not(.external)',
        updateHash: true
    });
=======
$(function() {
    $(".navbar-toggler").on("click", function(e) {
        $(".tm-header").toggleClass("show");
        e.stopPropagation();
      });
    
      $("html").click(function(e) {
        var header = document.getElementById("tm-header");
    
        if (!header.contains(e.target)) {
          $(".tm-header").removeClass("show");
        }
      });
    
      $("#tm-nav .nav-link").click(function(e) {
        $(".tm-header").removeClass("show");
      });
>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2
});