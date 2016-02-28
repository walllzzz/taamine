'use strict';

angular.module('taamineApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


