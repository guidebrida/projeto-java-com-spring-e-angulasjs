angular.module("appmanutencao").factory("funcionarioAPI", function ($http, config) {
 
    var _adicionarFuncionarios = function (funcionario) {
        return $http.post(config.baseUrl + "/funcionarios", funcionario);
    };

    return{
        adicionarFuncionarios: _adicionarFuncionarios,
    }

});
