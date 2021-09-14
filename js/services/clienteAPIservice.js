angular.module("appmanutencao").factory("clienteAPI", function ($http, config) {
    var _getCliente = function() {
        return $http.get(config.baseUrl + "/clientes");
    }
    var _getClienteUnico = function (cliid) {
        return $http.get(config.baseUrl + "/clientes/" + cliid);
    }

    var _saveCliente = function (cli) {
        return $http.post(config.baseUrl + "/clientes", cli);
    };

    var _deleteCliente = function (cliid) {
        return $http.delete(config.baseUrl + "/clientes/" + cliid);
    };
    
    var _editarCliente = function (cli) {
        return $http.put(config.baseUrl + "/clientes/" + id, cli);
    };

    return {
        getCliente: _getCliente,
        getclienteUnico: _getClienteUnico,
        saveCliente: _saveCliente,
        deleteCliente: _deleteCliente,
        editarCliente: _editarCliente,
    };
});

