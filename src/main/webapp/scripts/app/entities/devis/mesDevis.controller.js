'use strict';

angular.module('taamineApp')
    .controller('MesDevisController', function ($scope, $state, Devis, MesDevis,Principal, User) {

        $scope.deviss = [];
        $scope.loadAll = function(idUser) {
        	MesDevis.query({id:idUser},function(result) {
        		console.log("getting result");
               $scope.deviss = result;
            });
        };
        
      //get user
    	$scope.user = function (login) {
            User.get({login: login}, function(result) {
            	console.log("user id +"+result.id);
            	$scope.loadAll(result.id);             	
            });
        };
        $scope.loadMesDevis = function(){
        	Principal.identity().then(function(account) {        
    		if(!account){    			 
    			 $state.go("login", { devis: $scope.devis });
    		}
    		else {
    			$scope.currentAccount = account;
    			// get user ID
                $scope.user($scope.currentAccount.login);                
               
    		}                
        });      
        };
        $scope.loadMesDevis();
        $scope.refresh = function () {
            $scope.loadMesDevis();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.devis = {
                dateDevis: null,
                id: null
            };
        };
    });
