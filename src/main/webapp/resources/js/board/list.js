var myApp = angular.module('myApp', []);
myApp.controller('listController', function($scope, $http)
{

	$http.get('api/select'+window.location.search).then(function(response){
		$scope.data = response.data;
		$scope.Math = window.Math;
		
		
	});
});