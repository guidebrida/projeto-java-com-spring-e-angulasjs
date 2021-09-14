angular.module("appmanutencao").factory("ordemDeServicoAPI", function ($http, config) {
    var _getOrdemDeServico = function () {
        return $http.get(config.baseUrl + "/ordemdeservicos");
    }
    var _getOrdemDeServicoUnica = function (osid) {
        return $http.get(config.baseUrl + "/ordemdeservicos/" + osid);
    }
  

    var _saveOs = function (os) {
        return $http.post(config.baseUrl + "/ordemdeservicos", os);
    };

    var _deleteOs = function (osid) {
        return $http.delete(config.baseUrl + "/ordemdeservicos/" + osid);
    };

    var _editarOrdemDeServico = function (os) {
        return $http.put(config.baseUrl + "/ordemdeservicos/" +  os.id  + "/status", os);
    };

    
    var _adicionarImagem = function (osid, file) {
        var fd = new FormData();
        fd.append('file', file);
        return $http.post(config.baseUrl + "/ordemdeservicos/" +  osid + "/picture", fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        });

    };

    return {
        getOrdemDeServico: _getOrdemDeServico,
        saveOs: _saveOs,
        deleteOs: _deleteOs,
        getOrdemDeServicoUnica: _getOrdemDeServicoUnica,
        editarOrdemDeServico: _editarOrdemDeServico,
        adicionarImagem: _adicionarImagem,
    };


});


