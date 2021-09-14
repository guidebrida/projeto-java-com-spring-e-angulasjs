angular.module("appmanutencao").controller("equipamentoCtrl", function ($scope, equipamentoAPI, $location) {
    $scope.app = "appmanutencao";
    $scope.equi = {};

    var idEquipamento = equipamentoAPI.getEquipamentoObj().id;

    $scope.equipamentoUnico = function (equiid){     
        equipamentoAPI.getEquipamentoUnico(equiid).then(function(retorno){
            $scope.equi = retorno.data
        });
    };

    //equipamentos
    $scope.editEqui  = function (equi){
        equipamentoAPI.editarEquipamentos(idEquipamento, equi).then(function(retorno){
            $location.path("/ordemdeservico")
        })
        .catch(function(error) {
            alert ("Prencha todos os campos");
        });
    }

});
