/*
 * slideshow.js
 * Copyright (C) 2014 carwest <carwest@carwest-UDISK>
 *
 * Distributed under terms of the MIT license.
 */

console.log( "slideshow loaded" );

$(document).ready( function(){
    move.select = function(selector){
        return $(selector).get(0);
    };

     Slideshow.init();
} );


function slide(){
    console.log(' slide ');
}

var Slideshow = {

    log: (function( content ){
        console.log( 'Slideshow: ' + content );
    }),

    init_page: (function( page ){
        console.log( page.text() );
    }),

    init_slider: (function( slider ){
        var thispt = this;

        thispt.log( 'slider size:' + slider.children().length );
        var length = slider.children().length;
        var list = slider.children();

        for ( x=0; x<length; x++ ){
            thispt.init_page( list.eq( x ) );
            console.log( list.eq(x).index() );
        }
    }),

    resize: (function(){
        $(".slideshow").each( function(){
            $(this).height( $(window).innerHeight() );
            //$(this).width( $(window).innerWidth() );
            $(this).children().each( function(){
                $(this).height( $(window).innerHeight() );
                $(this).width( $(window).innerWidth() );
            } );
        });
    }),

    init_animate:(function(){
        this.log( 'init_animate' );
        $(".slideshow").each( function(){
            pages = $(this).children();
            length = (pages.length || 0);
            console.log( length );

            if ( length > 0 ){
                pages.eq(0).addClass( "currentPage" );
                move( $(this) ).x( 500 ).end();
            }
        } );
    }),

    init: (function(){

        var thispt =  this;
        var slideshow = $(".slideshow");
        slideshow.each( function(){
            thispt.init_slider( $(this) );
        } );

        // resize the slideshow 
        thispt.resize();
        $(window).resize( function(){thispt.resize();});

        // init the animate
        thispt.init_animate();

    }),
};


