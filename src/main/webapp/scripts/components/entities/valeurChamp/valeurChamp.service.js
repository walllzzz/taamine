'use strict';

angular.module('taamineApp')
    .factory('ValeurChamp', function ($resource, DateUtils) {
        return $resource('api/valeurChamps/:id', {}, {
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
    })
    .factory('ValeurChampDevis', function ($resource, DateUtils) {
        return $resource('api/valeurChampsDevis/:id', {}, {
            'query': { method: 'GET', isArray: true}           
        });
    });
