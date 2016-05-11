'use strict';

angular.module('taamineApp')
    .factory('Devis', function ($resource, DateUtils) {
        return $resource('api/deviss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.dateDevis = DateUtils.convertDateTimeFromServer(data.dateDevis);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    })
     .factory('MesDevis', function ($resource, DateUtils){
    	 return $resource('api/mesdevis/:id', {}, {
             'query': { method: 'GET', isArray: true}           
         });
     }
     )
    .factory('DevisUser', function ($resource, DateUtils,Devis,ValeurChamp) {
    	var devis={};
    	var valid=false;
    	var devisTypeChps={};
    	var onSaveSuccess = function (result) {                                      
          };
        var onSaveError = function (result) {                                          
          }; 
    	var onSaveSuccessDevis = function (result) {
         	 devis=result;                    
             devisTypeChps.forEach(function(dtChp) {             	
            	dtChp.valeurChamp.devisId=devis.id;             
             	ValeurChamp.save(dtChp.valeurChamp,onSaveSuccess, onSaveError);
             });
             //$state.go("register", { devis: $scope.devis });
         };
    	return {
            getDevis: function() {
                return devis;
            },
            setDevis: function(value) {
            	devis = value;
            },
            setValid: function(value){
            	valid=value;
            },
            getValid: function(){
            	return valid;
            },
            setDevisChamps: function(value){
            	devisTypeChps=value;
            },
            getDevisChamps: function(){
            	return devisTypeChps;
            },            
            saveDevis: function(){
            	if (devis.id != null) {
                    Devis.update(devis, onSaveSuccessDevis, onSaveError);
                } else {
                	Devis.save(devis, onSaveSuccessDevis, onSaveError);        	                        
                }
            }
        }
    });
