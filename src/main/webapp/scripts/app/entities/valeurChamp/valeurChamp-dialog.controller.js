'use strict';

angular.module('taamineApp').controller('ValeurChampDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ValeurChamp', 'Devis', 'Champ',
        function($scope, $stateParams, $uibModalInstance, entity, ValeurChamp, Devis, Champ) {

        $scope.valeurChamp = entity;
        $scope.deviss = Devis.query();
        $scope.champs = Champ.query();
        $scope.load = function(id) {
            ValeurChamp.get({id : id}, function(result) {
                $scope.valeurChamp = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('taamineApp:valeurChampUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.valeurChamp.id != null) {
                ValeurChamp.update($scope.valeurChamp, onSaveSuccess, onSaveError);
            } else {
                ValeurChamp.save($scope.valeurChamp, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
