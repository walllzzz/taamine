'use strict';

angular.module('taamineApp')
    .factory('TypeDevis', function ($resource, DateUtils) {
        return $resource('api/typeDeviss/:id', {}, {
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
