/**
 * Created by Administrator on 8/9/2016.
 */
(function () {
    angular.module('app', ['global']);
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
        }).state('articles', {
            url: '/articles',
            templateUrl: 'articles/articles.html',
            abstract: true
        }).state('articles.list', {
            url: '/list',
            templateUrl: 'articles/list.html',
            title: '文章'
        }).state('articles.add', {
            url: '/add',
            templateUrl: 'articles/add.html',
            controller: 'ArticleAddController',
            title: '文章'
        }).state('articles.detail', {
            url: '/{id:[0-9]{1,}}',
            templateUrl: 'articles/detail.html',
            controller: 'ArticleDetailController',
            resolve: {
                article: function (ArticleService, $stateParams) {
                    return ArticleService.findById($stateParams.id).promise;
                }
            },
            title: '文章'
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
