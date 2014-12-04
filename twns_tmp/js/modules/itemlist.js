/*
 * itemlist.js
 * Copyright (C) 2014 carwest <carwest@carwest-UDISK>
 *
 * Distributed under terms of the MIT license.
 */

define( ["jquery", "underscore"], function($, _){
    $(document).ready( function(){
        dataToView( disks );
        console.log( "itemlist loaded" );
    } );

    function dataToView( data ){
        //$('#itemlist').empty();
        var diskDivs = _.map( data, function( disk ){
            return diskDiv( disk );
        } );
        _.each( diskDivs, function(div){
            $("#itemlist").append( div );
        } );
        $("#itemlist").children().each( function(){
           //$(this).height ( $(this).width() / 0.744 + 'px');
        } );
    };

    function diskDiv( disk ){
        return $("<div>")
            .attr( "class", "item" )
            .append( diskImg(disk.img) )
            .append( diskNote( disk.name, disk.desc, disk.price ) );
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


