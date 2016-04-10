/**
 * @author Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (4/9/16)
 */
var module = angular.module("Site", ["ngRoute", "ui.bootstrap", "ui.bootstrap.tpls", "ui.router"]);
module.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('readme', {
            url: "/readme",
            templateUrl: "views/readme.html"
        })
        .state('roadmap', {
            url: "/roadmap",
            templateUrl: "views/roadmap.html"
        })
        .state('changelog', {
            url: "/changelog",
            templateUrl: "views/changelog.html"
        });
    $urlRouterProvider.otherwise("/readme");
}]);