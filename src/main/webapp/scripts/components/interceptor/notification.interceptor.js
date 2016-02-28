 'use strict';

angular.module('taamineApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-taamineApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-taamineApp-params')});
                }
                return response;
            }
        };
    });
