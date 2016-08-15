/**
 * Created by yang_shoulai on 2016/8/14.
 */
(function () {
    angular.module('app').controller('ArticleAddController', ['$scope', 'MessageService',
        function ($scope, MessageService) {
            $scope.html = '';
            $scope.onFileDrop = function (file, insertAction) {
                if (file.type.substring(0, 5) !== 'image') {
                    MessageService.toast.warning('文件' + file.name + '为非图片文件');
                    return;
                }
                if (file.size > 1024 * 1024 * 2) {
                    MessageService.toast.warning('文件' + file.name + '超过2M');
                    return;
                }
                var reader = new FileReader();
                reader.onload = function () {
                    if (reader.result !== '') insertAction('insertImage', reader.result, true);
                };
                reader.readAsDataURL(file);
                return true;
            };

        }]);
})();
