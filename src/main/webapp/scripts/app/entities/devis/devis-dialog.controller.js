'use strict';

angular.module('taamineApp').controller('DevisDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Devis', 'TypeDevis', 'ValeurChamp', 'User',
        function($scope, $stateParams, $uibModalInstance, entity, Devis, TypeDevis, ValeurChamp, User) {

        $scope.devis = entity;
        $scope.typedeviss = TypeDevis.query();
        $scope.valeurchamps = ValeurChamp.query();
        $scope.users = User.query();
        $scope.load = function(id) {
            Devis.get({id : id}, function(result) {
                $scope.devis = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('taamineApp:devisUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.devis.id != null) {
                Devis.update($scope.devis, onSaveSuccess, onSaveError);
            } else {
            	console.log("date" +$scope.devis.dateDevis);
            	console.log($scope.devis.dateDevis);
                Devis.save($scope.devis, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForDateDevis = {};

        $scope.datePickerForDateDevis.status = {
            opened: false
        };

        $scope.datePickerForDateDevisOpen = function($event) {
            $scope.datePickerForDateDevis.status.opened = true;
        };
}]);
