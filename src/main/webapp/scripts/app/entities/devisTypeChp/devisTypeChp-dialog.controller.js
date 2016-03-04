'use strict';

angular.module('taamineApp').controller('DevisTypeChpDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'DevisTypeChp', 'TypeDevis', 'Champ',
        function($scope, $stateParams, $uibModalInstance, entity, DevisTypeChp, TypeDevis, Champ) {

        $scope.devisTypeChp = entity;
        $scope.typedeviss = TypeDevis.query();
        $scope.champs = Champ.query();
        $scope.load = function(id) {
            DevisTypeChp.get({id : id}, function(result) {
                $scope.devisTypeChp = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('taamineApp:devisTypeChpUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.devisTypeChp.id != null) {
                DevisTypeChp.update($scope.devisTypeChp, onSaveSuccess, onSaveError);
            } else {
                DevisTypeChp.save($scope.devisTypeChp, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
