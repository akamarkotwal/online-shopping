$(function(){
	switch(menu){
		
		case 'About Us':
			$('#about').addClass('active');
			break;
		case 'Contact us':
			$('#contact').addClass('active');
			break;
		case 'All Products':
			$('#listProduct').addClass('active');
			break;
		default: 
			$('#listProducts').addClass('active');
		    $('#a_'+menu).addClass('active');
			break;
	
	}
	
	
	
	
});