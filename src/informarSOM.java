
public class informarSOM extends procesoSOM{

	public informarSOM(kernelSOM kernel) {
		super("informarPSOM", kernel);
		// TODO Auto-generated constructor stub
	}
	public void run() {
		servicioSOM s3=new servicioSOM("Servicio 1 de Informar");
		for (int j=0;j<10;j++){ 
			microServicioSOM ms1=new microServicioSOM("MicroServicio "+String.valueOf(j)+" de "+s3.getNombre());
			s3.agregarMicroServicio(ms1);
		}
		colaServicios.add(s3);
		s3.setProceso(this);
		s3.start();
		enEjecucion=s3;
		while (s3.isAlive()){
			
		}

	}
	public void recibirmensaje(mensaje msg) {
		
	}
}
