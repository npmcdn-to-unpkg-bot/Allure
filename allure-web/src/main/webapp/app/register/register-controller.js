/**
 * Created by Administrator on 8/9/2016.
 */
(function () {
    angular.module('register', ['global']);
    angular.module('register').controller('RegisterController', [
        '$scope',
        'AccountService',
        'SessionService',
        'MessageService',
        'CookieService',
        '$timeout',
        '$window',
        'HttpService',
        function ($scope, AccountService, SessionService, MessageService, CookieService, $timeout, $window, HttpService) {
            $scope.submitted = false;
            $scope.requestParams = {
                email: '',
                password: '',
                repassword: '',
                nickName: '',
                validationCode: ''
            };

            $scope.getVerifyCode = function () {
                AccountService.getVerifyCode()
                    .success(function (base64) {
                        $scope.verifyImage = 'data:image/jpg;base64,' + base64;
                    }).error(function () {
                    MessageService.toast.error('获取验证码失败');
                });
            };

            $scope.submit = function () {
                $scope.submitted = true;
                if ($scope.form.$valid && $scope.requestParams.repassword == $scope.requestParams.password) {
                    AccountService.register($scope.requestParams.email, $scope.requestParams.password, $scope.requestParams.nickName, $scope.requestParams.validationCode
                    ).success(function () {
                        MessageService.toast.success("正在登陆...", "注册成功");
                        $timeout(function () {
                            AccountService.login($scope.requestParams.email, $scope.requestParams.password)
                                .success(function () {
                                    $window.location.href = "index.html";
                                }).error(function (data) {
                                MessageService.toast.error(data.message, "登录失败");
                            });
                        }, 500);
                    }).error(function (apiErrors) {
                        MessageService.toast.error(HttpService.parseErrors(apiErrors));
                    });
                }
            };

            $scope.getVerifyCode();
        }]);
})();