
function setCookie(id,value,hours,path){  
    var id = escape(id);  
    var value = escape(value);  
    var expires = new Date();  
    expires.setTime(expires.getTime() + hours*3600000);  
    path = path == "" ? "" : ";path=" + path;  
    _expires = (typeof hours) == "string" ? "" : ";expires=" + expires.toUTCString();  
    document.cookie = id + "=" + value + _expires + path;  
}  
 
function getCookieValue(id){  
    var id = escape(id);  
    
    var allcookies = document.cookie;         
   
    id += "=";  
    var pos = allcookies.indexOf(id);      
      
    if (pos != -1){                                             
        var start = pos + id.length;                  
        var end = allcookies.indexOf(";",start);        
        if (end == -1) end = allcookies.length;         
        var value = allcookies.substring(start,end);  
        return unescape(value);                                  
        }     
    else return "";                                              
}  
 
function deleteCookie(id,path){  
    var id = escape(id);  
    var expires = new Date(0);  
    path = path == "" ? "" : ";path=" + path;  
    document.cookie = id + "="+ ";expires=" + expires.toUTCString() + path;  
}  