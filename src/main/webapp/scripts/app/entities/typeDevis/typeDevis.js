'use strict';

angular.module('taamineApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('typeDevis', {
                parent: 'entity',
                url: '/typeDeviss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'taamineApp.typeDevis.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/typeDevis/typeDeviss.html',
                        controller: 'TypeDevisController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('typeDevis');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('typeDevis.detail', {
                parent: 'entity',
                url: '/typeDevis/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'taamineApp.typeDevis.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/typeDevis/typeDevis-detail.html',
                        controller: 'TypeDevisDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('typeDevis');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'TypeDevis', function($stateParams, TypeDevis) {
                        return TypeDevis.get({id : $stateParams.id});
                    }]
                }
            })
            .state('typeDevis.new', {
                parent: 'typeDevis',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/typeDevis/typeDevis-dialog.html',
                        controller: 'TypeDevisDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    libelle: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('typeDevis', null, { reload: true });
                    }, function() {
                        $state.go('typeDevis');
                    })
                }]
            })
            .state('typeDevis.edit', {
                parent: 'typeDevis',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/typeDevis/typeDevis-dialog.html',
                        controller: 'TypeDevisDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['TypeDevis', function(TypeDevis) {
                                return TypeDevis.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('typeDevis', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('typeDevis.delete', {
                parent: 'typeDevis',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/typeDevis/typeDevis-delete-dialog.html',
                        controller: 'TypeDevisDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['TypeDevis', function(TypeDevis) {
                                return TypeDevis.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('typeDevis', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
