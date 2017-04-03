import java.util.LinkedList;
import java.util.Queue;

public class procesoSOM extends Thread {
	private String nombre;
	private Queue<mensaje> colaMensajes = new LinkedList();
	private Queue<mensaje> colaMsgSys = new LinkedList();
	protected Queue<servicioSOM> colaServicios = new LinkedList();
	private kernelSOM kernel;
	protected servicioSOM enEjecucion;
	private procesoSOM proces;
	private boolean esperando = false;

	public procesoSOM() {

	}

	public procesoSOM(String nombre, kernelSOM n) {
		this.kernel = n;
		this.nombre = nombre;
	}

	public void run() {
		if (nombre.compareTo("EJECUTADOR") == 0) {

			proces.getEnEjecucion().enviar();
			proces.enviar();
		} else {
			servicioSOM enEjecucion = colaServicios.peek();
			enEjecucion.start();
			while (enEjecucion.isAlive()) {

			}
		}
	}

	public void recibirmensaje(mensaje msg) {
		if (msg.isSysmsg()) {
			if (msg.getTxt().compareTo("INICIAR") == 0) {
				System.out.println("INICIAR");
			}
			if (msg.getTxt().compareTo("BLOQUEAR") == 0) {
				this.esperar();
			}
			if (msg.getTxt().compareTo("DESPERTAR") == 0) {
				System.out.println("DESPERTAR");
				System.out.println(msg.getFrom().getNombre());
				msg.getFrom().enviar();
				msg.getFrom().getColaServicios().peek().enviar();
			}
			if (msg.getTxt().compareTo("EJECUCION") == 0) {
				msg.getFrom().enviar();
			}
		} else {

		}

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Queue<servicioSOM> getColaServicios() {
		return colaServicios;
	}

	public void setColaServicios(Queue<servicioSOM> colaServicios) {
		this.colaServicios = colaServicios;
	}

	public Queue<mensaje> getColaMsgSys() {
		return colaMsgSys;
	}

	public void setColaMsgSys(Queue<mensaje> colaMsgSys) {
		this.colaMsgSys = colaMsgSys;
	}

	public Queue<mensaje> getColaMensajes() {
		return colaMensajes;
	}

	public void setColaMensajes(Queue<mensaje> colaMensajes) {
		this.colaMensajes = colaMensajes;
	}

	public kernelSOM getKernel() {
		return kernel;
	}

	public void setKernel(kernelSOM kernel) {
		this.kernel = kernel;
	}

	public servicioSOM getEnEjecucion() {
		return enEjecucion;
	}

	public void setEnEjecucion(servicioSOM enEjecucion) {
		this.enEjecucion = enEjecucion;
	}

	public synchronized void esperar() {
		try {
			this.esperando = true;
			System.out.println("EXPROPIANDO " + this.nombre);
			wait();
		} catch (InterruptedException e) {
		}
	}

	public synchronized void enviar() {
		this.esperando = false;

		System.out.println("DESPERTANDO " + this.nombre);
		notify();

	}

	public boolean isEsperando() {
		return esperando;
	}

	public void setEsperando(boolean esperando) {
		this.esperando = esperando;
	}

	public procesoSOM getProces() {
		return proces;
	}

	public void setProces(procesoSOM proces) {
		this.proces = proces;
	}

}