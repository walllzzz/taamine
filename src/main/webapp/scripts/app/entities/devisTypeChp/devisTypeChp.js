'use strict';

angular.module('taamineApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('devisTypeChp', {
                parent: 'entity',
                url: '/devisTypeChps',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'taamineApp.devisTypeChp.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/devisTypeChp/devisTypeChps.html',
                        controller: 'DevisTypeChpController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('devisTypeChp');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('devisTypeChpByDevisType', {
                parent: 'entity',
                url: '/devisTypeChps/devisType/{id}',
                data: {
                    //authorities: ['ROLE_USER'],
                    authorities: [],
                    pageTitle: 'taamineApp.devisTypeChp.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/devisTypeChp/SaisieDevis.html',
                        controller: 'DevisTypeChpByTypeDevisController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('devisTypeChp');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
//                ,
//                    devisTypeChps: ['$stateParams', 'DevisTypeChp', function($stateParams, DevisTypeChp) {
//                        return DevisTypeChpByTypeDevis.getByIdTypeDevis({id : $stateParams.id});
//                    }]
                }
            })
            .state('devisTypeChp.detail', {
                parent: 'entity',
                url: '/devisTypeChp/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'taamineApp.devisTypeChp.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/devisTypeChp/devisTypeChp-detail.html',
                        controller: 'DevisTypeChpDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('devisTypeChp');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'DevisTypeChp', function($stateParams, DevisTypeChp) {
                        return DevisTypeChp.get({id : $stateParams.id});
                    }]
                }
            })
            .state('devisTypeChp.new', {
                parent: 'devisTypeChp',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/devisTypeChp/devisTypeChp-dialog.html',
                        controller: 'DevisTypeChpDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    actif: null,
                                    obligatoire: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('devisTypeChp', null, { reload: true });
                    }, function() {
                        $state.go('devisTypeChp');
                    })
                }]
            })
            .state('devisTypeChp.edit', {
                parent: 'devisTypeChp',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/devisTypeChp/devisTypeChp-dialog.html',
                        controller: 'DevisTypeChpDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['DevisTypeChp', function(DevisTypeChp) {
                                return DevisTypeChp.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('devisTypeChp', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('devisTypeChp.delete', {
                parent: 'devisTypeChp',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/devisTypeChp/devisTypeChp-delete-dialog.html',
                        controller: 'DevisTypeChpDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['DevisTypeChp', function(DevisTypeChp) {
                                return DevisTypeChp.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('devisTypeChp', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
