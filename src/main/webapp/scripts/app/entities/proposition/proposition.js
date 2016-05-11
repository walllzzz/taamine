'use strict';

angular.module('taamineApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('proposition', {
                parent: 'entity',
                url: '/propositions',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'taamineApp.proposition.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/proposition/propositions.html',
                        controller: 'PropositionController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('proposition');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('propositionDevis', {
                parent: 'entity',
                url: '/propositionsDevis/{id}',
                data: {
                    authorities: ['ROLE_USER','ROLE_COMPANY'],
                    pageTitle: 'taamineApp.proposition.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/proposition/propositions.html',
                        controller: 'PropositionDevisController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('proposition');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
             .state('mespropositions', {
                parent: 'entity',
                url: '/mespropositions',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'taamineApp.proposition.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/proposition/propositions.html',
                        controller: 'MesPropositionController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('proposition');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
             .state('mespropositionsEntreprise', {
                parent: 'entity',
                url: '/mespropositionsEntreprise',
                data: {
                    authorities: ['ROLE_COMPANY'],
                    pageTitle: 'taamineApp.proposition.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/proposition/propositions.html',
                        controller: 'MesPropositionEntrepriseController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('proposition');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('proposition.detail', {
                parent: 'entity',
                url: '/proposition/{id}',
                data: {
                    authorities: ['ROLE_USER','ROLE_COMPANY'],
                    pageTitle: 'taamineApp.proposition.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/proposition/proposition-detail.html',
                        controller: 'PropositionDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('proposition');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Proposition', function($stateParams, Proposition) {
                        return Proposition.get({id : $stateParams.id});
                    }]
                }
            })
            .state('proposition.new', {
                parent: 'proposition',
                url: '/new',
                data: {
                    authorities: ['ROLE_COMPANY','ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/proposition/proposition-dialog.html',
                        controller: 'PropositionDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    prix: null,
                                    dateProposition: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('proposition', null, { reload: true });
                    }, function() {
                        $state.go('proposition');
                    })
                }]
            })
            .state('propositionDevis.new', {
                parent: 'proposition',
                url: '/{id}/new',
                data: {
                    authorities: ['ROLE_COMPANY'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/proposition/proposition-dialog.html',
                        controller: 'PropositionDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    prix: null,
                                    dateProposition: null,
                                    id: null,
                                    devisId: $stateParams.id                                   
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('proposition', null, { reload: true });
                    }, function() {
                        $state.go('proposition');
                    })
                }]
            })
            .state('proposition.edit', {
                parent: 'proposition',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_COMPANY'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/proposition/proposition-dialog.html',
                        controller: 'PropositionDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Proposition', function(Proposition) {
                                return Proposition.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('proposition', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('proposition.delete', {
                parent: 'proposition',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_COMPANY'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/proposition/proposition-delete-dialog.html',
                        controller: 'PropositionDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Proposition', function(Proposition) {
                                return Proposition.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('proposition', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
