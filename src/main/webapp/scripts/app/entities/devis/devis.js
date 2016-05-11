'use strict';

angular.module('taamineApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('devis', {
                parent: 'entity',
                url: '/deviss',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'taamineApp.devis.home.title'
       
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/devis/deviss.html',
                        controller: 'DevisController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('devis');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
             .state('mesdevis', {
                parent: 'entity',
                url: '/mesdevis',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'taamineApp.devis.home.title'
       
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/devis/deviss.html',
                        controller: 'MesDevisController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('devis');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('devisEntreprise', {
                parent: 'entity',
                url: '/devisEntreprise',
                data: {
                    authorities: ['ROLE_COMPANY'],
                    pageTitle: 'taamineApp.devis.home.title'
       
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/devis/devisEntreprise.html',
                        controller: 'DevisCompanyController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('devis');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('devis.detail', {
                parent: 'entity',
                url: '/devis/{id}',
                data: {
                    authorities: ['ROLE_USER','ROLE_COMPANY'],
                    pageTitle: 'taamineApp.devis.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/devis/devis-detail.html',
                        controller: 'DevisDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('devis');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Devis', function($stateParams, Devis) {
                        return Devis.get({id : $stateParams.id});
                    }]
                }
            })
            .state('devis.new', {
                parent: 'devis',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/devis/devis-dialog.html',
                        controller: 'DevisDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    dateDevis: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('devis', null, { reload: true });
                    }, function() {
                        $state.go('devis');
                    })
                }]
            })
            .state('devis.edit', {
                parent: 'devis',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/devis/devis-dialog.html',
                        controller: 'DevisDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Devis', function(Devis) {
                                return Devis.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('devis', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('devis.delete', {
                parent: 'devis',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/devis/devis-delete-dialog.html',
                        controller: 'DevisDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Devis', function(Devis) {
                                return Devis.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('devis', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
