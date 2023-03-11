package com.farenet.nodo.maestro.api.reporte.bean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SunatReporteBean {
	
	public String nroComprobante;
	public String nroSerie;
	public String ruc;
	public String reInspeccion;
	public String resultado;
	public String nroCertificado;
	public String nroInforme;
	public String insEstado;
	public Double importeTotal;
	public String importeCadena;
	public Date fechComprobante;
	public String tipoInspeccion;
	public String moneda;
	public String nroPlaca;
	public String conceptoInspeccion;
	public String tipoDocIdentidad;
	public String nroIdentidad;
	public String nomPersona;
	public String nomRazonSocial;
	public String fecha;
	
	
	public SunatReporteBean(String nroComprobante, String nroSerie, String ruc, String reInspeccion, String resultado,
			String nroCertificado, String nroInforme, String insEstado, Double importeTotal, String importeCadena,
			Date fechComprobante, String tipoInspeccion, String moneda, String nroPlaca, String conceptoInspeccion,
			String tipoDocIdentidad, String nroIdentidad, String nomPersona, String nomRazonSocial, String fecha) {
		super();
		this.nroComprobante = nroComprobante;
		this.nroSerie = nroSerie;
		this.ruc = ruc;
		this.reInspeccion = reInspeccion;
		this.resultado = resultado;
		this.nroCertificado = nroCertificado;
		this.nroInforme = nroInforme;
		this.insEstado = insEstado;
		this.importeTotal = importeTotal;
		this.importeCadena = importeCadena;
		this.fechComprobante = fechComprobante;
		this.tipoInspeccion = tipoInspeccion;
		this.moneda = moneda;
		this.nroPlaca = nroPlaca;
		this.conceptoInspeccion = conceptoInspeccion;
		this.tipoDocIdentidad = tipoDocIdentidad;
		this.nroIdentidad = nroIdentidad;
		this.nomPersona = nomPersona;
		this.nomRazonSocial = nomRazonSocial;
		this.fecha = fecha;
	}
	
	public String getNroComprobante() {
		return nroComprobante;
	}

	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public String getNroSerie() {
		return nroSerie;
	}

	public void setNroSerie(String nroSerie) {
		this.nroSerie = nroSerie;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getReInspeccion() {
		return reInspeccion;
	}

	public void setReInspeccion(String reInspeccion) {
		this.reInspeccion = reInspeccion;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getNroCertificado() {
		return nroCertificado;
	}

	public void setNroCertificado(String nroCertificado) {
		this.nroCertificado = nroCertificado;
	}

	public String getNroInforme() {
		return nroInforme;
	}

	public void setNroInforme(String nroInforme) {
		this.nroInforme = nroInforme;
	}

	public String getInsEstado() {
		return insEstado;
	}

	public void setInsEstado(String insEstado) {
		this.insEstado = insEstado;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getImporteCadena() {
		return importeCadena;
	}

	public void setImporteCadena(String importeCadena) {
		this.importeCadena = importeCadena;
	}

	public Date getFechComprobante() {
		return fechComprobante;
	}

	public void setFechComprobante(Date fechComprobante) {
		this.fechComprobante = fechComprobante;
	}

	public String getTipoInspeccion() {
		return tipoInspeccion;
	}

	public void setTipoInspeccion(String tipoInspeccion) {
		this.tipoInspeccion = tipoInspeccion;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getNroPlaca() {
		return nroPlaca;
	}

	public void setNroPlaca(String nroPlaca) {
		this.nroPlaca = nroPlaca;
	}

	public String getConceptoInspeccion() {
		return conceptoInspeccion;
	}

	public void setConceptoInspeccion(String conceptoInspeccion) {
		this.conceptoInspeccion = conceptoInspeccion;
	}

	public String getTipoDocIdentidad() {
		return tipoDocIdentidad;
	}

	public void setTipoDocIdentidad(String tipoDocIdentidad) {
		this.tipoDocIdentidad = tipoDocIdentidad;
	}

	public String getNroIdentidad() {
		return nroIdentidad;
	}

	public void setNroIdentidad(String nroIdentidad) {
		this.nroIdentidad = nroIdentidad;
	}

	public String getNomPersona() {
		return nomPersona;
	}

	public void setNomPersona(String nomPersona) {
		this.nomPersona = nomPersona;
	}

	public String getNomRazonSocial() {
		return nomRazonSocial;
	}

	public void setNomRazonSocial(String nomRazonSocial) {
		this.nomRazonSocial = nomRazonSocial;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public SunatReporteBean validarCampos(SunatReporteBean bean){
		
		final String FORMATO_FECHA_MEDIUM = "yyyyMMdd";
		final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.00");
		SimpleDateFormat dtf= new SimpleDateFormat(FORMATO_FECHA_MEDIUM);
		bean.setFecha(dtf.format(bean.getFechComprobante()));
		Boolean reinspe = bean.getReInspeccion()!=null && !bean.getReInspeccion().trim().equals("");
		String estadoReinspe ="";
		if (reinspe) {
			estadoReinspe = "TRUE";
		}else{
			estadoReinspe = "FALSE";
		}
		String ruc ="";
		if (bean.getRuc() != null) {
			ruc = bean.getRuc();
			ruc = ruc.substring(0, ruc.length()-1);
		}
		bean.setRuc(ruc);
		
		String nroCompro = "";
		if(bean.getNroComprobante()!=null){
			nroCompro = bean.getNroComprobante();
		}
		// Validacion Numero de Serie
		// 0 -> NroSerie     1 -> NroCorrelativo
		int val = 0;
		int vali = 0;
		if(!nroCompro.equals("")){
			bean.setNroComprobante(nroCompro.substring(nroCompro.indexOf("-")+1,nroCompro.length()));
			bean.setNroSerie(nroCompro.substring(0,nroCompro.indexOf("-")));
		}
		bean.setNroComprobante(limitador(bean.getNroComprobante(), 15));
		bean.setNroSerie(limitador(bean.getNroSerie(), 4));
		String nroRevisionTecnica = "";
		String estd = "";
		if (bean.getResultado() != null){
			estd = bean.getResultado();
		}
		String nro;
		if(bean.getNroCertificado()!=null){
			nro = bean.getNroCertificado();
		}else{
			nro = bean.getNroInforme();
		}
		if (estd.equals("A")) {

			if (nro != null && !nro.isEmpty()) {

				nroRevisionTecnica = nro;
				if(nroRevisionTecnica.length() <8){
					nroRevisionTecnica = limitador(nroRevisionTecnica, 10);
				}
				nroRevisionTecnica = limitadorInverso(nroRevisionTecnica, 8);
				if(estadoReinspe.equals("TRUE")){
					if(bean.getImporteTotal() == 0){
						String ImporServic = "";
						bean.setImporteCadena(limitador(ImporServic, 14));
						bean.setNroComprobante(limitador("", 15));
					}
				}
				if(bean.getInsEstado().equals("ANU")){
					String ImporServic = "0.00";
					bean.setImporteCadena(limitador(ImporServic, 14));
				}
				if(bean.getInsEstado().equals("CON")){
					if(bean.getImporteTotal() == 0){
						String ImporServic="0.00";
						bean.setImporteCadena(limitador(ImporServic, 14));
					}else {
						bean.setImporteCadena(limitador(DECIMAL_FORMAT.format(bean.getImporteTotal()),14));
					}
				}
			}
		}
		if (estd.equals("D")) {
			if(nro != null){
				if(bean.getNroInforme()!= null){
					nroRevisionTecnica = bean.getNroInforme();
				}else{
					nroRevisionTecnica = nro;
				}
				nroRevisionTecnica = limitador(nroRevisionTecnica, 8);
				if(estadoReinspe.equals("TRUE")){
					if(bean.getImporteTotal() == 0 ){
						String ImporServic = "";
						bean.setImporteCadena(limitador(ImporServic, 14));
						bean.setNroComprobante(limitador("", 15));
					}
				}
				if(bean.getInsEstado().equals("ANU")){
					String ImporServic = "0.00";
					bean.setImporteCadena(limitador(ImporServic, 14));
				}
				if(bean.getInsEstado().equals("CON")){
					if(bean.getImporteTotal() == 0 ){
						String ImporServic="0.00";
						bean.setImporteCadena(limitador(ImporServic, 14));
					}else {
						bean.setImporteCadena(limitador(DECIMAL_FORMAT.format(bean.getImporteTotal()),14));
					}
					if(bean.getNroComprobante().trim().equals("")){
						String ImporServic = "";
						bean.setImporteCadena(limitador(ImporServic, 14));
					}
				}
				
			}else{
				nroRevisionTecnica = limitador(nroRevisionTecnica, 8);
			}
		}
		bean.setNroInforme(nroRevisionTecnica);
		String classRevision="";
		if(bean.getTipoInspeccion()!=null){
			classRevision = bean.getTipoInspeccion();
		}
		switch (classRevision) {
		case "1":
			bean.setTipoInspeccion("01");
			break;
		case "2":
			bean.setTipoInspeccion("01");
			break;
		case "3":
			bean.setTipoInspeccion("04");
			break;
		case "4":
			bean.setTipoInspeccion("02");
			break;
		case "5":
			bean.setTipoInspeccion("02");
			break;
		case "7":
			bean.setTipoInspeccion("03");
			break;
		default:
			bean.setTipoInspeccion("  ");
		}
		
		if (bean.getMoneda().equals("sol")) {
			bean.setMoneda("0");
		}else if (bean.getMoneda().equals("dolar")) {
			bean.setMoneda("1");
		}
		String nroplacaStr = "";
		if(bean.getNroPlaca() != null){
			nroplacaStr= bean.getNroPlaca();
		}
		//Validador de placa (quitar '-')
		String placa="";
		placa = nroplacaStr.replace("-", "");
		bean.setNroPlaca(limitador(placa, 6));
		
		if(bean.getConceptoInspeccion() != null){
			String tipoVehic = bean.getConceptoInspeccion();
			switch (tipoVehic) {
			case "2":
				bean.setConceptoInspeccion("04");
				break;
			case "3":
				bean.setConceptoInspeccion("03");
				break;
			case "4":
				bean.setConceptoInspeccion("06");
				break;
			case "7":
				bean.setConceptoInspeccion("09");
				break;
			case "8":
				bean.setConceptoInspeccion("08");
				break;
			case "9":
				bean.setConceptoInspeccion("09");
				break;
			case "11":
				bean.setConceptoInspeccion("11");
				break;
			case "14":
				bean.setConceptoInspeccion("10");
				break;
			case "15":
				bean.setConceptoInspeccion("09");
				break;
			case "16":
				bean.setConceptoInspeccion("08");
				break;
			case "17":
				bean.setConceptoInspeccion("09");
				break;
			case "18":
				bean.setConceptoInspeccion("08");
				break;
			case "20":
				bean.setConceptoInspeccion("09");
				break;
			case "21":
				bean.setConceptoInspeccion("06");
				break;
			case "23":
				bean.setConceptoInspeccion("08");
				break;
			case "28":
				bean.setConceptoInspeccion("08");
				break;
			case "29":
				bean.setConceptoInspeccion("07");
				break;
			case "30":
				bean.setConceptoInspeccion("08");
				break;
			case "31":
				bean.setConceptoInspeccion("11");
				break;
			case "32":
				bean.setConceptoInspeccion("07");
				break;
			case "33":
				bean.setConceptoInspeccion("08");
				break;
			case "34":
				bean.setConceptoInspeccion("11");
				break;
			case "35":
				bean.setConceptoInspeccion("10");
				break;
			case "36":
				bean.setConceptoInspeccion("10");
				break;
			case "37":
				bean.setConceptoInspeccion("11");
				break;
			case "38":
				bean.setConceptoInspeccion("10");
				break;
			case "39":
				bean.setConceptoInspeccion("07");
				break;
			case "40":
				bean.setConceptoInspeccion("04");
				break;
			case "44":
				bean.setConceptoInspeccion("01");
				break;
			case "45":
				bean.setConceptoInspeccion("02");
				break;
			default:
				bean.setConceptoInspeccion("  ");
				break;
			}
		}else{ 
			bean.setConceptoInspeccion("  "); 
		}
		String tipdoc = "";
		if (bean.getTipoDocIdentidad() != null){
			tipdoc = bean.getTipoDocIdentidad();
			switch (tipdoc) {
			case "dni":
				bean.setTipoDocIdentidad("1 ");
				break;
			case "pasaporte":
				bean.setTipoDocIdentidad("7 ");
				break;
			case "ruc":
				bean.setTipoDocIdentidad("6 ");
				break;
			default:
				bean.setTipoDocIdentidad("  ");
				break;
			} 
		} else {
			bean.setTipoDocIdentidad("  ");
		}
		String numIdent = "";
        if (bean.getNroIdentidad() != null) {
        	numIdent = bean.getNroIdentidad();
        }
        bean.setNroIdentidad(limitador(numIdent, 20));
        
        bean.setNomPersona(limitador(bean.getNomPersona(), 40));
        bean.setNomRazonSocial(limitador(bean.getNomRazonSocial(), 40));
        
		return bean;
	}
	

		private String limitador(String texto, int tamano) {
		// TODO Auto-generated method stub
		String textolimitado="";
		if (texto==null)
			texto="";
		int tamTxt = texto.length();
		for (int i = 0; i < tamano; i++) {
			if (i < tamTxt){
				char ltr = texto.charAt(i);
				textolimitado=textolimitado+ltr;
			}else {
				textolimitado=textolimitado+" ";
			}
		}
		return textolimitado;
		
	}
	
	private String limitadorInverso(String texto, int tamano) {
		// TODO Auto-generated method stub
		String textolimitado="";
		if(texto.equals(null) || texto.equals("")){ 
		      texto = "                "; 
		} 
		textolimitado = texto.substring(texto.length() -tamano, texto.length());
		return textolimitado;
		
	}
	
}
