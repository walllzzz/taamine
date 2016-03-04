'use strict';

describe('Controller Tests', function() {

    describe('ValeurChamp Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockValeurChamp, MockDevis, MockChamp;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockValeurChamp = jasmine.createSpy('MockValeurChamp');
            MockDevis = jasmine.createSpy('MockDevis');
            MockChamp = jasmine.createSpy('MockChamp');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ValeurChamp': MockValeurChamp,
                'Devis': MockDevis,
                'Champ': MockChamp
            };
            createController = function() {
                $injector.get('$controller')("ValeurChampDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'taamineApp:valeurChampUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
