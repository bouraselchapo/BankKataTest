// create the app module
var bankKataApp = angular.module('bankKataApp', []).config(function($httpProvider) {

	$httpProvider.defaults.headers.post['Content-Type'] = 'application/json; charset=utf-8';

});


