'use strict';

angular.module('mutrack')
  .controller('LoginCtrl', function($scope, $http, $rootScope, $location, ngNotify, SERVICE_PATH) {

    var serviceFactory = {};
    var url = SERVICE_PATH.PUBLIC_PATH + '/auth';


    $scope.login = function(email,password) {
      var requestParams = {
        method: 'POST',
        url: url,
        headers: { 'Content-Type': 'application/json' },
        data: {

          'email': email,
          'password': password

        }
      };

      $http(requestParams).then(
        function success(response) {
          var data = response.data;

          if (data.name) {
            $rootScope.authDetails = {

              id:   data.id,
              name: data.name,
              email:data.email,
              mimeType: data.mimeType,
              file: data.file,

              authenticated: true ,
              permissions: data.permissions
              //file: data.file

            };
            $location.path('/');
            ngNotify.set('Welcome ' + data.name + '.', 'success');



          } else {
            $rootScope.authDetails = {
              name: '',

              authenticated: false,
              permissions: []
              //file: {}

            };

            ngNotify.set('The email or password that you have entered do not match our records.', {
              type: 'failure',
              duration: 5000
            });
          }
        },
        function failure(response) {
          $rootScope.authDetails = {
            name: '',
            authenticated: false,
            permissions: []
          //  file: {}
          };
        }
      );
    };


    serviceFactory.logout = function() {
          var requestParams = {
            method: 'POST',
            url: urlLogout,
            headers: {
              'Content-Type': 'application/json'
            }
          };

          $http(requestParams).finally(function success() {
              $rootScope.authDetails = { name: '', authenticated: false, permissions: []  };//, file: {}
              $location.path('/');
            });
        };

        return serviceFactory;






  });
