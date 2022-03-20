package view;

import java.util.concurrent.Semaphore;

import Controller.ThreadOvercooked;

public class Principal {

	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		Semaphore semaforoSaida = new Semaphore(1);
		
		for (int idProcesso = 1 ; idProcesso < 6 ; idProcesso++) {
			Thread  tProcesso = new ThreadOvercooked(idProcesso, semaforo, semaforoSaida);
			tProcesso.start();
		}

	}

}
