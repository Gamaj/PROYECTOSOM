
public class recogerPSOM extends procesoSOM {
	private procesoSOM a;

	public recogerPSOM() {

	}

	public recogerPSOM(kernelSOM kernel) {
		super("recogerPSOM", kernel);
	}

	public void run() {
		servicioSOM s2 = new servicioSOM("Servicio 1 de Recoger");
		for (int j = 0; j < 10; j++) {
			microServicioSOM ms1 = new microServicioSOM("MicroServicio " + String.valueOf(j) + " de " + s2.getNombre());
			s2.agregarMicroServicio(ms1);
		}
		s2.setProceso(this);
		colaServicios.add(s2);
		enEjecucion = s2;
		s2.start();
		while (enEjecucion.isAlive()) {

		}
	}

	public void recibirmensaje(mensaje msg) {
	}

}
