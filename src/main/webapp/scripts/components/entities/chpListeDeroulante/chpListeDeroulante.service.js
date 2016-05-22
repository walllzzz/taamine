'use strict';

angular.module('taamineApp')
    .factory('ChpListeDeroulante', function ($resource, DateUtils) {
        return $resource('api/chpListeDeroulantes/:id', {}, {
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
    .factory('ChpListeDeroulanteChamp', function ($resource, DateUtils) {
        return $resource('api/chpListeDeroulantes/champ/:id', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });;
