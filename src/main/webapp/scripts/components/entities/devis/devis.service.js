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
    });
