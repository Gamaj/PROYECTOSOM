
public class guardarSOM extends procesoSOM{

	public guardarSOM(kernelSOM kernel) {
		super("guardarPSOM", kernel);
		// TODO Auto-generated constructor stub
	}
	public void run() {
	  servicioSOM s4=new servicioSOM("Servicio 1 de Guardar");
		for (int j=0;j<10;j++){ 
			microServicioSOM ms1=new microServicioSOM("MicroServicio "+String.valueOf(j)+" de "+s4.getNombre());
			s4.agregarMicroServicio(ms1);
		}
	  s4.setProceso(this);
	  colaServicios.add(s4);
	  s4.start();
	  enEjecucion=s4;
	  while (enEjecucion.isAlive()){
		  
	  }
	}
	public void recibirmensaje(mensaje msg) {}

}
