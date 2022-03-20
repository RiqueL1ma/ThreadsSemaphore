package Controller;

import java.util.concurrent.Semaphore;

public class ThreadMultiprocessamento extends Thread {
	private int idProcesso;
	private Semaphore semaforo;
	private Semaphore semaforoSaida;
	
	public ThreadMultiprocessamento(int idProcesso, Semaphore semaforo, Semaphore semaforoSaida) {
		this.idProcesso = idProcesso;
		this.semaforo = semaforo; 
		this.semaforoSaida = semaforoSaida;
	}
	
	@Override
	public void run() {
		calculoOcorrendo();
		try {
			semaforo.acquire();
			transacaoBD();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		trasacaoFinalizada();
	}

	private void calculoOcorrendo() {
		int res = idProcesso % 3;
		
		if (res == 1) {
			
			for(int i =  0; i < 2; i++) {
				int tempo = (int)((Math.random() * 1001) + 200);
				try {
					sleep(tempo);
					System.out.println("Calculo " + (i+1) + " do processo #" + idProcesso + " efetuado");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
		} else {
			if (res == 2) {
				for(int i =  0; i < 3; i++) {
					int tempo = (int)((Math.random() * 1501) + 500);
					try {
						sleep(tempo);
						System.out.println("Calculo " + (i+1) + " do processo #" + idProcesso + " efetuado");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				for(int i =  0; i < 3; i++) {
					int tempo = (int)((Math.random() * 2001) + 1000);
					try {
						sleep(tempo);
						System.out.println("Calculo " + (i+1) + " do processo #" + idProcesso + " efetuado");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
			
	}


	private void transacaoBD() {
		int res = idProcesso % 3;
		
		if (res == 1) {
			for(int i =  0; i < 2; i++) {
				int tempo = 1000;
				try {
					sleep(tempo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("A Transação " + (i+1) + " de BD do processo: #" + idProcesso + " foi finalizado");
			}
				
		} else {
			if(res == 2){
				for(int i =  0; i < 3; i++) {
					int tempo = 1500;
					try {
						sleep(tempo);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println("A Transação " + (i+1) + " de BD do processo: #" + idProcesso + " foi finalizado");
				}
			} else {
				for(int i =  0; i < 3; i++) {
					int tempo = 1500;
					try {
						sleep(tempo);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println("A Transação " + (i+1) + " de BD do processo: #" + idProcesso + " foi finalizado");
				}
			}
		}
		

		try {
			semaforoSaida.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} finally {
			semaforoSaida.release();
		}

		;
	}

	private void trasacaoFinalizada() {
		System.out.println("----- Bloco Completo #" + idProcesso + " -----------");
	}
}
