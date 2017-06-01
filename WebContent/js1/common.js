function $(objStr){return document.getElementById(objStr);}  
window.onload = function(){  
    
    var userNameValue = getCookieValue("guestId");  
    $("guestId").value = userNameValue;  
    var passwordValue = getCookieValue("password");  
    $("password").value = passwordValue;      

    $("submit").onclick = function()  
    {  
        var userNameValue = $("guestId").value;  
        var passwordValue = $("password").value;  
           
        if(isMatched){  
            if( $("saveCookie").checked){    
                setCookie("guestId",$("guestId").value,24,"/");  
                setCookie("password",$("password").value,24,"/");  
            }      
          self.location.replace("index.html");  
        }
    }
}
     