/**
 * Created by Administrator on 8/8/2016.
 */
/**
 * Created by Administrator on 8/8/2016.
 */
(function () {
    angular.module('global').service('TagService', ['HttpService', 'ConfigService',
        function (HttpService, ConfigService) {
            /**
             * 获取所有相册Tag
             * @returns {{promise, success, error}|*}
             */
            this.getTags = function () {
                return HttpService.http({
                    url: ConfigService.allureServer + '/tags',
                    method: 'GET'
                });
            };

        }]);
})();
