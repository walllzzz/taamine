'use strict';

angular.module('taamineApp')
    .factory('Proposition', function ($resource, DateUtils) {
        return $resource('api/propositions/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.dateProposition = DateUtils.convertDateTimeFromServer(data.dateProposition);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    })
    .factory('PropositionDevis', function ($resource, DateUtils) {
        return $resource('api/propositionsDevis/:id', {}, {
            'query': { method: 'GET', isArray: true}            
        });
    })
    .factory('MesProposition', function ($resource, DateUtils) {
        return $resource('api/mespropositions/:id', {}, {
            'query': { method: 'GET', isArray: true}            
        });
    })
    .factory('MesPropositionEntreprise', function ($resource, DateUtils) {
        return $resource('api/mespropositionsEntreprise/:id', {}, {
            'query': { method: 'GET', isArray: true}            
        });
    }) ;
