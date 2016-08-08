/**
 * Created by Administrator on 8/8/2016.
 */
(function () {
    angular.module('global').service('ConfigService', function () {
        this.allureServer = 'http:localhost:8080/allure-service';
    });
})();