
public class mensaje {
	private boolean sysmsg;
	private String txt;
	private procesoSOM from;
	private procesoSOM to;
	private servicioSOM service;
	private kernelSOM kernel;

	public mensaje() {

	}

	public mensaje(String txt, procesoSOM from, procesoSOM to, boolean sysmsg) {
		this.txt = txt;
		this.from = from;
		this.to = to;
		this.sysmsg = sysmsg;
	}

	public mensaje(servicioSOM n, String txt, procesoSOM from, procesoSOM to, boolean sysmsg) {
		this.service = n;
		this.txt = txt;
		this.from = from;
		this.to = to;
		this.sysmsg = sysmsg;
	}

	public mensaje(String txt, kernelSOM from, procesoSOM to, boolean sysmsg) {
		this.txt = txt;
		this.kernel = from;
		this.from = null;
		this.to = to;
		this.sysmsg = sysmsg;
	}

	public mensaje(String txt, procesoSOM from, kernelSOM to, boolean sysmsg) {
		this.txt = txt;
		this.from = from;
		this.to = null;
		this.kernel = to;
		this.sysmsg = sysmsg;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public boolean isSysmsg() {
		return sysmsg;
	}

	public void setSysmsg(boolean sysmsg) {
		this.sysmsg = sysmsg;
	}

	public procesoSOM getFrom() {
		return from;
	}

	public void setFrom(procesoSOM from) {
		this.from = from;
	}

	public procesoSOM getTo() {
		return to;
	}

	public void setTo(procesoSOM to) {
		this.to = to;
	}

	public servicioSOM getService() {
		return service;
	}

	public void setService(servicioSOM service) {
		this.service = service;
	}

	public kernelSOM getKernel() {
		return kernel;
	}

	public void setKernel(kernelSOM kernel) {
		this.kernel = kernel;
	}
}
