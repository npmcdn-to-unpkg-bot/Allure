/**
 * Created by Administrator on 8/8/2016.
 */
(function () {
    angular.module('global').service('AccountService', ['HttpService', 'SessionService', 'ConfigService',
        function (HttpService, SessionService, ConfigService) {

            /**
             * 用户登录
             * @param email 邮箱
             * @param password 密码
             * @returns {{promise, success, error}|*}
             */
            this.login = function (email, password) {
                return HttpService.http({
                    url: ConfigService.allureServer + '/login',
                    method: 'POST',
                    params: 'email=' + email + '&password=' + password,
                    headers: {
                        'Content-Type': 'x-www-form-urlencoded'
                    }
                });
            };

            /**
             * 用户注销登录
             * @returns {{promise, success, error}|*}
             */
            this.logout = function () {
                return HttpService.http({
                    url: ConfigService.allureServer + '/logout',
                    method: 'GET'
                });
            };

            /**
             * 用户注册
             * @param email 邮箱
             * @param password 密码
             * @param nickName 昵称
             * @returns {{promise, success, error}|*}
             */
            this.register = function (email, password, nickName) {
                return HttpService.http({
                    url: ConfigService.allureServer + '/accounts/register',
                    method: 'POST',
                    data: {
                        email: email,
                        password: password,
                        nickName: nickName
                    }
                });
            };

            /**
             * 用户更新
             * @param id 用户ID
             * @param email 邮箱
             * @param password 密码
             * @param nickName 昵称
             * @returns {{promise, success, error}|*}
             */
            this.update = function (id, email, password, nickName) {
                return HttpService.http({
                    url: ConfigService.allureServer + '/accounts/' + id,
                    method: 'PUT',
                    data: {
                        email: email,
                        password: password,
                        nickName: nickName
                    }
                });
            };


        }]);
})();
