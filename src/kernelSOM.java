import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class kernelSOM{
	private ArrayList<procesoSOM> procesos;
	private Queue<mensaje> recibidos=new LinkedList();
	private Queue<procesoSOM> planificador=new LinkedList();

	public kernelSOM() {
		procesos = new ArrayList<procesoSOM>();
	}
	public kernelSOM(ArrayList<procesoSOM> n) {
		procesos = n;
	}

	public synchronized void enviar(procesoSOM n) {
		procesos.add(n);
		notify();

	}

	public synchronized procesoSOM esperar() {
		while (procesos.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}
		procesoSOM x = procesos.get(0);
		procesos.remove(0);
		return x;
	}

	public void recibirmensaje(mensaje msg){
		if (msg.getTxt().compareTo("EXPROPIAR")==0){
			recibidos.add(msg);
			planificador.add(msg.getFrom());
			System.out.println(planificador.peek().getState());
			if (planificador.peek().getState().compareTo(Thread.State.RUNNABLE)==0){
				   procesoSOM ejecutador= new procesoSOM();
				   ejecutador.setNombre("EJECUTADOR");
				   ejecutador.setEnEjecucion(planificador.peek().getColaServicios().peek());
				   ejecutador.setProces(planificador.peek());
				   mensaje despiertelo=new mensaje();
				   despiertelo.setTxt("EJECUCION");
				   despiertelo.setSysmsg(true);
				   despiertelo.setFrom(msg.getFrom());
				   ejecutador.recibirmensaje(despiertelo);
				   ejecutador.start();
				   planificador.poll();
				}else{
				planificador.poll().start();
				}
			msg.getService().esperar();
			msg.getFrom().esperar();
		}	
	if (msg.getTxt().compareTo("ESPERANDOPROCESO")==0){
		recibidos.add(msg);
		planificador.add(msg.getFrom());
		procesoSOM ejecutador= new procesoSOM();
		System.out.println(msg.getFrom().getState());
		ejecutador.setProces(msg.getFrom());
		 ejecutador.setNombre("EJECUTADOR");
		 /* mensaje despiertelo=new mensaje();
		  despiertelo.setSysmsg(true);
		   despiertelo.setTxt("DESPERTAR");
		 despiertelo.setFrom(msg.getFrom()); */
		   ejecutador.start();
		   //ejecutador.recibirmensaje(despiertelo);
		if (planificador.peek().getState().compareTo(Thread.State.TERMINATED)==0){
			planificador.peek().getColaServicios().peek().enviar();
			planificador.poll().enviar();
		}else{
		planificador.poll().start();}
	}
	if (msg.getTxt().compareTo("ACABE")==0){
		recibidos.add(msg);
		if (!planificador.isEmpty()){
			if (planificador.peek().getState().compareTo(Thread.State.NEW)==0){
				planificador.poll().start();
				}else{
			  procesoSOM ejecutador= new procesoSOM();
		//	  System.out.println(msg.getFrom().getState());
			  ejecutador.setProces(msg.getFrom());
			  ejecutador.setNombre("EJECUTADOR");
			   mensaje despiertelo=new mensaje();
			  despiertelo.setSysmsg(true);
			   despiertelo.setFrom(planificador.peek());
			   despiertelo.setTxt("DESPERTAR");
			   ejecutador.start();
			   ejecutador.recibirmensaje(despiertelo);
			   planificador.poll();
			   }
		}
	}
	if (msg.getTxt().compareTo("PRUEBA")==0){
		
		planificador.add(msg.getFrom());
		if (planificador.peek().getState().compareTo(Thread.State.NEW)==0){
			planificador.poll().start();
			}else{
		  procesoSOM ejecutador= new procesoSOM();
	//	  System.out.println(msg.getFrom().getState());
		  ejecutador.setProces(msg.getFrom());
		  ejecutador.setNombre("EJECUTADOR");
		   mensaje despiertelo=new mensaje();
		  despiertelo.setSysmsg(true);
		   despiertelo.setFrom(planificador.peek());
		   despiertelo.setTxt("DESPERTAR");
		   ejecutador.start();
		   ejecutador.recibirmensaje(despiertelo);
		   planificador.poll();
		 //  msg.getFrom().esperar();
		  }
		//msg.getFrom().esperar();
		   
	}
}
	public ArrayList<procesoSOM> getProcesos() {
		return procesos;
	}
	public void setProcesos(ArrayList<procesoSOM> procesos) {
		this.procesos = procesos;
	}
	public void iniciar(){
		procesos.get(0).start();
		procesos.get(2).start();
		mensaje msg= new mensaje("BLOQUEAR",this,procesos.get(0),true);
		procesos.get(0).recibirmensaje(msg);
		mensaje msg1= new mensaje("DESPERTAR",this,procesos.get(0),true);
	}
	public void crearProcesos(){
		recogerPSOM primerProceso=new recogerPSOM(this);
		calcularSOM segundoProceso=new calcularSOM(this);
		guardarSOM tercerProceso=new guardarSOM(this);
		informarSOM cuartoProceso=new informarSOM(this);
		procesos.add(primerProceso);
		procesos.add(segundoProceso);
		procesos.add(tercerProceso);
		procesos.add(cuartoProceso);
		for (int i=0;i<procesos.size();i++){
			planificador.add(procesos.get(i));
		}
	}
	public void arrancarProcesos(){
			planificador.poll().start();
	}
	public void pruebahilos(){
		servicioSOM s2=new servicioSOM("Servicio 1 de Recoger");
		for (int j=0;j<10;j++){ 
			microServicioSOM ms1=new microServicioSOM("MicroServicio "+String.valueOf(j)+" de "+s2.getNombre());
			s2.agregarMicroServicio(ms1);
		}
		recogerPSOM primerProceso=new recogerPSOM();
		primerProceso.setEnEjecucion(s2);
		primerProceso.setKernel(this);
		s2.setProceso(primerProceso);
		primerProceso.start();
		System.out.println(primerProceso.getState());
		primerProceso.esperar();
		System.out.println(primerProceso.getState());
		
	}
}