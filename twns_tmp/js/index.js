/*
 * index.js
 * Copyright (C) 2014 carwest <carwest@carwest-UDISK>
 *
 * Distributed under terms of the MIT license.
 */



require.config({
    paths:{
        "jquery" : "libs/jquery/jquery-2.1.1.min" ,
        "underscore" : "libs/underscore/underscore-min" ,
        "slide" : "modules/slide_require",
        "itemlist" : "modules/itemlist",
        "jquery.transit" : "libs/jquery/jquery.transit.min",
    },
    skim: {
        "slide" : {
            deps: ["jquery"],
            exports: "Slide"
        },
        "itemlist" :{
            deps: ["jquery", "underscore"],
            exports: "itemlist"
        },
        "jquery.transit" : ['jquery'],
    }
});

require(["slide"]);
require(["itemlist"]);
require(["underscore"]);
require(["jquery.transit"]);

