angular.module("appmanutencao").controller("funcionarioCtrl", function ($scope, funcionarioAPI){
    $scope.app = "appmanutencao";
    

    $scope.adicionarFuncionarios = function (funcionario) {
        console.log(funcionario)
        delete $scope.funcionario;
        funcionarioAPI.adicionarFuncionarios(funcionario).then(function (data) {
            $scope.novoFuncionarioForm.$setPristine();   
        }).catch(function(error) {
            alert ("Você precissa ser admin para poder adicionar um novo funcionario");
        });
    };
    

   
});
