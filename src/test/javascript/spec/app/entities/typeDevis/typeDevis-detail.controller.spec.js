'use strict';

describe('Controller Tests', function() {

    describe('TypeDevis Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockTypeDevis;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockTypeDevis = jasmine.createSpy('MockTypeDevis');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'TypeDevis': MockTypeDevis
            };
            createController = function() {
                $injector.get('$controller')("TypeDevisDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'taamineApp:typeDevisUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
