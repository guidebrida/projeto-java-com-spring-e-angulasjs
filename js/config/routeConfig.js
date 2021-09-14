angular.module("appmanutencao").config(function ($routeProvider, $locationProvider) {
    
    $locationProvider.hashPrefix('');
    $routeProvider.when("/ordemdeservico", {
        templateUrl: './view/ordemdeservico.html',
        controller: "ordemdeservicoCtrl",
    });

$locationProvider.hashPrefix('');
$routeProvider.when("/clientes", {
    templateUrl: './view/clientes.html',
    controller: "clienteCtrl",
});


$locationProvider.hashPrefix('');
$routeProvider.when("/inicio", {
    templateUrl: './view/inicio.html',
    controller: "clienteCtrl",
});

$locationProvider.hashPrefix('');
$routeProvider.when("/addclientes", {
    templateUrl: './view/addclientes.html',
    controller: "clienteCtrl",
});

$locationProvider.hashPrefix('');
$routeProvider.when("/login", {
    templateUrl: './view/login.html',
    controller: "loginCtrl",
});


$locationProvider.hashPrefix('');
$routeProvider.when("/funcionarios", {
    templateUrl: './view/cadastrarFuncionarios.html',
    controller: "funcionarioCtrl",
})

$locationProvider.hashPrefix('');
$routeProvider.when("/equipamentos",{
    templateUrl: './view/editarEquipamento.html',
    controller: "equipamentoCtrl",
})

$routeProvider.otherwise({
    redirectTo: "/login"
});

});
