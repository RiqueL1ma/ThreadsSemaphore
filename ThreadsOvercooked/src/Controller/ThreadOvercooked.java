package Controller;

import java.util.concurrent.Semaphore;

public class ThreadOvercooked extends Thread {
	private int idProcesso;
	private Semaphore semaforo;
	private Semaphore semaforoSaida;
	
	public ThreadOvercooked(int idProcesso, Semaphore semaforo, Semaphore semaforoSaida) {
		this.idProcesso = idProcesso;
		this.semaforo = semaforo; 
		this.semaforoSaida = semaforoSaida;
	}
	
	@Override
	public void run() {
		realizandoPrato();
		try {
			semaforo.acquire();
			entrega();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		
	}
	private void realizandoPrato() {
		
		int res = idProcesso % 2;
		if (res == 1) {
			System.out.println("#" + idProcesso + " sopa de cebola iniciada");
				int tempo = (int)((Math.random() * 801) + 500);
				double tempoNoFogo = 0;
				double percentCozimento = 0.0;
				while (tempoNoFogo < tempo) {
					try {
						sleep(1000);
						tempoNoFogo += 100;
						percentCozimento = (tempoNoFogo / tempo);
						if (percentCozimento >= 1) {
							System.out.println("Sopa de Cebola #" + idProcesso + ": % de cozimento = 100% \nThread #" + idProcesso
									+ ": Sopa de Cebola cozida!");
						} else {
							System.out.println("Sopa de Cebola #" + idProcesso + ": % de cozimento = "
									+ (int) (percentCozimento * 100) + "%");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}	
		} else {
			System.out.println("#" + idProcesso + " lasanha a bolonhesa iniciada");
			int tempo = (int)((Math.random() * 1201) + 600);
			double tempoNoFogo = 0;
			double percentCozimento = 0.0;
			while (tempoNoFogo < tempo) {
				try {
					sleep(1000);
					tempoNoFogo += 100;
					percentCozimento = (tempoNoFogo / tempo);
					if (percentCozimento >= 1) {
						System.out.println("Sopa de Cebola #" + idProcesso + ": % de cozimento = 100% \nThread #" + idProcesso
								+ ": Sopa de Cebola cozida!");
					} else {
						System.out.println("Sopa de Cebola #" + idProcesso + ": % de cozimento = "
								+ (int) (percentCozimento * 100) + "%");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
			}
			
		}

	
	private void entrega() {
				int tempo = 500;
				try {
					sleep(tempo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("O prato #" + idProcesso + " foi entregue");
		

		try {
			semaforoSaida.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} finally {
			semaforoSaida.release();
		}

		;
	}
}
