"use strict"
function itemController($scope, $http){
    $scope.load = function(){
        console.log("init");
        $scope.refresh();
        console.log("init success");
    }

    $scope.person="me";
    $scope.phones=[
        {"name":"nexus"},
        {"name":"moto"},
        {"name":"iphone"}
    ];
    $scope.addBar = false;
    $scope.toggleAddBar = function(){
        $scope.addBar = !$scope.addBar;
    };

    /*
    $http.get("fakedata.json").success( function (response){
        $scope.disks = response;
    });
    */

    $scope.refresh =function(){
        $http.get("/mall").success( function(response){
            console.log("success at disks");
            console.log(response);
            $scope.disks = response;
            console.log("============");
        } ).error(function(e){
            console.log("failed");
            console.log(e);
        });
    };

    $scope.submitAdd = function(){
        console.log( $scope.add );
        $scope.addBar = !$scope.addBar;
        
        var postData = {
            "instruct" : "addToMall",
            "data" : $scope.add
        }
        $http
            .post("/mall/instr", postData)
            .success(function(data, status, headers, config){
                console.log( "add2Cart success" );
                $scope.refresh();
            })
            .error( function(data, status, headers, config){
                console.log( "add2Cart failed" );
            } );
    };

    $scope.deleteById = function( id ){
        console.log("delete by Id");
        console.log(id);
    };

    $scope.postFactory = {
        instr : "",
        data : "{}",
        callback : (function(callbackFunc){
            console.log( callbackFunc );
        }),
    };
}
