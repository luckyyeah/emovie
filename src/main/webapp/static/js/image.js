$(function(){	
	//loadImg(1);		
	$("#left").click(function(){
		loadImg('pre');
		});
	
	$("#right").click(function(){
		loadImg('next');
		});
	
    // 键盘控制图片
    $(document).keydown(function(event){ 
        if(event.keyCode == 37){ 
        loadImg('pre');
      	}else if (event.keyCode == 39){
		 loadImg('next'); 
		} 
    	});
	
	});