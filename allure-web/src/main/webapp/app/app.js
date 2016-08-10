/**
 * Created by Administrator on 8/9/2016.
 */
(function () {
    angular.module('app', ['global', 'ui.bootstrap']);
    angular.module("app").run(['$rootScope', '$state', '$stateParams', '$window',
        function ($rootScope, $state, $stateParams, $window) {
            $rootScope.$state = $state;
            $rootScope.$stateParams = $stateParams;
            $rootScope.$on('$viewContentLoaded', function () {
                $window.scrollTo(0, 0);
            });
        }
    ]);
    angular.module('app').config(function (cfpLoadingBarProvider) {
        cfpLoadingBarProvider.includeSpinner = true;
    });

    angular.module("app").config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/albums/list");

        $stateProvider.state('albums', {
            url: '/albums',
            templateUrl: 'albums/albums.html',
            abstract: true
        }).state('albums.list', {
            url: '/list',
            templateUrl: 'albums/list.html',
            controller: 'AlbumsListController',
            resolve: {
                tags: function (TagService) {
                    return TagService.getTags().promise;
                }
            },
            title: '首页'
        }).state('albums.detail', {
            url: '/{id:[0-9]{1,}}',
            templateUrl: 'albums/detail.html',
            controller: 'AlbumsDetailController',
            resolve: {
                album: function (AlbumService, $stateParams) {
                    return AlbumService.get($stateParams.id).promise;
                }
            },
            title: '首页'
        });

    });

    angular.module("app").controller('TitleBarController', [
        '$scope', 'SessionService', 'AccountService', 'MessageService',
        '$window',
        function ($scope, SessionService, AccountService, MessageService, $window) {
            $scope.user = null;
            AccountService.reload().success(function (account) {
                SessionService.setUser(account);
            }).error(function (resp) {
                MessageService.toast.error('获取用户登录信息出错');
            });

            $scope.$on('session-on', function (evt, account) {
                $scope.user = account;
            });
            $scope.$on('session-off', function (evt, msg) {
                $scope.user = null;
            });

            $scope.logout = function () {
                AccountService.logout()
                    .success(function (resp) {
                        $window.location.href = 'login.html';
                    }).error(function (resp) {
                    MessageService.toast.error('用户注销登录失败');
                });
            };

        }]);

})();