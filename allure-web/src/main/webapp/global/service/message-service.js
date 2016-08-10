(function () {
    angular.module("global").config(function (toastrConfig) {
        angular.extend(toastrConfig, {
            autoDismiss: true,
            allowHtml: false,
            closeButton: true,
            progressBar: false,
            tapToDismiss: false,
            containerId: 'toast-container',
            maxOpened: 0,
            newestOnTop: true,
            positionClass: 'toast-top-right toastr',
            preventDuplicates: false,
            preventOpenDuplicates: false,
            target: 'body',
            timeOut: 2000
        });
    });
    angular.module('global').service('MessageService', ['toastr', function (toastr) {
        this.toast = {};
        this.toast.info = function (message, title) {
            if (!title) {
                toastr.info(message);
            } else {
                toastr.info(message, title);
            }

        };
        this.toast.success = function (message, title) {
            if (!title) {
                toastr.success(message);
            } else {
                toastr.success(message, title);
            }
        };
        this.toast.error = function (message, title) {
            if (!title) {
                toastr.error(message);
            } else {
                toastr.error(message, title);
            }
        };
        this.toast.warning = function (message, title) {
            if (!title) {
                toastr.warning(message);
            } else {
                toastr.warning(message, title);
            }
        };

    }]);
})();