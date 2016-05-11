'use strict';

angular.module('taamineApp').controller('PropositionDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Proposition', 'Devis', 'User',
        function($scope, $stateParams, $uibModalInstance, entity, Proposition,Principal, Devis, User) {

        $scope.proposition = entity;
        console.log(User);
        $scope.users = User.query();
        //if devisId is specified else on récupére tout
        //get user

        if($scope.proposition.devisId){
        	$scope.deviss=[Devis.get({id:$scope.proposition.devisId})];
//        	Principal.getUserId().then(function(id){
//        		console.get("prinncipal user id  "+id);
//        		$scope.proposition.entrepriseId=id;
//        	});
        	console.log("devis 0");
        	console.log($scope.deviss[0]);
        	//$scope.proposition.devisId=$scope.deviss[0].id;
        }else 
        	$scope.deviss = Devis.query();
       
        $scope.load = function(id) {
            Proposition.get({id : id}, function(result) {
                $scope.proposition = result;
            });
        };
        console.log('proposition');
        console.log($scope.proposition);
        

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
