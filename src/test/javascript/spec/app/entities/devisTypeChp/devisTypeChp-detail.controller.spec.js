'use strict';

describe('Controller Tests', function() {

    describe('DevisTypeChp Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockDevisTypeChp, MockTypeDevis, MockChamp;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockDevisTypeChp = jasmine.createSpy('MockDevisTypeChp');
            MockTypeDevis = jasmine.createSpy('MockTypeDevis');
            MockChamp = jasmine.createSpy('MockChamp');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'DevisTypeChp': MockDevisTypeChp,
                'TypeDevis': MockTypeDevis,
                'Champ': MockChamp
            };
            createController = function() {
                $injector.get('$controller')("DevisTypeChpDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'taamineApp:devisTypeChpUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
