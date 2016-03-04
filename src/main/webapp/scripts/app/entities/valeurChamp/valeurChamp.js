'use strict';

angular.module('taamineApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('valeurChamp', {
                parent: 'entity',
                url: '/valeurChamps',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'taamineApp.valeurChamp.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/valeurChamp/valeurChamps.html',
                        controller: 'ValeurChampController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('valeurChamp');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('valeurChamp.detail', {
                parent: 'entity',
                url: '/valeurChamp/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'taamineApp.valeurChamp.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/valeurChamp/valeurChamp-detail.html',
                        controller: 'ValeurChampDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('valeurChamp');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'ValeurChamp', function($stateParams, ValeurChamp) {
                        return ValeurChamp.get({id : $stateParams.id});
                    }]
                }
            })
            .state('valeurChamp.new', {
                parent: 'valeurChamp',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/valeurChamp/valeurChamp-dialog.html',
                        controller: 'ValeurChampDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    valValeur: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('valeurChamp', null, { reload: true });
                    }, function() {
                        $state.go('valeurChamp');
                    })
                }]
            })
            .state('valeurChamp.edit', {
                parent: 'valeurChamp',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/valeurChamp/valeurChamp-dialog.html',
                        controller: 'ValeurChampDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ValeurChamp', function(ValeurChamp) {
                                return ValeurChamp.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('valeurChamp', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('valeurChamp.delete', {
                parent: 'valeurChamp',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/valeurChamp/valeurChamp-delete-dialog.html',
                        controller: 'ValeurChampDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ValeurChamp', function(ValeurChamp) {
                                return ValeurChamp.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('valeurChamp', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
