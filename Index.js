
function addit(a, b, form){
	var A=parseFloat (a);
	var B=parseFloat (b);
	form.answer.value=A+B;
}
function subit(a, b, form){
	var A=parseFloat (a);
	var B=parseFloat (b);
	form.answer.value=A-B;
}
function mulit(a, b, form){
	var A=parseFloat (a);
	var B=parseFloat (b);
	form.answer.value=A*B;
}
function divit(a, b, form){
	var A=parseFloat (a);
	var B=parseFloat (b);
	form.answer.value=A/B;
}
function powit(a, b, form){
	var A=parseFloat (a);
	var B=parseFloat (b);
	form.answer.value=Math.pow(A, B);
}
function cosit(a, b, form){
	var A=parseFloat (a);
	form.answer.value=Math.cos(A);
}
function sinit(a, b, form){
	var A=parseFloat (a);
	form.answer.value=Math.sin(A);

}
function randit(a, b, form){
	var A=parseInt (a);
	form.answer.value=Math.floor((Math.random() * A));
}
function tanit(a, b, form){
	var A=parseFloat (a);
	form.answer.value=Math.tan(A);

}

function pcalc(momentumm, velocityy, masss, form){
    if(momentumm==""){
            var M = parseFloat(masss);
            var V = parseFloat(velocityy);
            form.P.value = (M*V);
    }
    else if(velocityy==""){
            var M = parseFloat(masss);
            var P = parseFloat(momentumm);
            form.V.value = (P/M);
    }
    else if(masss==""){
        var P = parseFloat(momentumm);
            var V = parseFloat(velocityy);
            form.M.value = (P/V);
    }
    else{
        alert("Atleast one box must be empty");
    }
}

function clear(box, form){
    if(box=='P'){
        form.P.value = null;
    }
    else if(box=="M"){
        form.M.value = "";
    }
    else{
        form.V.value = "";
    }
}

function ajax(){
    var ajax;
    if(window.XMLHttpRequest){
    //This is for Firefox, Chrome, IE 7+, and Safari.
        ajax = new XMLHttpRequest();
    }
    else {
    //This is for IE 6 and IE 5.
    ajax = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return ajax;

}
function loadDoc() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
     alert("yeet");
    }
  };
  xhttp.open("GET", "secondPage.html", true);
  xhttp.send();
}