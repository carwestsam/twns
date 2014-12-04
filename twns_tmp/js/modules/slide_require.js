/*
 * slide_require.js
 * Copyright (C) 2014 carwest <carwest@carwest-UDISK>
 *
 * Distributed under terms of the MIT license.
 */


define( ["jquery"], function($ ){

    $(document).ready( function(){
         Slideshow.init();
    } );


    function slide(){
        console.log(' slide ');
    }

    var Slideshow = {
        
        /*
         * the Width and Height of the Slide
         */
        height: 0,
        width: 0,

        log: (function( content ){
            console.log( 'Slideshow: ' + content );
        }),

        /*
         * listen the Window resize event
         * adjust the size of the slides
         */

        resize: (function( thispt ){

            var nheight = $(window).innerHeight();
            var nwidth = $(window).innerWidth();

            if ( nwidth == thispt.width ){
                return thispt.resize;
            }

            thispt.height = $(window).innerHeight();
            thispt.width = $(window).innerWidth();

            if ( thispt.height > thispt.width * 0.618 ){
                thispt.height = thispt.width * 0.618;
            }

            var Height = thispt.height;
            var Width = thispt.width;

            $(".slideshow").each( function(){
                $(this).height( Height );
                $(this).css('font-size', (Height/30) + 'px');
                //$(this).width( $(window).innerWidth() );
                $(this).children().each( function(){
                    $(this).height( Height );
                    $(this).width( Width );
                } );
            });

            return thispt.resize;
        }),

        /*
         * control the animation of the slides
         */

        animate_bufs: [],
        animate_callbacks: [],


        init_animate:(function( thispt ){
            thispt.log( 'init_animate2' );
            $(".slideshow .page").each( function(){
                $(this).hide();
                $(this).transition( { x:thispt.Width, y:0 },0 );
                console.log( $(this) );
            });
            $(".slideshow").each( function(){
                var pages = $(this).children();
                var length = (pages.length || 0);

                if ( length > 1 ){
                    for ( var i=0; i<length-1; i++ ){
                        pages.eq(i).data( 'next', i+1 );
                    }pages.eq(length-1).data( 'next', 0 );

                    pages.eq(length-1).addClass( 'currentPage' ).show();
                    pages.eq(0).addClass( 'nextPage' );
                    console.log( "init_width: " + thispt.width );
                    thispt.animate_setup( $(this), thispt );
                    $(this).data('callback', $.Callbacks());
                    $(this).data('callback').add( thispt.animate_setup );
                }
            } );
        }),
        animate_setup: ( function( slide, thispt ){

            var currentIndex = slide.find( '.currentPage' ).index();
            var length = slide.children().length;
            currentIndex = ( currentIndex + 1 ) % length;
            var nextIndex = ( currentIndex + 1 ) % length;
            console.log( 'currentIdx: ' + currentIndex + ' nextIdx: ' + nextIndex );


            var currentPage = slide.children().eq( currentIndex );
            var nextPage = slide.children().eq( nextIndex );

            slide.data( 'counter', [0,2]  );
            var width = thispt.width;
            console.log( 'setup_width' + width );

            slide.find( '.nextPage' ).removeClass( 'nextPage' ).hide();
            slide.find( '.currentPage' ).removeClass( 'currentPage' );
            nextPage.transition({ x: width }, 1000, (function(){
                nextPage.addClass('nextPage').show();
            }));
            currentPage.transition({ x:0, delay:0 }, 1000);
            currentPage.addClass( 'currentPage' ).show();


            currentPage.transition({ x: -width, delay:1000 }, 1000, thispt.animate_finish( slide, thispt ));
            nextPage.transition({ x: 0, delay:1000 }, 1000, thispt.animate_finish( slide, thispt ));

            return thispt.animate_setup;

        } ),
        animate_finish: ( function( slide, thispt ){
            return (function(){
                (slide.data('counter'))[0] += 1;
                if ( (slide.data('counter'))[0] == (slide.data('counter'))[1] ){
                    (slide.data('callback')).fire(slide, thispt);
                }
            });
        } ),

        init: (function(){

            var thispt =  this;
            var slideshow = $(".slideshow");

            thispt.resize( thispt );
            $(window).resize( function(){thispt.resize( thispt );});

            // init the animate
            thispt.init_animate( thispt );

        }),
    };

    console.log( "slideshow loaded" );

    return Slideshow;
} );

