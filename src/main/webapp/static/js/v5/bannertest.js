document.ns = navigator.appName == "Netscape"
function fly_leftload()
{
if (navigator.appName == "Netscape")
{document.fly_left.pageY=pageYOffset+window.innerHeight-95;
document.fly_left.pageX=+window.innerWidth-110;
document.fly_left2.pageY=pageYOffset+window.innerHeight-263;
document.fly_left2.pageX=+window.innerWidth-290;
fly_leftmove();
}
else
{
fly_left.style.top=document.body.scrollTop+document.body.offsetHeight-95;
fly_left.style.right=document.body.offsetWidth-110;
fly_left2.style.top=document.body.scrollTop+document.body.offsetHeight-263;
fly_left2.style.right=document.body.offsetWidth-290;
fly_leftmove();
}
}
function fly_leftmove()
{
if(document.ns)
{
document.fly_left.top=pageYOffset+window.innerHeight-95
document.fly_left.right=+window.innerWidth-110;
document.fly_left2.top=pageYOffset+window.innerHeight-263
document.fly_left2.right=+window.innerWidth-290;
setTimeout("fly_leftmove();",5)
}
else
{
fly_left.style.top=document.body.scrollTop+document.body.offsetHeight-95;
fly_left.style.right=document.body.offsetWidth-110;
fly_left2.style.top=document.body.scrollTop+document.body.offsetHeight-263;
fly_left2.style.right=document.body.offsetWidth-290;
setTimeout("fly_leftmove();",5)
}
}

function MM_reloadPage(init) { //reloads the window if Nav4 resized
if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true)


function fly_leftover()
{
if(navigator.appName == "Netscape"){
document.fly_left2.visibility="show";
}else 
{
fly_left2.style.visibility="visible";
}
}
function fly_leftout()
{
if(navigator.appName == "Netscape"){
document.fly_left2.visibility="hide";
}else 
{
fly_left2.style.visibility="hidden";
}
}

if (navigator.appName == "Netscape")
{document.write("<layer id=fly_left top=300 width=320 height=240><a href=http://www.jz123.cn onmouseover=fly_leftover() onmouseout=fly_leftout() border=0 target=_blank><img src=logo.gif border=0></a></layer>"
+"<layer id=fly_left2><img src='static/images/p-weixin.jpg' border=0></a></layer>");
fly_leftload()}
else
{
document.write("<div id=fly_left style='position: absolute;width:80;top:300;visibility: visible;z-index: 999'><a href='http://www.jz123.cn ' onmouseover=fly_leftover() onmouseout=fly_leftout() target=_blank><img src='logo.gif' border='0'></a></div>"
+"<div id=fly_left2 style='position: absolute;visibility: hidden;z-index: 100'><a href='http://www.jz123.cn ' onmouseover=fly_leftover() onmouseout=fly_leftout() target=_blank><img src='static/images/p-weixin.jpg' border='0'></a></div>");
fly_leftload()
}