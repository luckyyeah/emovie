var sid = 0;
var clid = location.pathname.split('/')[1];
if (clid == '359') {
    var myDate = new Date();var xiaos=myDate.getSeconds();
    if (xiaos >= 1 && xiaos <= 30) { document.writeln('<span style="display:none"><script src="//s11.cnzz.com/z_stat.php?id=1259035608&web_id=1259035608" language="JavaScript"></script></span>'); } 
}
switch (clid) {
    case '112':sid = 1258643455;break;
    case '114':sid = 1258641562;break;
    case '119':sid = 1000496999;break;
    case '127':sid = 1259182161;break;
    case '361':sid = 1259010158;break;
    case '366':sid = 1258992320;break;
    case '518':sid = 1259184862;break;
    case '565':sid = 1259293510;break;
    case '575':sid = 1259348680;break;
    case '626':sid = 1259408149;break;
    case '636':sid = 1259469432;break;
    case '672':sid = 1259538435;break;
    default:sid = 0;
}
if (sid !== 0) {
    var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cspan style='display:none' id='cnzz_stat_icon_" + sid + "'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D" + sid + "' type='text/javascript'%3E%3C/script%3E"));
}
