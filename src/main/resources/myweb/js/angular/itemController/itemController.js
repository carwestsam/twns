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

    $scope.cart = false;
    $scope.toggleCart = function(){
        $scope.cart = !$scope.cart;
        $scope.refreshCart();
    }

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

    $scope.mycart={
        List : [],
        totPrice : 50.00
    };

    $scope.refreshCart = function(){
        $http.get("/mall/cart").success( function(response){
            console.log("success get cart inform");
            console.log(response);
            $scope.mycart.List = response;
            $scope.mycart.totPrice = 0;
            console.log("*******Ids************");
            console.log( $scope.mycart.List );
            var item;
            angular.forEach( $scope.mycart.List, function( value, key ){
                $scope.mycart.totPrice += value.price * value.num;
                console.log( value.name, value.price, value.num );
            } );
            console.log($scope.mycart.totPrice);
            for ( item in $scope.mycart.List ){
                console.log( $scope.mycart.List[item].id );
            }
        } ).error( function(e){
            console.log("refreshCart failed");
        } );
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

    $scope.deleteByIdFromMall = function( id ){
        console.log("delete by Id");
        console.log(id);
        var postData = { Id : id };
        $scope.postFactory.setPropertys(
            "deleteByIdFromMall", 
            postData,
            function(){
                (function(func){
                    func();
                })($scope.refresh);
                console.log("yes");
            } ).run();
    };

    $scope.addToCartOne = function( id ){
        console.log("add To Cart One");
        console.log ( id );
        var postData = { Id: id, Num: 1 };
        $scope.postFactory.setPropertys(
            "addToCart",
            postData,
            function(){
                $scope.refreshCart();
                console.log("addToCartOne success");
            }).run();
    };

    $scope.postFactory = {
        setPropertys : function( _instr, _data, _callback ){
            this.instr = _instr;
            this.data = _data;
            this._callbackfunc = _callback;
            return this;
        },
        instr : "",
        data : "{}",
        callback : function(callbackFunc){
            _callbackfunc = function(){
                console.log("_callbackfunc not defined 2");
            };
            _callbackfunc = callbackFunc;
            console.log( callbackFunc );
            return this;
        },
        _callbackfunc : function(){
            console.log("_callbackfunc not defined");
        },
        run : function(){
            var postData = {
                "instruct" : this.instr,
                "data" : this.data
            };
            console.log(postData);
            $http
                .post("/mall/instr", postData)
                .success( this._callbackfunc )
                .error( function (data, status, headers, config){
                    console.log("failed 32");
                } );
        },
    };
}
