/**
 * Created by Administrator on 8/9/2016.
 */

(function () {
    angular.module('app').controller('AlbumsListController', [
        '$scope', 'tags', 'AlbumService', 'MessageService',
        function ($scope, tags, AlbumService, MessageService) {
            $scope.tags = tags;
            $scope.albums = [];
            var pagination = $scope.pagination = {
                page: 0,
                pageSize: 24,
                tagId: tags[0].id,
                maxSize: 0
            };

            var getAlbums = function () {
                AlbumService.list(pagination.tagId, pagination.page, pagination.pageSize)
                    .success(function (page) {
                        pagination.maxSize = page.totalElements;
                        $scope.albums = page.content;

                    }).error(function (globalErrors, fieldErrors) {
                    MessageService.toast.error('获取相册列表失败');
                });
            };

            $scope.clickTag = function (tag) {
                pagination.maxSize = 0;
                pagination.page = 0;
                pagination.tagId = tag.id;
                getAlbums();
            };

            getAlbums();


        }]);
})();
