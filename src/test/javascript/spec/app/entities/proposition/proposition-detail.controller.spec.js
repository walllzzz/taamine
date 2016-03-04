'use strict';

describe('Controller Tests', function() {

    describe('Proposition Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockProposition, MockDevis, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockProposition = jasmine.createSpy('MockProposition');
            MockDevis = jasmine.createSpy('MockDevis');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Proposition': MockProposition,
                'Devis': MockDevis,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("PropositionDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'taamineApp:propositionUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
