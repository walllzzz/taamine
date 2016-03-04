'use strict';

angular.module('taamineApp').controller('PropositionDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Proposition', 'Devis', 'User',
        function($scope, $stateParams, $uibModalInstance, entity, Proposition, Devis, User) {

        $scope.proposition = entity;
        $scope.deviss = Devis.query();
        $scope.users = User.query();
        $scope.load = function(id) {
            Proposition.get({id : id}, function(result) {
                $scope.proposition = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('taamineApp:propositionUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.proposition.id != null) {
                Proposition.update($scope.proposition, onSaveSuccess, onSaveError);
            } else {
                Proposition.save($scope.proposition, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForDateProposition = {};

        $scope.datePickerForDateProposition.status = {
            opened: false
        };

        $scope.datePickerForDatePropositionOpen = function($event) {
            $scope.datePickerForDateProposition.status.opened = true;
        };
}]);
