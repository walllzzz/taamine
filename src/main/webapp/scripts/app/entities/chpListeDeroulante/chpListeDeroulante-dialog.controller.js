'use strict';

angular.module('taamineApp').controller('ChpListeDeroulanteDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ChpListeDeroulante', 'Champ',
        function($scope, $stateParams, $uibModalInstance, entity, ChpListeDeroulante, Champ) {

        $scope.chpListeDeroulante = entity;
        $scope.champs = Champ.query();
        $scope.load = function(id) {
            ChpListeDeroulante.get({id : id}, function(result) {
                $scope.chpListeDeroulante = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('taamineApp:chpListeDeroulanteUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.chpListeDeroulante.id != null) {
                ChpListeDeroulante.update($scope.chpListeDeroulante, onSaveSuccess, onSaveError);
            } else {
                ChpListeDeroulante.save($scope.chpListeDeroulante, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
