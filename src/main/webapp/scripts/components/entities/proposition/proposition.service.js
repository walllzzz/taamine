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
    });
