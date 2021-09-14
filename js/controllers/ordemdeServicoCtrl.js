angular.module("appmanutencao").controller("ordemdeservicoCtrl", function ($scope, ordemDeServicoAPI, equipamentoAPI, $route) {
    $scope.app = "appmanutencao";
    $scope.os = {};
   
    var _formatarEquipamentos = function (os) {
        return os.equipamentos
            .map(function (equipamento) {
                return equipamento.id + " " + "Nome: " + equipamento.nome + ", descrição: " + equipamento.descricao + " Defeito: " + equipamento.defeito + ", Marca: " + equipamento.marca;
            })
            .join(",");
    };


    var carregarordemDeServico = function () {
        ordemDeServicoAPI.getOrdemDeServico().then(function (retorno) {
            $scope.ordemDeServico = retorno.data;
        });


    };

    $scope.adicionarOS = function (os) {
        
        ordemDeServicoAPI.saveOs(os).then(function (data) {
            delete $scope.os;
            $scope.addOs.$setPristine();
            carregarordemDeServico();
            $route.reload()
        });        
        carregarordemDeServico();

    };
 

    $scope.setEquipamento = function(equipamentoObj) {
        equipamentoAPI.setEquipamentoObj(equipamentoObj);
    }
   
    
    $scope.excluirOs = function (osid) {
   
        console.log($scope.retorno)
        ordemDeServicoAPI.deleteOs(osid).then(function (retorno)  {
            carregarordemDeServico();
        }).catch(function(error){
        alert("Erro Impossivel excluir")
    })
}

    $scope.ordemDeServicoUnica = function (osid){       
        ordemDeServicoAPI.getOrdemDeServicoUnica(osid).then(function(retorno){
            $scope.os = retorno.data;
        });
    };

    
    //status
    $scope.editarOrdemDeServico  = function (os){

        ordemDeServicoAPI.editarOrdemDeServico(os).then(function(retorno){
         
            carregarordemDeServico();
        })
    }

    $scope.adicionaImagem = function (os){
        var file = $scope.myFile;
        ordemDeServicoAPI.adicionarImagem(os.id, file).then(function(retorno){
            
            carregarordemDeServico();
        })
        carregarordemDeServico();
        
    }

    carregarordemDeServico();
    $scope.formatarEquipamentos = _formatarEquipamentos;
});

