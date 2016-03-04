'use strict';

describe('Controller Tests', function() {

    describe('Devis Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockDevis, MockTypeDevis, MockValeurChamp, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockDevis = jasmine.createSpy('MockDevis');
            MockTypeDevis = jasmine.createSpy('MockTypeDevis');
            MockValeurChamp = jasmine.createSpy('MockValeurChamp');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Devis': MockDevis,
                'TypeDevis': MockTypeDevis,
                'ValeurChamp': MockValeurChamp,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("DevisDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'taamineApp:devisUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
