package br.senai.sp.api.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.senai.sp.api.model.Preco;
import br.senai.sp.api.model.Veiculo;
import br.senai.sp.api.repository.PrecoRepository;

public class Pagamento {
	
	public Preco precos;
	
	/*public Pagamento(Preco precos) {
		this.precos = precos;
	}*/
	
	public static double getPagamento(Veiculo veiculo, Preco preco){
		
		double precoPrimeiraHora = preco.getValorPrimeiraHora();
		double precoDemaisHoras = preco.getValorDemaisHoras();
		int tolerancia = preco.getTolerancia() ;
		double horasPagar = 0;
		double total = 0;
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			
			Date horaEntrada = df.parse(String.valueOf(veiculo.getHorarioEntrada()));
			Date horaSaida = new Date();
			
			Long milisegundos = horaSaida.getTime() - horaEntrada.getTime();
			double minuto = milisegundos / 1000 / 60;
			double hora = (int) minuto / 60;
			double minutosExcedidos = minuto - hora * 60;
			
			if((minuto >= hora * 60)) {
				if((minuto - hora * 60) > tolerancia) {
					//horasPagar = (hora + 1) * precoPrimeiraHora;
					//((horasUsadas-1)*demaisHoras)+primeiraHora
					horasPagar = hora + 1;
					total =	 ((precoDemaisHoras) * (horasPagar - 1)) + precoPrimeiraHora ;
				}else {
					horasPagar = hora * precoPrimeiraHora;
				}
			}
			
			
			// horasPagar = hora * precoPrimeira; 
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return total;
	}
	
	
}
