angular.module("appmanutencao").controller("clienteCtrl", function ($scope, clienteAPI) {
    $scope.app = "appmanutencao";
    $scope.cli = {};

    var _formatarOS = function (cli) {
        return cli.ordemDeServico
            .map(function (ordemDeServico) {
                return ordemDeServico.id + " " + ordemDeServico.status;
            })
            .join(", ");
    };

    var carregarCliente = function () {
        clienteAPI.getCliente().then(function (retorno) {
            $scope.clientes = retorno.data;
        });
    };

    $scope.clienteUnico = function (cliid) {
        clienteAPI.getclienteUnico(cliid).then(function (retorno) {
            $scope.cli = retorno.data;
        });
    };

    $scope.adicionarCliente = function (cli) {
        clienteAPI.saveCliente(cli).then(function (data) {
            delete $scope.cli;
            $scope.novoclienteform.$setPristine();
            carregarCliente();
        });
    };

    $scope.excluirCliente = function (cliid) {
        console.log(cliid)
        clienteAPI.deleteCliente(cliid).then(function (retorno) {
            carregarCliente();
        })
            .catch(function (error) {
                alert("OS associada impossivel de excluir")
            })
    }

    $scope.editarCliente = function (cli) {
        console.log(cli)
        clienteAPI.editarCliente(cli).then(function (retorno) {
            carregarCliente();
        })
    }

    carregarCliente();
    $scope.formatarOS = _formatarOS;


});