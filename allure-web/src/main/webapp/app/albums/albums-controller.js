/**
 * Created by Administrator on 8/9/2016.
 */

(function () {
    angular.module('app').controller('AlbumsListController', [
        '$scope', 'tags', 'AlbumService', 'MessageService', '$state', '$window',
        function ($scope, tags, AlbumService, MessageService, $state, $window) {
            $scope.tags = tags;
            $scope.albums = [];
            $scope.pagination = {
                page: 1,
                pageSize: 24,
                tag: null,
                maxSize: 0
            };

            var getAlbums = function () {
                AlbumService.list($scope.pagination.tag.id, $scope.pagination.page - 1, $scope.pagination.pageSize)
                    .success(function (page) {
                        $scope.pagination.maxSize = page.totalElements;
                        $scope.albums = page.content;
                        $window.scrollTo(0, 0);

                    }).error(function (globalErrors, fieldErrors) {
                    MessageService.toast.error('获取相册列表失败');
                });
            };

            $scope.clickTag = function (tag) {
                $scope.pagination.maxSize = 0;
                $scope.pagination.page = 1;
                $scope.pagination.tag = tag;
                $state.current.title = tag.name;
                getAlbums();
            };

            $scope.onPageChanged = function () {
                getAlbums();
            };

            $scope.clickTag(tags[0]);
        }]);


    angular.module('app').controller('AlbumsDetailController', ['$scope', 'album', 'AlbumService', '$state', 'MessageService',
        function ($scope, album, AlbumService, $state, MessageService) {
            $scope.album = album;

            /*angular.forEach(album.photos, function (photo) {
             photo.src = 'http://tnfs.tngou.net/image/ext/160803/1c80ec19164a9ccfded52af97703cdb7.jpg';
             });*/

            $state.current.title = album.name;
            $scope.liked = false;
            $scope.collected = false;
            AlbumService.liked(album.id)
                .success(function (liked) {
                    $scope.liked = liked;
                });
            AlbumService.collected(album.id)
                .success(function (collected) {
                    $scope.collected = collected;
                });

            $scope.toggleLike = function () {
                if ($scope.liked) {
                    AlbumService.cancelLike(album.id)
                        .success(function () {
                            $scope.liked = false;
                            $scope.album.likeRecordsCount--;
                        }).error(function () {
                        MessageService.toast.error('取消喜欢失败');
                    });
                } else {
                    AlbumService.like(album.id)
                        .success(function () {
                            $scope.liked = true;
                            $scope.album.likeRecordsCount++;
                        }).error(function () {
                        MessageService.toast.error('喜欢该相册失败');
                    });
                }
            };

            $scope.toggleCollect = function () {
                if ($scope.collected) {
                    AlbumService.cancelCollect(album.id)
                        .success(function () {
                            $scope.collected = false;
                            $scope.album.collectionRecordsCount--;
                        }).error(function () {
                        MessageService.toast.error('取消收藏失败');
                    });
                } else {
                    AlbumService.collect(album.id)
                        .success(function () {
                            $scope.collected = true;
                            $scope.album.collectionRecordsCount++;
                        }).error(function () {
                        MessageService.toast.error('收藏失败');
                    });
                }
            };
        }]);
})();
