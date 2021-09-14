angular.module("appmanutencao").factory("equipamentoAPI", function ($http, config) {

 
    var _equipamentoObj = {};

    var _getEquipamentoObj = function() {
        return _equipamentoObj;
    }

    var _setEquipamentoObj = function(newEquipamentoObj){
        _equipamentoObj = newEquipamentoObj;
    }


    var _getEquipamentoUnico = function (equiid) {
        return $http.get(config.baseUrl + "/equipamentos/" + equiid);
    }

    var _editarEquipamentos = function (id, equi) {
        return $http.put(config.baseUrl + "/equipamentos/" + id, equi);
    };
    return{
        getEquipamentoUnico:_getEquipamentoUnico,
        editarEquipamentos:_editarEquipamentos,
        getEquipamentoObj: _getEquipamentoObj,
        setEquipamentoObj: _setEquipamentoObj
    }


});