'use strict';

angular.module('taamineApp')
    .controller('MesPropositionController', function ($scope, $state, MesProposition,Principal, User) {

        $scope.propositions = [];
        $scope.loadAll = function(id) {
        	MesProposition.query({id:id},function(result) {
               $scope.propositions = result;
            });
        };
        //get user
    	$scope.user = function (login) {
            User.get({login: login}, function(result) {
            	console.log("user id +"+result.id);
            	$scope.loadAll(result.id);             	
            });
        };
        $scope.loadPropositions = function(){
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
        }
        $scope.loadPropositions();
        $scope.refresh = function () {
        	 $scope.loadPropositions();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.proposition = {
                prix: null,
                dateProposition: null,
                id: null
            };
        };
    });
