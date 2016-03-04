'use strict';

angular.module('taamineApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('champ', {
                parent: 'entity',
                url: '/champs',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'taamineApp.champ.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/champ/champs.html',
                        controller: 'ChampController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('champ');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('champ.detail', {
                parent: 'entity',
                url: '/champ/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'taamineApp.champ.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/champ/champ-detail.html',
                        controller: 'ChampDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('champ');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Champ', function($stateParams, Champ) {
                        return Champ.get({id : $stateParams.id});
                    }]
                }
            })
            .state('champ.new', {
                parent: 'champ',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/champ/champ-dialog.html',
                        controller: 'ChampDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    chpLibelle: null,
                                    chpDansListe: null,
                                    chpType: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('champ', null, { reload: true });
                    }, function() {
                        $state.go('champ');
                    })
                }]
            })
            .state('champ.edit', {
                parent: 'champ',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/champ/champ-dialog.html',
                        controller: 'ChampDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Champ', function(Champ) {
                                return Champ.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('champ', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('champ.delete', {
                parent: 'champ',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/champ/champ-delete-dialog.html',
                        controller: 'ChampDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Champ', function(Champ) {
                                return Champ.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('champ', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
