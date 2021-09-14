angular.module("appmanutencao").config(function($httpProvider) {
    $httpProvider.interceptors.push("authInterceptor");
});