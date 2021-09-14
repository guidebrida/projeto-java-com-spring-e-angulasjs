angular.module("appmanutencao").factory("authAPI", function ($http, config) {
    var  _getlogin = function(funcionario)  {
        return $http.post(config.baseUrl + "/login", funcionario,{
            headers: {
                "Content-Type": "application/json"
            }
        });
    };

    return{
        getlogin:_getlogin,
    }
});