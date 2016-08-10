/**
 * Created by Administrator on 8/8/2016.
 */
/**
 * Created by yang_shoulai on 2016/6/25.
 */
(function () {
    angular.module('global').service('HttpService', ['$q', '$injector', '$http', 'SessionService', 'MessageService',
        function ($q, $injector, $http, SessionService, MessageService) {
            var toastr = MessageService.toast;
            this.http = function (params) {
                var defer = $q.defer();
                $http(params).success(function (data) {
                    if (data.status == 0) {
                        defer.resolve(data.result);
                    } else {
                        var message = '';
                        if (data.globalErrors && data.globalErrors.length > 0) {
                            angular.forEach(data.globalErrors, function (error) {
                                message = message + error.message + "\n";
                            });
                        }
                        if (data.filedErrors && data.filedErrors.length > 0) {
                            angular.forEach(data.filedErrors, function (error) {
                                message = message + error.message + "\n";
                            });
                        }
                        // toastr.error(message);
                        defer.reject({globalErrors: data.globalErrors, filedErrors: data.filedErrors});
                    }
                }).error(function (data, status, config, header) {
                    if (status == 401) {
                        SessionService.invalid();
                        toastr.error('用户未登陆或者会话过期');
                    } else if (status == 403) {
                        toastr.error('您没有登录或者没有权限执行此操作');
                    } else if (status == 500) {
                        toastr.error('服务器异常');
                    } else if (status == 404) {
                        toastr.error('资源不存在');
                    } else if (status == 400) {
                        toastr.error('错误的请求');
                    } else if (status == 406) {
                        toastr.error('不可接受的请求');
                    } else if (status == 415) {
                        toastr.error('不支持的媒体类型');
                    } else if (status == 405) {
                        toastr.error('不被支持的HTTP方法');
                    } else {
                        toastr.error('HTTP请求出错，状态码' + status);
                    }
                    //defer.reject(data, header, config, status);
                });

                return {
                    promise: defer.promise,
                    success: function (success) {
                        this.promise.then(success);
                        return this;
                    },
                    error: function (error) {
                        this.promise.then(null, error);
                        return this;
                    }
                };
            };
        }]);

})();