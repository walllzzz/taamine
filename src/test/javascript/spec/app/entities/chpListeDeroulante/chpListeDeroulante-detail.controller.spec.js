'use strict';

describe('Controller Tests', function() {

    describe('ChpListeDeroulante Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockChpListeDeroulante, MockChamp;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockChpListeDeroulante = jasmine.createSpy('MockChpListeDeroulante');
            MockChamp = jasmine.createSpy('MockChamp');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ChpListeDeroulante': MockChpListeDeroulante,
                'Champ': MockChamp
            };
            createController = function() {
                $injector.get('$controller')("ChpListeDeroulanteDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'taamineApp:chpListeDeroulanteUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
