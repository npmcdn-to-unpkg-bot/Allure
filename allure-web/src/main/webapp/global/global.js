/**
 * Created by Administrator on 8/8/2016.
 */
(function () {
    angular.module('global', [
        'ui.bootstrap',
        'ui.router',
        'ngCookies',
        'angular-loading-bar',
        'ngAnimate',
        'toastr',
        'ui.select',
        'ngSanitize',
        'scrollToTop',
        'ngDialog',
        'textAngular',
        'ngFileUpload'
    ]);

    //add image upload support for textAngular
    angular.module('global').config(['$provide', function ($provide) {
        $provide.decorator('taTools', ['$delegate', 'taSelection', '$window', 'taTranslations', 'ngDialog', 'Upload', 'ConfigService', '$document',
            function (taTools, taSelection, $window, taTranslations, ngDialog, Upload, ConfigService, $document) {
                taTools.insertImage.action = function ($deferred) {
                    var textAngular = this;
                    var savedSelection = rangy.saveSelection();
                    var blockJavascript = function (link) {
                        if (link.toLowerCase().indexOf('javascript') !== -1) {
                            return true;
                        }
                        return false;
                    };
                    var dialog = ngDialog.open({
                        template: '../global/tpls/tpl-text-angular-upload-img.html',
                        plain: false,
                        closeByDocument: false,
                        closeByEscape: false,
                        controller: ['$scope', function ($scope) {
                            $scope.image = {};
                            $scope.image.link = '';
                            $scope.error = '';
                            $scope.uploading = false;
                            $scope.uploadProgress = 0;
                            $scope.uploadSuccess = false;
                            $scope.uploadFile = function ($files) {
                                $scope.error = '';
                                $scope.uploadProgress = 0;
                                if ($files && $files.length && $files.length > 0) {
                                    if ($files[0].size > 1024 * 1024 * 5) {
                                        $scope.error = '文件大小超过5MB';
                                    } else {
                                        $scope.uploading = true;
                                        Upload.upload({
                                            url: ConfigService.articlesImageUploadServer,
                                            data: {file: $files[0]}
                                        }).then(function (resp) {
                                            $scope.image.link = ConfigService.articlesImageUploadServer + resp.data[0];
                                            $scope.uploading = false;
                                            $scope.uploadSuccess = true;
                                        }, function (resp) {
                                            $scope.error = '图片上传出错';
                                            $scope.uploading = false;
                                        }, function (evt) {
                                            $scope.uploadProgress = parseInt(100.0 * evt.loaded / evt.total);
                                        });
                                    }
                                }
                            };
                            $scope.reset = function () {
                                $scope.error = '';
                                $scope.uploading = false;
                                $scope.uploadProgress = 0;
                                $scope.uploadSuccess = false;
                                $scope.image.link = '';
                            };

                        }],
                        width: '40%',
                        height: '60%',
                        className: 'ngdialog-theme-default'
                    });
                    dialog.closePromise.then(function (data) {
                        // imageLink = $window.prompt(taTranslations.insertImage.dialogPrompt, 'http://');
                        var imageLink = data.value;
                        if (imageLink && imageLink !== '' && imageLink !== 'http://' && 'http://' === imageLink.substring(0, 7)) {
                            /* istanbul ignore next: don't know how to test this... since it needs a dialogPrompt */
                            // block javascript here
                            if (!blockJavascript(imageLink)) {
                                if (taSelection.getSelectionElement().tagName.toLowerCase() === 'a') {
                                    // due to differences in implementation between FireFox and Chrome, we must move the
                                    // insertion point past the <a> element, otherwise FireFox inserts inside the <a>
                                    // With this change, both FireFox and Chrome behave the same way!
                                    taSelection.setSelectionAfterElement(taSelection.getSelectionElement());
                                }
                                // In the past we used the simple statement:
                                //return this.$editor().wrapSelection('insertImage', imageLink, true);
                                //
                                // However on Firefox only, when the content is empty this is a problem
                                // See Issue #1201
                                // Investigation reveals that Firefox only inserts a <p> only!!!!
                                // So now we use insertHTML here and all is fine.
                                // NOTE: this is what 'insertImage' is supposed to do anyway!
                                var embed = '<img src="' + imageLink + '">';
                                rangy.restoreSelection(savedSelection);
                                return textAngular.$editor().wrapSelection('insertHTML', embed, true);
                            }
                        }
                    });

                };
                return taTools;
            }]);
    }]);
})();
