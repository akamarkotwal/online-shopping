$(function(){
	switch(menu){
		
		case 'About Us':
			$('#about').addClass('active');
			break;
		case 'Contact us':
			$('#contact').addClass('active');
			break;
		default: 
			$('#home').addClass('active');
			break;
	
	}
	
	
	
	
});