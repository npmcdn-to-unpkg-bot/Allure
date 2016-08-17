/**
 * Created by yang_shoulai on 2016/8/17.
 */
(function () {

    angular.module('global').service('ArticleService', [
        'HttpService',
        'ConfigService',
        function (HttpService, ConfigService) {

            /**
             * 获取文章分类
             * @returns {*|{promise, success, error}}
             */
            this.categories = function () {
                return HttpService.http({
                    url: ConfigService.allureServer + '/articles/categories',
                    method: 'GET'
                });
            };

            /**
             * 新建文章
             * @param name
             * @param content
             * @param category
             */
            this.createArticle = function (name, content, category) {
                return HttpService.http({
                    url: ConfigService.allureServer + '/articles',
                    method: 'POST',
                    data: {name: name, content: content, categoryId: category}
                });
            };

            /**
             * 获取所有文章
             */
            this.articles = function () {
                return HttpService.http({
                    url: ConfigService.allureServer + '/articles',
                    method: 'GET'
                });
            };

            /**
             * 获取文章
             * @param id
             * @returns {{promise, success, error}|*}
             */
            this.findById = function (id) {
                return HttpService.http({
                    url: ConfigService.allureServer + '/articles/' + id,
                    method: 'GET'
                });
            };
        }
    ]);

})();