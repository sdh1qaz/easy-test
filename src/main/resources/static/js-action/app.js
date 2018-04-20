/**
 * 页面路由转向
 */

var actionApp = angular.module('actionApp',['ngRoute']);




actionApp.config(['$routeProvider' , function($routeProvider) {
	
	$routeProvider.when('/apicount', { 
		controller: 'View1Controller', 
		templateUrl: 'views/view1.html', 
	}).when('/batchtest', {
		controller: 'View2Controller',
		templateUrl: 'views/view2.html',
	}).when('/casetest', {
		controller: 'View3Controller',
		templateUrl: 'views/view3.html',
	});

}]);
