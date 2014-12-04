/*
 * itemlist.js
 * Copyright (C) 2014 carwest <carwest@carwest-UDISK>
 *
 * Distributed under terms of the MIT license.
 */

define( ["jquery", "underscore"], function($, _){
    $(document).ready( function(){
        dataToView( disks );
        $('#filter').bind( 'keyup', function(){
            filter();
        } );
    } );

    function filter(){
        var keyword = $('#filter').val();
        var items = $( '#itemlist' ).children();
        items.each( function(){
            console.log( $(this).data('name') );
            if ( $(this).data( 'name' ).toLowerCase().indexOf( keyword ) == -1 ){
                $(this).hide();
            }else {
                $(this).show();
            }
        } );
    }

    function dataToView( data ){
        //$('#itemlist').empty();
        var diskDivs = _.map( data, function( disk ){
            return diskDiv( disk );
        } );
        _.each( diskDivs, function(div){
            $("#itemlist").append( div );
        } );
        $("#itemlist").children().each( function(){
            $(this).on('mouseover', function(){
                console.log( 'mousemover' );
                if ( $(this).data('scale') == 0 ){
                    $(this).find('.close').transition( {scale:1.0}, 200 );
                    $(this).data( 'scale', 1 );
                }
                //$(this).find( '.close' ).show();
            });
            $(this).on('mouseout', function(){
                console.log( 'mousemleft' );
                if ( $(this).data('scale') == 1 ){
                    $(this).find('.close').transition( {scale:0}, 200 );
                    $(this).data( 'scale', 0 );
                }
                //$(this).find( '.close' ).hide();
            });
            var closebutton = $(this).find('.close');

            $(this).find('.close').transition( {scale:0}, 200 );
            $(this).data('scale', 0);

            closebutton.on( 'click', function(){
                var thispt = this;
                $(thispt).closest('.item').transition( {scale:0}, 500, function(){
                    $(thispt).closest('.item').remove();
                } )
                //$(this).closest('.item').remove();
            } );
           //$(this).height ( $(this).width() / 0.744 + 'px');
        } );
    };

    function diskDiv( disk ){
        return $("<div>")
            .attr( "class", "item" )
            .append( clossButton() )
            .append( diskImg(disk.img) )
            .append( diskNote( disk.name, disk.desc, disk.price ) )
            .data( 'name', disk.name );
    };

    function diskImg( img ){
        return $("<img>").attr("src", img);
    }
    function diskNote( name, desc, price ){
        return $('<div>')
            .attr( 'class', 'note' )
            .append( diskTitle( name ) )
            .append( diskDesc( desc ) )
            .append( diskPrice( price ) );
    }
    function clossButton(){
        return $("<div>").attr( 'class', 'close' );
    }
    function diskTitle( title ){
        return $("<div>")
            .attr( 'class', 'title' )
            .html(title);
    };
    function diskDesc( desc ){
        return $("<div>")
            .attr('class', 'desc')
            .html(desc);
    };
    function diskPrice( price ){
        return $("<div>")
            .attr('class', 'price')
            .html(price);
    };
} );

