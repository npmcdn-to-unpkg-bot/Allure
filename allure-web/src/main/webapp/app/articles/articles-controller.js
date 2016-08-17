/**
 * Created by yang_shoulai on 2016/8/14.
 */
(function () {
    angular.module('app').controller('ArticleAddController', ['$scope', 'MessageService', 'ArticleService', '$state',
        function ($scope, MessageService, ArticleService, $state) {
            $scope.name = '';
            $scope.html = '';
            $scope.category = null;
            $scope.categories = [];
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

            ArticleService.categories().success(function (categories) {
                $scope.categories = categories;
                $scope.category = categories[0];
            }).error(function () {
                MessageService.toast.error('获取文章分类失败');
            });

            $scope.submit = function () {
                if (!$scope.category || !$scope.category.id) {
                    MessageService.toast.error('请选择分类');
                    return;
                }

                if (!$scope.name || $scope.name === '') {
                    MessageService.toast.error('请输入标题');
                    return;
                }
                if (!$scope.html || $scope.html === '') {
                    MessageService.toast.error('请输入内容');
                    return;
                }
                ArticleService.createArticle($scope.name, $scope.html, $scope.category.id)
                    .success(function (id) {
                        $state.go('articles.detail', {id: id});
                    }).error(function () {
                    MessageService.toast.error('创建失败');
                })
            };

        }]);

    angular.module('app').controller('ArticleDetailController', ['$scope', 'MessageService', 'ArticleService', 'article',
        function ($scope, MessageService, ArticleService, article) {
            $scope.article = article;
        }]);
})();
