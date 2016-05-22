'use strict';

angular.module('taamineApp')
    .controller('DevisTypeChpByTypeDevisController', function ($rootScope, $scope, $state,$filter,$stateParams,Principal, DevisTypeChpByTypeDevis,ValeurChamp,Champ,User,Devis,DevisUser,ChpListeDeroulanteChamp) {

        $scope.devisTypeChps = [];
        $scope.devis={
        		id: '',
        		userId:"",
        		typeDevisId:"",
        		dateDevis:	'2016-03-08T00:00+01:00[Europe/Paris]'
        };
    
    	//get user
    	$scope.user = function (login) {
            User.get({login: login}, function(result) {
            	// get devis from service 
             	$scope.devis=DevisUser.getDevis();
            	$scope.devis.userId = result.id;
            	DevisUser.setDevis($scope.devis);
            	DevisUser.setDevisChamps($scope.devisTypeChps);
            	DevisUser.saveDevis();
             	//$scope.saveDevis();
            	$state.go('mesdevis');
            });
        };
       
        
        $scope.load = function(id) {
        	DevisTypeChpByTypeDevis.getByIdTypeDevis({id: id},function(result) {
        		$scope.devis.typeDevisId=id;
                var devisTypeChpsV = result;
                devisTypeChpsV.forEach(function(devistypeChp) {
                	
            	   Champ.get({id: devistypeChp.champId}, function(result) {
            		   devistypeChp.champ = result;
            		   devistypeChp.valeurChamp={};
            		   devistypeChp.valeurChamp.champId=result.id;
            		   devistypeChp.valeurChamp.valValeur="";
            		   devistypeChp.champ.list=[];
            		   ChpListeDeroulanteChamp.query({id:devistypeChp.champId},function(result){
                		   result.forEach(function(listChamp){
                			   devistypeChp.champ.list.push(listChamp);
                		   });
                		   $scope.devisTypeChps.push(devistypeChp);
                		   console.log(devistypeChp);
                	   });
            		  
                   });            	   
            	  
            	});             
            });
        };
//        var onSaveSuccess = function (result) {
//            
//            $scope.isSaving = false;
//           
//        };

//        var onSaveSuccessDevis = function (result) {
//        	$scope.devis=result;
//            $scope.isSaving = false;
//           
//            $scope.devisTypeChps.forEach(function(devistypeChp) {
//            	
//            	devistypeChp.valeurChamp.devisId=$scope.devis.id;
//            
//            	ValeurChamp.save(devistypeChp.valeurChamp,onSaveSuccess, onSaveError);
//            });
            //$state.go("register", { devis: $scope.devis });
//        };

//        var onSaveError = function (result) {
//            $scope.isSaving = false;
//        };
        
        $scope.save = function () {
        	console.log("hello world");
        	Principal.identity().then(function(account) {
        		
        		if(!account){
        			 DevisUser.setValid(true);
        			 DevisUser.setDevis($scope.devis);
        			 $state.go("login", { devis: $scope.devis });
        		}
        		else {
        			$scope.currentAccount = account;
        			// get user ID
                    $scope.user($scope.currentAccount.login);                   
                   
        		}                
            });       
            
        };
//        $scope.saveDevis = function(){
//        	
//        	if ($scope.devis.id != null) {
//                Devis.update($scope.devis, onSaveSuccessDevis, onSaveError);
//            } else {
//            	Devis.save($scope.devis, onSaveSuccessDevis, onSaveError);        	                        
//            }
//        }
        $scope.load($stateParams.id);
        //$scope.loadAll(id);


        $scope.refresh = function (id) {
            $scope.load(id);
            $scope.clear();
        };
        
//        $scope.saveValCham = function () {        	
//            $scope.isSaving = true;
//            if ($scope.valeurChamp.id != null) {
//                ValeurChamp.update($scope.valeurChamp, onSaveSuccess, onSaveError);
//            } else {
//                ValeurChamp.save($scope.valeurChamp, onSaveSuccess, onSaveError);
//            }
//        };
        console.log("calling devistypechampBYtypedevis");
        console.log("previous "+$rootScope.previousStateName);
        if ($rootScope.previousStateName === 'login' && DevisUser.getValid()==true) {
        	console.log("previous login "+ DevisUser.getValid());
        	$scope.save(); 
            
        } 
       
    });
