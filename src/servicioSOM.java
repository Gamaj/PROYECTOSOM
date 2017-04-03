import java.io.EOFException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class servicioSOM extends Thread {
	private String nombre;
	private Queue<microServicioSOM> listaMicroServicios=new LinkedList();
	private Queue<mensaje> mensajesSYS=new LinkedList();
	private Queue<mensaje> otrosMensajes=new LinkedList();
	private procesoSOM proceso;
	private int quantum=3;
	public servicioSOM(String nombre){
		this.nombre=nombre;
	}
	public servicioSOM(String nombre, microServicioSOM microserv) {
		this.nombre = nombre;
		listaMicroServicios.add(microserv);
	}
	public servicioSOM(String nombre, procesoSOM proceso) {
		this.nombre = nombre;
		this.proceso=proceso;
	}
	public void run() {
		int ticks=0;
		while (!listaMicroServicios.isEmpty()){
			ticks++;
			microServicioSOM x=listaMicroServicios.poll();
			x.start();
			System.out.println("Ejecutando MicroServicio: "+x.getNombre());
			while (x.isAlive()){
				
			}
			if (ticks%3==0&&!listaMicroServicios.isEmpty()){
				mensaje expropieme = new mensaje();
				expropieme.setTxt("PRUEBA");
				expropieme.setService(this);
				expropieme.setSysmsg(false);
				//expropieme.setKernel(this.proceso.getKernel());
				expropieme.setFrom(proceso);
				proceso.getKernel().recibirmensaje(expropieme);
		     	proceso.esperar();
			}
			if (!mensajesSYS.isEmpty()||!otrosMensajes.isEmpty()){
				if (!mensajesSYS.isEmpty()){
					mensaje sistemM= mensajesSYS.poll();
					String contenido=sistemM.getTxt();
					if (contenido.compareTo("ESPERAR")==0){
						System.out.println("ENESPERA"+nombre);
						
					}
					
				}
				
			}
		}

	/*	mensaje expropieme = new mensaje();
		expropieme.setTxt("ACABE");
		expropieme.setService(this);
		expropieme.setSysmsg(false);
		//expropieme.setKernel(this.proceso.getKernel());
		expropieme.setFrom(proceso);
		proceso.getKernel().recibirmensaje(expropieme);
     	//proceso.esperar();
     	 */
		
	}
	public Queue<microServicioSOM> getListaMicroServicios() {
		return listaMicroServicios;
	}
	public void setListaMicroServicios(Queue<microServicioSOM> listaMicroServicios) {
		this.listaMicroServicios = listaMicroServicios;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void recibirMensaje(mensaje n){
		if (n.isSysmsg()){
			mensajesSYS.add(n);
		}else{
		otrosMensajes.add(n);
		}
	}
	public mensaje enviarMensaje(mensaje n){
		mensaje x=new mensaje();
		return x;
	}
	
	public Queue<mensaje> getMensajesSYS() {
		return mensajesSYS;
	}

	public void setMensajes(Queue<mensaje> mensajes) {
		this.mensajesSYS = mensajes;
	}
	public void agregarMicroServicio(microServicioSOM n){
		listaMicroServicios.add(n);
	}
	public void setProceso(procesoSOM x){
		proceso=x;
	}
	public synchronized void esperar() {
		try {
			wait();
		} catch (InterruptedException e) {
		}
	}
	public synchronized void enviar() {
		notify();
	}
	public int getQuantum() {
		return quantum;
	}
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
}
