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
            $(this).height ( $(this).width() / 0.618 + 'px');
        } )
    };

    function diskDiv( disk ){
        return $("<div>")
            .attr( "class", "item" )
            .append( diskTitle(disk.name) )
            .append( diskImg( disk.img ) )
            .append( diskDesc(disk.desc) );
    };

    function diskTitle( title ){
        return $("<h4>").html(title);
    };
    function diskImg(img){
        return $("<img>").attr("src", img);
    };
    function diskDesc( desc ){
        return $("<p>").html(desc);
    }
} );


