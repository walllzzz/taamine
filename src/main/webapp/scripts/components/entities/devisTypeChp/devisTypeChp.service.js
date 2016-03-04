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
