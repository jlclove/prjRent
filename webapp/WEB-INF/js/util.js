define(function(){
	var parseParam=function(param, key){
	    var paramStr="";
	    if(param.constructor == String||param.constructor == Number||param.constructor == Boolean){
	        paramStr+="&"+key+"="+encodeURIComponent(param);
	    }else if(param.constructor == Array){
            paramStr+="&"+key+"="+encodeURIComponent(param.join());
        }else{
            for(var i in param){
                if(param.hasOwnProperty(i)){
                    if(param[i]){
                        paramStr+='&'+parseParam(param[i], i);
                    }
                }
            }
	    }
	    return paramStr.substr(1);
	};
	
	return {
		parseParam: parseParam
	};
});