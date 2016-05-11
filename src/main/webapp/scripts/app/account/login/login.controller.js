'use strict';

angular.module('taamineApp')
    .controller('LoginController', function ($rootScope, $scope, $state,$stateParams, $timeout, Auth) {
        $scope.user = {};
        $scope.errors = {};

        $scope.rememberMe = true;
        $timeout(function (){angular.element('[ng-model="username"]').focus();});
        $scope.login = function (event) {
            event.preventDefault();
            Auth.login({
                username: $scope.username,
                password: $scope.password,
                rememberMe: $scope.rememberMe
            }).then(function () {
                $scope.authenticationError = false;
                console.log("login called");
                console.log($rootScope);
                console.log($scope);
                console.log($state.devis);
                console.log($stateParams.devis);
                console.log($rootScope.previousStateName);
                if ($rootScope.previousStateName === 'register') {
                    $state.go('home');
                } else {
                    $rootScope.back();
                }
            }).catch(function () {
                $scope.authenticationError = true;
            });
        };
    });
