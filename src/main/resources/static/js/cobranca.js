$('#confirmacaoEclusaoModal').on('show.bs.modal', function(event){
	var button = $(event.relatedTarget);
	var idTitulo = button.data('id');
	var descricaoTitulo = button.data('descricao');
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith('/')){
		action += '/';
	}
	
	form.attr('action', action+idTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir <b>' + descricaoTitulo + '</b>?');
	
});

$(function () {
	  $('[rel="tooltip"]').tooltip();
	  $('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});
	  
	  $('.js-atualizar-status').on('click', function(event){
		  event.preventDefault();
		  
		  var btnReceber = $(event.currentTarget);
		  var urlReceber = btnReceber.attr('href');
		  
		 //		  console.log('urlReceber:', urlReceber);
		 
		  var response = $.ajax({
			 
			  url: urlReceber,
			  type: 'PUT'
			  
		  });
		  
		  response.done(function(e) {
			  var idTituloRecebido = btnReceber.data('id');
			  $('[data-role=' + idTituloRecebido + ']').html('<span class="badge badge-success">' + e + '</span>');
			  btnReceber.hide();
			  
		  	});
		  
		  response.fail(function(e) {
				console.log(e);
				alert("Erro na tentativa de receber essa cobrança!")
			});
		  
	  });
	})