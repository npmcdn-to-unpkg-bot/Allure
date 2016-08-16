/**
 * Created by Administrator on 8/8/2016.
 */
(function () {
    angular.module('global').service('ConfigService', function () {
        /**
         * allure service server
         * @type {string}
         */
        this.allureServer = '/allure-service';

        /**
         * article image upload server
         * @type {string}
         */
        this.articlesImageUploadServer = "http://localhost:8080/allure-image/articles/image/upload/";
    });
})();