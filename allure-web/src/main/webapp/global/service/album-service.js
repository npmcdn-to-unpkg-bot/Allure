/**
 * Created by Administrator on 8/8/2016.
 */
/**
 * Created by Administrator on 8/8/2016.
 */
(function () {
    angular.module('global').service('AlbumService', ['HttpService', 'ConfigService',
        function (HttpService, ConfigService) {

            /**
             * 获取相册列表
             * @param tagId 相册tag
             * @param page 当前页码 从0开始
             * @param pageSize 每页数量
             * @returns {{promise, success, error}|*}
             */
            this.list = function (tagId, page, pageSize) {
                return HttpService.http({
                    url: ConfigService.allureServer + '/albums',
                    method: 'GET',
                    params: {tagId: tagId, page: page, pageSize: pageSize}
                });
            };

            /**
             * 删除相册
             * @param id
             * @returns {{promise, success, error}|*}
             */
            this.delete = function (id) {
                return HttpService.http({
                    url: ConfigService.allureServer + '/albums/' + id,
                    method: 'DELETE'
                });
            };

            /**
             *喜欢相册
             * @param id
             * @returns {{promise, success, error}|*}
             */
            this.like = function (id) {
                return HttpService.http({
                    url: ConfigService.allureServer + '/albums/' + id + '/liker',
                    method: 'POST'
                });
            };

            /**
             * 判断当前用户是否已经喜欢该相册
             * @param id
             * @returns {{promise, success, error}|*}
             */
            this.liked = function (id) {
                return HttpService.http({
                    url: ConfigService.allureServer + '/albums/' + id + '/liked',
                    method: 'GET'
                });
            };

            /**
             * 取消喜欢相册
             * @param id
             * @returns {{promise, success, error}|*}
             */
            this.cancelLike = function (id) {
                return HttpService.http({
                    url: ConfigService.allureServer + '/albums/' + id + '/liker',
                    method: 'DELETE'
                });
            };


            /**
             *收藏相册
             * @param id
             * @returns {{promise, success, error}|*}
             */
            this.collect = function (id) {
                return HttpService.http({
                    url: ConfigService.allureServer + '/albums/' + id + '/collection',
                    method: 'POST'
                });
            };

            /**
             * 判断当前用户是否已经收藏该相册
             * @param id
             * @returns {{promise, success, error}|*}
             */
            this.collected = function (id) {
                return HttpService.http({
                    url: ConfigService.allureServer + '/albums/' + id + '/collected',
                    method: 'GET'
                });
            };

            /**
             * 取消收藏相册
             * @param id
             * @returns {{promise, success, error}|*}
             */
            this.cancelCollect = function (id) {
                return HttpService.http({
                    url: ConfigService.allureServer + '/albums/' + id + '/collection',
                    method: 'DELETE'
                });
            };

        }]);
})();
