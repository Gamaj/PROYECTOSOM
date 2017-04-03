

public class calcularSOM extends procesoSOM{

	public calcularSOM(kernelSOM kernel) {
		super("calcularPSOM", kernel);
		// TODO Auto-generated constructor stub
	}
	public void run() {
		servicioSOM s1=new servicioSOM("Servicio 1 de Calcular");
		for (int j=0;j<10;j++){ 
			microServicioSOM ms1=new microServicioSOM("MicroServicio "+String.valueOf(j)+" de "+s1.getNombre());
			s1.agregarMicroServicio(ms1);
		}
		s1.setProceso(this);
		this.colaServicios.add(s1);
		colaServicios.add(s1);
		s1.start();
		enEjecucion=s1;
		while (enEjecucion.isAlive()){
			
		}
		
	}
	public void recibirmensaje(mensaje msg) {}
}
