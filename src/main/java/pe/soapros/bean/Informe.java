package pe.soapros.bean;

public class Informe {

	private String metadata;
	private int cnt;
	private int cntDuplicados;
	private int cntErrores;
	private int cntGuardados;
	private int cntNOPDF;

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getCntDuplicados() {
		return cntDuplicados;
	}

	public void setCntDuplicados(int cntDuplicados) {
		this.cntDuplicados = cntDuplicados;
	}

	public int getCntErrores() {
		return cntErrores;
	}

	public void setCntErrores(int cntErrores) {
		this.cntErrores = cntErrores;
	}

	public int getCntGuardados() {
		return cntGuardados;
	}

	public void setCntGuardados(int cntGuardados) {
		this.cntGuardados = cntGuardados;
	}
	
	

	public int getCntNOPDF() {
		return cntNOPDF;
	}

	public void setCntNOPDF(int cntNOPDF) {
		this.cntNOPDF = cntNOPDF;
	}

	@Override
	public String toString() {
		return metadata + "|" + cnt + "|" + cntDuplicados + "|" + cntErrores + "|" + cntGuardados +"|" + cntNOPDF;
	}

}
