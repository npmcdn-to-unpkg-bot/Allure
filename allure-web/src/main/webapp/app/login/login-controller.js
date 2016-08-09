/**
 * Created by Administrator on 8/9/2016.
 */
(function () {
    angular.module('login', ['global']);
    angular.module('login').controller('LoginController', [
        '$scope',
        'AccountService',
        'SessionService',
        'MessageService',
        'CookieService',
        '$timeout',
        '$window',
        function ($scope, AccountService, SessionService, MessageService, CookieService, $timeout, $window) {
            $scope.submitted = false;
            var email = '';
            var remember = false;
            if (CookieService.get(CookieService.KEY_CONSTANT.user_login_remember_me) === true) {
                remember = true;
                email = CookieService.get(CookieService.KEY_CONSTANT.user_login_username);
            }

            $scope.request = {
                email: email,
                password: '',
                remember: remember
            };

            $scope.submit = function () {
                $scope.submitted = true;
                if ($scope.form.$valid) {
                    AccountService.login($scope.request.email, $scope.request.password)
                        .success(function (account) {
                            SessionService.setUser(account);
                            if ($scope.request.remember) {
                                CookieService.put(CookieService.KEY_CONSTANT.user_login_remember_me, true);
                                CookieService.put(CookieService.KEY_CONSTANT.user_login_username, $scope.request.email);
                            } else {
                                CookieService.remove(CookieService.KEY_CONSTANT.user_login_remember_me);
                                CookieService.remove(CookieService.KEY_CONSTANT.user_login_username);
                            }
                            MessageService.toast.success('登录成功');
                            $timeout(function () {
                                $window.location.href = "index.html";
                            }, 500);
                        }).error(function () {
                        MessageService.toast.error('登录失败');
                    });
                }
            };

        }]);
})();