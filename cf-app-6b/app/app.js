(function() {
  var app = angular.module('cfapp6b', [ 'ngRoute' ]);
  app.controller('cfapp6bController', function($scope, $http) {
    $scope.userInfo;

    $http.get("https://cf-app-6a.local.pcfdev.io/userinfo")
        .success(function(response) {
          $scope.userInfo = response;
        });
    });

}) ();