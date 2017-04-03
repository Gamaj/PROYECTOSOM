import java.util.Random;

public class microServicioSOM extends Thread {
	private String nombre;
	private int segs;
	private Random r;

	public microServicioSOM(String nombre) {
		this.nombre = nombre;
		this.r = new Random();
		this.segs = r.nextInt((900 - 300) + 1) + 300;
	}

	public void run() {
		try {
			Thread.sleep(segs);
		} catch (InterruptedException e) {
		}
	}

	public int getSegs() {
		return segs;
	}

	public void setSegs(int segs) {
		this.segs = segs;
	}

	public Random getR() {
		return r;
	}

	public void setR(Random r) {
		this.r = r;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
