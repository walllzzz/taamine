'use strict';

angular.module('taamineApp')
    .factory('DevisTypeChp', function ($resource, DateUtils) {
        return $resource('api/devisTypeChps/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
angular.module('taamineApp')
	.factory('DevisTypeChpByTypeDevis', function ($resource) {
        return $resource('api/devisTypeChps/devisType/:id', {}, {
            'getByIdTypeDevis': { method: 'GET', isArray: true}
        });
    });

//function ($http) {
//        return {
//        	$resource('api/devisTypeChps/devisType/:id/', {id:'@id'}, {
//        
//            'getByIdTypeDevis': { method: 'GET', 
//            	transformResponse: function (data) {
//                data = angular.fromJson(data);
//                return data;
//            }, isArray: true}
//        });
//        	getByIdTypeDevis: function (id) {
//
//                  return $http.get('api/devisTypeChps/devisType/:id', {params: {id: id}}).then(function (response) {
//                      return response.data;
//                  });
//              }	
//        }
//    });
