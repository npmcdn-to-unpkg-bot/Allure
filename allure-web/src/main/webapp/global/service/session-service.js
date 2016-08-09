/**
 * Created by Administrator on 8/8/2016.
 */
(function () {
    angular.module('global').service('SessionService', ['$rootScope', function ($rootScope) {

        var _User = null;

        this.setUser = function (user) {
            if (user != null) {
                _User = user;
                $rootScope.$broadcast('session-on', user);
            } else {
                this.invalid();
            }

        };
        this.getUser = function () {
            return _User;
        };

        this.invalid = function () {
            _User = null;
            $rootScope.$broadcast('session-off', null);
        };

    }]);
})();
