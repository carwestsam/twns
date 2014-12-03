/*
 * main.js
 * Copyright (C) 2014 carwest <carwest@carwest-UDISK>
 *
 * Distributed under terms of the MIT license.
 */

require.config({
    paths:{
        "jquery" : "libs/jquery/jquery-2.1.1.min" ,
        "underscore" : "libs/underscore/underscore-min" ,
        "a" : "a",
        "slide" : "modules/slide_require",
        "jquery.transit" : "libs/jquery/jquery.transit.min",
    },
    skim: {
        "slide" : {
            deps: ["jquery"],
            exports: "Slide"
        },
        "jquery.transit" : ['jquery'],
    }
});

require(["a"]);
require(["slide"]);
require(["jquery.transit"]);

