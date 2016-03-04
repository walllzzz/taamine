'use strict';

angular.module('taamineApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('chpListeDeroulante', {
                parent: 'entity',
                url: '/chpListeDeroulantes',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'taamineApp.chpListeDeroulante.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/chpListeDeroulante/chpListeDeroulantes.html',
                        controller: 'ChpListeDeroulanteController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('chpListeDeroulante');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('chpListeDeroulante.detail', {
                parent: 'entity',
                url: '/chpListeDeroulante/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'taamineApp.chpListeDeroulante.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/chpListeDeroulante/chpListeDeroulante-detail.html',
                        controller: 'ChpListeDeroulanteDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('chpListeDeroulante');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'ChpListeDeroulante', function($stateParams, ChpListeDeroulante) {
                        return ChpListeDeroulante.get({id : $stateParams.id});
                    }]
                }
            })
            .state('chpListeDeroulante.new', {
                parent: 'chpListeDeroulante',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/chpListeDeroulante/chpListeDeroulante-dialog.html',
                        controller: 'ChpListeDeroulanteDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    valeur: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('chpListeDeroulante', null, { reload: true });
                    }, function() {
                        $state.go('chpListeDeroulante');
                    })
                }]
            })
            .state('chpListeDeroulante.edit', {
                parent: 'chpListeDeroulante',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/chpListeDeroulante/chpListeDeroulante-dialog.html',
                        controller: 'ChpListeDeroulanteDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['ChpListeDeroulante', function(ChpListeDeroulante) {
                                return ChpListeDeroulante.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('chpListeDeroulante', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('chpListeDeroulante.delete', {
                parent: 'chpListeDeroulante',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/chpListeDeroulante/chpListeDeroulante-delete-dialog.html',
                        controller: 'ChpListeDeroulanteDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['ChpListeDeroulante', function(ChpListeDeroulante) {
                                return ChpListeDeroulante.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('chpListeDeroulante', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
