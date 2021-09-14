angular.module("appmanutencao").controller("loginCtrl", function ($scope, authAPI, $location) {
    $scope.app = "appmanutencao";

    $scope.enviarLogin = function (funcionario) {
      
        authAPI.getlogin(funcionario).then(function (retorno) {
            const token = retorno.headers("authorization");

            if (token) {
                localStorage.setItem("session_token", token);
                $location.path("/inicio")
            }
        }).catch(function(error) {
            alert ("Usuario ou senha errados! Por favor tente novamente");
            location.reload(); 
        });

    } 
    $scope.logout = function ()  {
        localStorage.clear();
        $location.path("/login");
    }


});
