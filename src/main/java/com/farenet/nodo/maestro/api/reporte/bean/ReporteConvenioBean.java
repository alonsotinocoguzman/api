package com.farenet.nodo.maestro.api.reporte.bean;

import java.util.List;

public class ReporteConvenioBean {
	
	private Long id;
	private String nombre;
	private Boolean estado;
	private String nroruc;
	private String empresa;
	private String ejecutivo;
	private double creditooMaximo;
	public ReporteConvenioBean(Long id, String nombre, Boolean estado, String nroruc, String empresa, String ejecutivo, double creditooMaximo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.nroruc = nroruc;
		this.empresa = empresa;
		this.ejecutivo = ejecutivo;
		this.creditooMaximo = creditooMaximo;
	}
	
	// PARTICULAR LIVIANO
	private String contadoParticularLiv;
	private String creditoParticularLiv;
	// CAMIONETA RURAL
	private String contadoCamionetaRural;
	private String creditoCamionetaRural;
	// CAMIONETA PICK UP
	private String contadoCamionetaPickUp;
	private String creditoCamionetaPickUp;
	// MOTO LINEAL
	private String contadoMotoLineal;
	private String creditoMotoLineal;
	// MOTO TAXI
	private String contadoMotoTaxi;
	private String creditoMotoTaxi;
	// TAXI
	private String contadoTaxi;
	private String creditoTaxi;
	// CAMIONETA MICROBUS (COMBI)
	private String contadoCamionetaMicroComb;
	private String creditoCamionetaMicroComb;
	// TURISTICO (COMBI/COUSTER)
	private String contadoTuristicoCombCouster;
	private String creditoTuristicoCombCouster;
	// TURISTICO (BUS)
	private String contadoTuristicoBus;
	private String creditoTuristicoBus;
	// ESCOLAR (COMBI)
	private String contadoEscolarCombi;
	private String creditoEscolarCombi;
	// ESCOLAR (BUS)
	private String contadoEscolarBus;
	private String creditoEscolarBus;
	// URBANO E INTERURBANO DE PERSONAS (COMBI/COUSTER)
	private String contadoUrbInterCombCoust;
	private String creditoUrbInterCombCoust;
	// URBANO E INTERURBANO DE PERSONAS (BUS)
	private String contadoUrbInterBus;
	private String creditoUrbInterBus;
	// INTERPROVINCIAL DE PASAJEROS BUS
	private String contadoInterPasaBus;
	private String creditoInterPasaBus;	
	// INTERNACIONAL DE PASAJEROS
	private String contadoInterPasaj;
	private String creditoInterPasaj;
	// CAMIONETA RURAL (PUBLICO)
	private String contadoCamionetaRuralPub;
	private String creditoCamionetaRuralPub;
	// AMBULANCIA
	private String contadoAmbulancia;
	private String creditoAmbulancia;
	// CAMION REMOLCADOR (MERCANCIAS)
	private String contadoCamionRemolcadorM;
	private String creditoCamionRemolcadorM;
	// CISTERNA / TANQUE (MERCANCIAS)
	private String contadoCisternaM;
	private String creditoCisternaM;
	// CARRETA (MERCANCIAS)
	private String contadoCarretaM;
	private String creditoCarretaM;
	// CAMIONETA PICK UP (MERCANCIAS)
	private String contadoCamionetaPickUpM;
	private String creditoCamionetaPickUpM;
	// CAMIONETA FURGON (MERCANCIAS)
	private String contadoCamionetaFurgonM;
	private String creditoCamionetaFurgonM;
	// CAMIONETA PANEL (MERCANCIA)
	private String contadoCamionetaPanelM;
	private String creditoCamionetaPanelM;
	// CAMIONETA RURAL (MERCANCIAS)
	private String contadoCamionetaRuralM;
	private String creditoCamionetaRuralM;
	// CAMION REMOLCADOR (R. PELIGROSO)
	private String contadoCamionRemolRp;
	private String creditoCamionRemolRp;
	// CAMION N2 (R. PELIGROSO)
	private String contadoCamionN2Rp;
	private String creditoCamionN2Rp;
	// CAMION PICK UP N1 (R. PELIGROSO)
	private String contadoCamionPickUpRp;
	private String creditoCamionPickUpRp;
	// CARRETA (R. PELIGROSO)
	private String contadoCarretaRp;
	private String creditoCarretaRp;
	// CAMIONETA PANEL
	private String contadoCamionetaPanel;
	private String creditoCamionetaPanel;
	// CAMIONETA RURAL (SUV)
	private String contadoCamionetaRuralSuv;
	private String creditoCamionetaRuralSuv;
	// CISTERNA COMBUSTIBLE (R. PELIGROSO)
	private String contadoCisternaCombusRp;
	private String creditoCisternaCombusRp;
	// MATERIALES Y RESIDUOS PELIGROSOS
	private String contadoMaterialesRp;
	private String creditoMaterialesRp;
	// MERCANCIAS
	private String contadoMercancia;
	private String creditoMercancia;
	// COMPLEMENTARIO
	private String contadoComplementario;
	private String creditoComplementario;
	// CONSTATACION DE CARACTERISTICAS
	private String contadoConsCaract;
	private String creditoConsCaract;
	// TRABAJADORES POR CARRETERA
	private String contadoTrabCarreta;
	private String creditoTrabCarreta;
	// CERTIFICADO HERMETICIDAD
	private String contadoCertHermeticidad;
	private String creditoCertHermeticidad;
	// FARENET EXPRESS
	private String contadoFarenetExpress;
	private String creditoFarenetExpress;
	// CERTIFICADO OPACIDAD
	private String contadoCertOpacidad;
	private String creditoCertOpacidad;
	// CERTIFICADO VELOCIDAD
	private String contadoCertVelocidad;
	private String creditoCertVelocidad;
	// CERTIFICADO DE CONFORMIDAD
	private String contadoCertConformidad;
	private String creditoCertConformidad;
	// CONSTANCIA DE ANALISIS DE GASES
	private String contadoConstAnalisisGases;
	private String creditoConstAnalisisGases;
	
	public ReporteConvenioBean(Long id, String nombre, Boolean estado, String nroruc, String empresa, String ejecutivo,
			double creditooMaximo, String contadoParticularLiv, String creditoParticularLiv,
			String contadoCamionetaRural, String creditoCamionetaRural, String contadoCamionetaPickUp,
			String creditoCamionetaPickUp, String contadoMotoLineal, String creditoMotoLineal, String contadoMotoTaxi,
			String creditoMotoTaxi, String contadoTaxi, String creditoTaxi, String contadoCamionetaMicroComb,
			String creditoCamionetaMicroComb, String contadoTuristicoCombCouster, String creditoTuristicoCombCouster,
			String contadoTuristicoBus, String creditoTuristicoBus, String contadoEscolarCombi,
			String creditoEscolarCombi, String contadoEscolarBus, String creditoEscolarBus,
			String contadoUrbInterCombCoust, String creditoUrbInterCombCoust, String contadoUrbInterBus,
			String creditoUrbInterBus, String contadoInterPasaBus, String creditoInterPasaBus, String contadoInterPasaj,
			String creditoInterPasaj, String contadoCamionetaRuralPub, String creditoCamionetaRuralPub,
			String contadoAmbulancia, String creditoAmbulancia, String contadoCamionRemolcadorM,
			String creditoCamionRemolcadorM, String contadoCisternaM, String creditoCisternaM, String contadoCarretaM,
			String creditoCarretaM, String contadoCamionetaPickUpM, String creditoCamionetaPickUpM,
			String contadoCamionetaFurgonM, String creditoCamionetaFurgonM, String contadoCamionetaPanelM,
			String creditoCamionetaPanelM, String contadoCamionetaRuralM, String creditoCamionetaRuralM,
			String contadoCamionRemolRp, String creditoCamionRemolRp, String contadoCamionN2Rp,
			String creditoCamionN2Rp, String contadoCamionPickUpRp, String creditoCamionPickUpRp,
			String contadoCarretaRp, String creditoCarretaRp, String contadoCamionetaPanel,
			String creditoCamionetaPanel, String contadoCamionetaRuralSuv, String creditoCamionetaRuralSuv,
			String contadoCisternaCombusRp, String creditoCisternaCombusRp, String contadoMaterialesRp,
			String creditoMaterialesRp, String contadoMercancia, String creditoMercancia, String contadoComplementario,
			String creditoComplementario, String contadoConsCaract, String creditoConsCaract, String contadoTrabCarreta,
			String creditoTrabCarreta, String contadoCertHermeticidad, String creditoCertHermeticidad,
			String contadoFarenetExpress, String creditoFarenetExpress, String contadoCertOpacidad,
			String creditoCertOpacidad, String contadoCertVelocidad, String creditoCertVelocidad,
			String contadoCertConformidad, String creditoCertConformidad, String contadoConstAnalisisGases,
			String creditoConstAnalisisGases) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.nroruc = nroruc;
		this.empresa = empresa;
		this.ejecutivo = ejecutivo;
		this.creditooMaximo = creditooMaximo;
		this.contadoParticularLiv = contadoParticularLiv;
		this.creditoParticularLiv = creditoParticularLiv;
		this.contadoCamionetaRural = contadoCamionetaRural;
		this.creditoCamionetaRural = creditoCamionetaRural;
		this.contadoCamionetaPickUp = contadoCamionetaPickUp;
		this.creditoCamionetaPickUp = creditoCamionetaPickUp;
		this.contadoMotoLineal = contadoMotoLineal;
		this.creditoMotoLineal = creditoMotoLineal;
		this.contadoMotoTaxi = contadoMotoTaxi;
		this.creditoMotoTaxi = creditoMotoTaxi;
		this.contadoTaxi = contadoTaxi;
		this.creditoTaxi = creditoTaxi;
		this.contadoCamionetaMicroComb = contadoCamionetaMicroComb;
		this.creditoCamionetaMicroComb = creditoCamionetaMicroComb;
		this.contadoTuristicoCombCouster = contadoTuristicoCombCouster;
		this.creditoTuristicoCombCouster = creditoTuristicoCombCouster;
		this.contadoTuristicoBus = contadoTuristicoBus;
		this.creditoTuristicoBus = creditoTuristicoBus;
		this.contadoEscolarCombi = contadoEscolarCombi;
		this.creditoEscolarCombi = creditoEscolarCombi;
		this.contadoEscolarBus = contadoEscolarBus;
		this.creditoEscolarBus = creditoEscolarBus;
		this.contadoUrbInterCombCoust = contadoUrbInterCombCoust;
		this.creditoUrbInterCombCoust = creditoUrbInterCombCoust;
		this.contadoUrbInterBus = contadoUrbInterBus;
		this.creditoUrbInterBus = creditoUrbInterBus;
		this.contadoInterPasaBus = contadoInterPasaBus;
		this.creditoInterPasaBus = creditoInterPasaBus;
		this.contadoInterPasaj = contadoInterPasaj;
		this.creditoInterPasaj = creditoInterPasaj;
		this.contadoCamionetaRuralPub = contadoCamionetaRuralPub;
		this.creditoCamionetaRuralPub = creditoCamionetaRuralPub;
		this.contadoAmbulancia = contadoAmbulancia;
		this.creditoAmbulancia = creditoAmbulancia;
		this.contadoCamionRemolcadorM = contadoCamionRemolcadorM;
		this.creditoCamionRemolcadorM = creditoCamionRemolcadorM;
		this.contadoCisternaM = contadoCisternaM;
		this.creditoCisternaM = creditoCisternaM;
		this.contadoCarretaM = contadoCarretaM;
		this.creditoCarretaM = creditoCarretaM;
		this.contadoCamionetaPickUpM = contadoCamionetaPickUpM;
		this.creditoCamionetaPickUpM = creditoCamionetaPickUpM;
		this.contadoCamionetaFurgonM = contadoCamionetaFurgonM;
		this.creditoCamionetaFurgonM = creditoCamionetaFurgonM;
		this.contadoCamionetaPanelM = contadoCamionetaPanelM;
		this.creditoCamionetaPanelM = creditoCamionetaPanelM;
		this.contadoCamionetaRuralM = contadoCamionetaRuralM;
		this.creditoCamionetaRuralM = creditoCamionetaRuralM;
		this.contadoCamionRemolRp = contadoCamionRemolRp;
		this.creditoCamionRemolRp = creditoCamionRemolRp;
		this.contadoCamionN2Rp = contadoCamionN2Rp;
		this.creditoCamionN2Rp = creditoCamionN2Rp;
		this.contadoCamionPickUpRp = contadoCamionPickUpRp;
		this.creditoCamionPickUpRp = creditoCamionPickUpRp;
		this.contadoCarretaRp = contadoCarretaRp;
		this.creditoCarretaRp = creditoCarretaRp;
		this.contadoCamionetaPanel = contadoCamionetaPanel;
		this.creditoCamionetaPanel = creditoCamionetaPanel;
		this.contadoCamionetaRuralSuv = contadoCamionetaRuralSuv;
		this.creditoCamionetaRuralSuv = creditoCamionetaRuralSuv;
		this.contadoCisternaCombusRp = contadoCisternaCombusRp;
		this.creditoCisternaCombusRp = creditoCisternaCombusRp;
		this.contadoMaterialesRp = contadoMaterialesRp;
		this.creditoMaterialesRp = creditoMaterialesRp;
		this.contadoMercancia = contadoMercancia;
		this.creditoMercancia = creditoMercancia;
		this.contadoComplementario = contadoComplementario;
		this.creditoComplementario = creditoComplementario;
		this.contadoConsCaract = contadoConsCaract;
		this.creditoConsCaract = creditoConsCaract;
		this.contadoTrabCarreta = contadoTrabCarreta;
		this.creditoTrabCarreta = creditoTrabCarreta;
		this.contadoCertHermeticidad = contadoCertHermeticidad;
		this.creditoCertHermeticidad = creditoCertHermeticidad;
		this.contadoFarenetExpress = contadoFarenetExpress;
		this.creditoFarenetExpress = creditoFarenetExpress;
		this.contadoCertOpacidad = contadoCertOpacidad;
		this.creditoCertOpacidad = creditoCertOpacidad;
		this.contadoCertVelocidad = contadoCertVelocidad;
		this.creditoCertVelocidad = creditoCertVelocidad;
		this.contadoCertConformidad = contadoCertConformidad;
		this.creditoCertConformidad = creditoCertConformidad;
		this.contadoConstAnalisisGases = contadoConstAnalisisGases;
		this.creditoConstAnalisisGases = creditoConstAnalisisGases;
	}
	
	public ReporteConvenioBean(ReporteConvenioBean bean) {
		
	}
	
	

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public double getCreditooMaximo() {
		return creditooMaximo;
	}

	public void setCreditooMaximo(double creditooMaximo) {
		this.creditooMaximo = creditooMaximo;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNroruc() {
		return nroruc;
	}
	public void setNroruc(String nroruc) {
		this.nroruc = nroruc;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getEjecutivo() {
		return ejecutivo;
	}
	public void setEjecutivo(String ejecutivo) {
		this.ejecutivo = ejecutivo;
	}
	public double getCreditoMaximo() {
		return creditooMaximo;
	}
	public void setCreditoMaximo(double creditooMaximo) {
		this.creditooMaximo = creditooMaximo;
	}
	public String getContadoParticularLiv() {
		return contadoParticularLiv;
	}
	public void setContadoParticularLiv(String contadoParticularLiv) {
		this.contadoParticularLiv = contadoParticularLiv;
	}
	public String getCreditoParticularLiv() {
		return creditoParticularLiv;
	}
	public void setCreditoParticularLiv(String creditoParticularLiv) {
		this.creditoParticularLiv = creditoParticularLiv;
	}
	public String getContadoCamionetaRural() {
		return contadoCamionetaRural;
	}
	public void setContadoCamionetaRural(String contadoCamionetaRural) {
		this.contadoCamionetaRural = contadoCamionetaRural;
	}
	public String getCreditoCamionetaRural() {
		return creditoCamionetaRural;
	}
	public void setCreditoCamionetaRural(String creditoCamionetaRural) {
		this.creditoCamionetaRural = creditoCamionetaRural;
	}
	public String getContadoCamionetaPickUp() {
		return contadoCamionetaPickUp;
	}
	public void setContadoCamionetaPickUp(String contadoCamionetaPickUp) {
		this.contadoCamionetaPickUp = contadoCamionetaPickUp;
	}
	public String getCreditoCamionetaPickUp() {
		return creditoCamionetaPickUp;
	}
	public void setCreditoCamionetaPickUp(String creditoCamionetaPickUp) {
		this.creditoCamionetaPickUp = creditoCamionetaPickUp;
	}
	public String getContadoMotoLineal() {
		return contadoMotoLineal;
	}
	public void setContadoMotoLineal(String contadoMotoLineal) {
		this.contadoMotoLineal = contadoMotoLineal;
	}
	public String getCreditoMotoLineal() {
		return creditoMotoLineal;
	}
	public void setCreditoMotoLineal(String creditoMotoLineal) {
		this.creditoMotoLineal = creditoMotoLineal;
	}
	public String getContadoMotoTaxi() {
		return contadoMotoTaxi;
	}
	public void setContadoMotoTaxi(String contadoMotoTaxi) {
		this.contadoMotoTaxi = contadoMotoTaxi;
	}
	public String getCreditoMotoTaxi() {
		return creditoMotoTaxi;
	}
	public void setCreditoMotoTaxi(String creditoMotoTaxi) {
		this.creditoMotoTaxi = creditoMotoTaxi;
	}
	public String getContadoTaxi() {
		return contadoTaxi;
	}
	public void setContadoTaxi(String contadoTaxi) {
		this.contadoTaxi = contadoTaxi;
	}
	public String getCreditoTaxi() {
		return creditoTaxi;
	}
	public void setCreditoTaxi(String creditoTaxi) {
		this.creditoTaxi = creditoTaxi;
	}
	public String getContadoCamionetaMicroComb() {
		return contadoCamionetaMicroComb;
	}
	public void setContadoCamionetaMicroComb(String contadoCamionetaMicroComb) {
		this.contadoCamionetaMicroComb = contadoCamionetaMicroComb;
	}
	public String getCreditoCamionetaMicroComb() {
		return creditoCamionetaMicroComb;
	}
	public void setCreditoCamionetaMicroComb(String creditoCamionetaMicroComb) {
		this.creditoCamionetaMicroComb = creditoCamionetaMicroComb;
	}
	public String getContadoTuristicoCombCouster() {
		return contadoTuristicoCombCouster;
	}
	public void setContadoTuristicoCombCouster(String contadoTuristicoCombCouster) {
		this.contadoTuristicoCombCouster = contadoTuristicoCombCouster;
	}
	public String getCreditoTuristicoCombCouster() {
		return creditoTuristicoCombCouster;
	}
	public void setCreditoTuristicoCombCouster(String creditoTuristicoCombCouster) {
		this.creditoTuristicoCombCouster = creditoTuristicoCombCouster;
	}
	public String getContadoTuristicoBus() {
		return contadoTuristicoBus;
	}
	public void setContadoTuristicoBus(String contadoTuristicoBus) {
		this.contadoTuristicoBus = contadoTuristicoBus;
	}
	public String getCreditoTuristicoBus() {
		return creditoTuristicoBus;
	}
	public void setCreditoTuristicoBus(String creditoTuristicoBus) {
		this.creditoTuristicoBus = creditoTuristicoBus;
	}
	public String getContadoEscolarCombi() {
		return contadoEscolarCombi;
	}
	public void setContadoEscolarCombi(String contadoEscolarCombi) {
		this.contadoEscolarCombi = contadoEscolarCombi;
	}
	public String getCreditoEscolarCombi() {
		return creditoEscolarCombi;
	}
	public void setCreditoEscolarCombi(String creditoEscolarCombi) {
		this.creditoEscolarCombi = creditoEscolarCombi;
	}
	public String getContadoEscolarBus() {
		return contadoEscolarBus;
	}
	public void setContadoEscolarBus(String contadoEscolarBus) {
		this.contadoEscolarBus = contadoEscolarBus;
	}
	public String getCreditoEscolarBus() {
		return creditoEscolarBus;
	}
	public void setCreditoEscolarBus(String creditoEscolarBus) {
		this.creditoEscolarBus = creditoEscolarBus;
	}
	public String getContadoUrbInterCombCoust() {
		return contadoUrbInterCombCoust;
	}
	public void setContadoUrbInterCombCoust(String contadoUrbInterCombCoust) {
		this.contadoUrbInterCombCoust = contadoUrbInterCombCoust;
	}
	public String getCreditoUrbInterCombCoust() {
		return creditoUrbInterCombCoust;
	}
	public void setCreditoUrbInterCombCoust(String creditoUrbInterCombCoust) {
		this.creditoUrbInterCombCoust = creditoUrbInterCombCoust;
	}
	public String getContadoUrbInterBus() {
		return contadoUrbInterBus;
	}
	public void setContadoUrbInterBus(String contadoUrbInterBus) {
		this.contadoUrbInterBus = contadoUrbInterBus;
	}
	public String getCreditoUrbInterBus() {
		return creditoUrbInterBus;
	}
	public void setCreditoUrbInterBus(String creditoUrbInterBus) {
		this.creditoUrbInterBus = creditoUrbInterBus;
	}
	public String getContadoInterPasaBus() {
		return contadoInterPasaBus;
	}
	public void setContadoInterPasaBus(String contadoInterPasaBus) {
		this.contadoInterPasaBus = contadoInterPasaBus;
	}
	public String getCreditoInterPasaBus() {
		return creditoInterPasaBus;
	}
	public void setCreditoInterPasaBus(String creditoInterPasaBus) {
		this.creditoInterPasaBus = creditoInterPasaBus;
	}
	public String getContadoInterPasaj() {
		return contadoInterPasaj;
	}
	public void setContadoInterPasaj(String contadoInterPasaj) {
		this.contadoInterPasaj = contadoInterPasaj;
	}
	public String getCreditoInterPasaj() {
		return creditoInterPasaj;
	}
	public void setCreditoInterPasaj(String creditoInterPasaj) {
		this.creditoInterPasaj = creditoInterPasaj;
	}
	public String getContadoCamionetaRuralPub() {
		return contadoCamionetaRuralPub;
	}
	public void setContadoCamionetaRuralPub(String contadoCamionetaRuralPub) {
		this.contadoCamionetaRuralPub = contadoCamionetaRuralPub;
	}
	public String getCreditoCamionetaRuralPub() {
		return creditoCamionetaRuralPub;
	}
	public void setCreditoCamionetaRuralPub(String creditoCamionetaRuralPub) {
		this.creditoCamionetaRuralPub = creditoCamionetaRuralPub;
	}
	public String getContadoAmbulancia() {
		return contadoAmbulancia;
	}
	public void setContadoAmbulancia(String contadoAmbulancia) {
		this.contadoAmbulancia = contadoAmbulancia;
	}
	public String getCreditoAmbulancia() {
		return creditoAmbulancia;
	}
	public void setCreditoAmbulancia(String creditoAmbulancia) {
		this.creditoAmbulancia = creditoAmbulancia;
	}
	public String getContadoCamionRemolcadorM() {
		return contadoCamionRemolcadorM;
	}
	public void setContadoCamionRemolcadorM(String contadoCamionRemolcadorM) {
		this.contadoCamionRemolcadorM = contadoCamionRemolcadorM;
	}
	public String getCreditoCamionRemolcadorM() {
		return creditoCamionRemolcadorM;
	}
	public void setCreditoCamionRemolcadorM(String creditoCamionRemolcadorM) {
		this.creditoCamionRemolcadorM = creditoCamionRemolcadorM;
	}
	public String getContadoCisternaM() {
		return contadoCisternaM;
	}
	public void setContadoCisternaM(String contadoCisternaM) {
		this.contadoCisternaM = contadoCisternaM;
	}
	public String getCreditoCisternaM() {
		return creditoCisternaM;
	}
	public void setCreditoCisternaM(String creditoCisternaM) {
		this.creditoCisternaM = creditoCisternaM;
	}
	public String getContadoCarretaM() {
		return contadoCarretaM;
	}
	public void setContadoCarretaM(String contadoCarretaM) {
		this.contadoCarretaM = contadoCarretaM;
	}
	public String getCreditoCarretaM() {
		return creditoCarretaM;
	}
	public void setCreditoCarretaM(String creditoCarretaM) {
		this.creditoCarretaM = creditoCarretaM;
	}
	public String getContadoCamionetaPickUpM() {
		return contadoCamionetaPickUpM;
	}
	public void setContadoCamionetaPickUpM(String contadoCamionetaPickUpM) {
		this.contadoCamionetaPickUpM = contadoCamionetaPickUpM;
	}
	public String getCreditoCamionetaPickUpM() {
		return creditoCamionetaPickUpM;
	}
	public void setCreditoCamionetaPickUpM(String creditoCamionetaPickUpM) {
		this.creditoCamionetaPickUpM = creditoCamionetaPickUpM;
	}
	public String getContadoCamionetaFurgonM() {
		return contadoCamionetaFurgonM;
	}
	public void setContadoCamionetaFurgonM(String contadoCamionetaFurgonM) {
		this.contadoCamionetaFurgonM = contadoCamionetaFurgonM;
	}
	public String getCreditoCamionetaFurgonM() {
		return creditoCamionetaFurgonM;
	}
	public void setCreditoCamionetaFurgonM(String creditoCamionetaFurgonM) {
		this.creditoCamionetaFurgonM = creditoCamionetaFurgonM;
	}
	public String getContadoCamionetaPanelM() {
		return contadoCamionetaPanelM;
	}
	public void setContadoCamionetaPanelM(String contadoCamionetaPanelM) {
		this.contadoCamionetaPanelM = contadoCamionetaPanelM;
	}
	public String getCreditoCamionetaPanelM() {
		return creditoCamionetaPanelM;
	}
	public void setCreditoCamionetaPanelM(String creditoCamionetaPanelM) {
		this.creditoCamionetaPanelM = creditoCamionetaPanelM;
	}
	public String getContadoCamionetaRuralM() {
		return contadoCamionetaRuralM;
	}
	public void setContadoCamionetaRuralM(String contadoCamionetaRuralM) {
		this.contadoCamionetaRuralM = contadoCamionetaRuralM;
	}
	public String getCreditoCamionetaRuralM() {
		return creditoCamionetaRuralM;
	}
	public void setCreditoCamionetaRuralM(String creditoCamionetaRuralM) {
		this.creditoCamionetaRuralM = creditoCamionetaRuralM;
	}
	public String getContadoCamionRemolRp() {
		return contadoCamionRemolRp;
	}
	public void setContadoCamionRemolRp(String contadoCamionRemolRp) {
		this.contadoCamionRemolRp = contadoCamionRemolRp;
	}
	public String getCreditoCamionRemolRp() {
		return creditoCamionRemolRp;
	}
	public void setCreditoCamionRemolRp(String creditoCamionRemolRp) {
		this.creditoCamionRemolRp = creditoCamionRemolRp;
	}
	public String getContadoCamionN2Rp() {
		return contadoCamionN2Rp;
	}
	public void setContadoCamionN2Rp(String contadoCamionN2Rp) {
		this.contadoCamionN2Rp = contadoCamionN2Rp;
	}
	public String getCreditoCamionN2Rp() {
		return creditoCamionN2Rp;
	}
	public void setCreditoCamionN2Rp(String creditoCamionN2Rp) {
		this.creditoCamionN2Rp = creditoCamionN2Rp;
	}
	public String getContadoCamionPickUpRp() {
		return contadoCamionPickUpRp;
	}
	public void setContadoCamionPickUpRp(String contadoCamionPickUpRp) {
		this.contadoCamionPickUpRp = contadoCamionPickUpRp;
	}
	public String getCreditoCamionPickUpRp() {
		return creditoCamionPickUpRp;
	}
	public void setCreditoCamionPickUpRp(String creditoCamionPickUpRp) {
		this.creditoCamionPickUpRp = creditoCamionPickUpRp;
	}
	public String getContadoCarretaRp() {
		return contadoCarretaRp;
	}
	public void setContadoCarretaRp(String contadoCarretaRp) {
		this.contadoCarretaRp = contadoCarretaRp;
	}
	public String getCreditoCarretaRp() {
		return creditoCarretaRp;
	}
	public void setCreditoCarretaRp(String creditoCarretaRp) {
		this.creditoCarretaRp = creditoCarretaRp;
	}
	public String getContadoCamionetaPanel() {
		return contadoCamionetaPanel;
	}
	public void setContadoCamionetaPanel(String contadoCamionetaPanel) {
		this.contadoCamionetaPanel = contadoCamionetaPanel;
	}
	public String getCreditoCamionetaPanel() {
		return creditoCamionetaPanel;
	}
	public void setCreditoCamionetaPanel(String creditoCamionetaPanel) {
		this.creditoCamionetaPanel = creditoCamionetaPanel;
	}
	public String getContadoCamionetaRuralSuv() {
		return contadoCamionetaRuralSuv;
	}
	public void setContadoCamionetaRuralSuv(String contadoCamionetaRuralSuv) {
		this.contadoCamionetaRuralSuv = contadoCamionetaRuralSuv;
	}
	public String getCreditoCamionetaRuralSuv() {
		return creditoCamionetaRuralSuv;
	}
	public void setCreditoCamionetaRuralSuv(String creditoCamionetaRuralSuv) {
		this.creditoCamionetaRuralSuv = creditoCamionetaRuralSuv;
	}
	public String getContadoCisternaCombusRp() {
		return contadoCisternaCombusRp;
	}
	public void setContadoCisternaCombusRp(String contadoCisternaCombusRp) {
		this.contadoCisternaCombusRp = contadoCisternaCombusRp;
	}
	public String getCreditoCisternaCombusRp() {
		return creditoCisternaCombusRp;
	}
	public void setCreditoCisternaCombusRp(String creditoCisternaCombusRp) {
		this.creditoCisternaCombusRp = creditoCisternaCombusRp;
	}
	public String getContadoMaterialesRp() {
		return contadoMaterialesRp;
	}
	public void setContadoMaterialesRp(String contadoMaterialesRp) {
		this.contadoMaterialesRp = contadoMaterialesRp;
	}
	public String getCreditoMaterialesRp() {
		return creditoMaterialesRp;
	}
	public void setCreditoMaterialesRp(String creditoMaterialesRp) {
		this.creditoMaterialesRp = creditoMaterialesRp;
	}
	public String getContadoMercancia() {
		return contadoMercancia;
	}
	public void setContadoMercancia(String contadoMercancia) {
		this.contadoMercancia = contadoMercancia;
	}
	public String getCreditoMercancia() {
		return creditoMercancia;
	}
	public void setCreditoMercancia(String creditoMercancia) {
		this.creditoMercancia = creditoMercancia;
	}
	public String getContadoComplementario() {
		return contadoComplementario;
	}
	public void setContadoComplementario(String contadoComplementario) {
		this.contadoComplementario = contadoComplementario;
	}
	public String getCreditoComplementario() {
		return creditoComplementario;
	}
	public void setCreditoComplementario(String creditoComplementario) {
		this.creditoComplementario = creditoComplementario;
	}
	public String getContadoConsCaract() {
		return contadoConsCaract;
	}
	public void setContadoConsCaract(String contadoConsCaract) {
		this.contadoConsCaract = contadoConsCaract;
	}
	public String getCreditoConsCaract() {
		return creditoConsCaract;
	}
	public void setCreditoConsCaract(String creditoConsCaract) {
		this.creditoConsCaract = creditoConsCaract;
	}
	public String getContadoTrabCarreta() {
		return contadoTrabCarreta;
	}
	public void setContadoTrabCarreta(String contadoTrabCarreta) {
		this.contadoTrabCarreta = contadoTrabCarreta;
	}
	public String getCreditoTrabCarreta() {
		return creditoTrabCarreta;
	}
	public void setCreditoTrabCarreta(String creditoTrabCarreta) {
		this.creditoTrabCarreta = creditoTrabCarreta;
	}
	public String getContadoCertHermeticidad() {
		return contadoCertHermeticidad;
	}
	public void setContadoCertHermeticidad(String contadoCertHermeticidad) {
		this.contadoCertHermeticidad = contadoCertHermeticidad;
	}
	public String getCreditoCertHermeticidad() {
		return creditoCertHermeticidad;
	}
	public void setCreditoCertHermeticidad(String creditoCertHermeticidad) {
		this.creditoCertHermeticidad = creditoCertHermeticidad;
	}
	public String getContadoFarenetExpress() {
		return contadoFarenetExpress;
	}
	public void setContadoFarenetExpress(String contadoFarenetExpress) {
		this.contadoFarenetExpress = contadoFarenetExpress;
	}
	public String getCreditoFarenetExpress() {
		return creditoFarenetExpress;
	}
	public void setCreditoFarenetExpress(String creditoFarenetExpress) {
		this.creditoFarenetExpress = creditoFarenetExpress;
	}
	public String getContadoCertOpacidad() {
		return contadoCertOpacidad;
	}
	public void setContadoCertOpacidad(String contadoCertOpacidad) {
		this.contadoCertOpacidad = contadoCertOpacidad;
	}
	public String getCreditoCertOpacidad() {
		return creditoCertOpacidad;
	}
	public void setCreditoCertOpacidad(String creditoCertOpacidad) {
		this.creditoCertOpacidad = creditoCertOpacidad;
	}
	public String getContadoCertVelocidad() {
		return contadoCertVelocidad;
	}
	public void setContadoCertVelocidad(String contadoCertVelocidad) {
		this.contadoCertVelocidad = contadoCertVelocidad;
	}
	public String getCreditoCertVelocidad() {
		return creditoCertVelocidad;
	}
	public void setCreditoCertVelocidad(String creditoCertVelocidad) {
		this.creditoCertVelocidad = creditoCertVelocidad;
	}
	public String getContadoCertConformidad() {
		return contadoCertConformidad;
	}
	public void setContadoCertConformidad(String contadoCertConformidad) {
		this.contadoCertConformidad = contadoCertConformidad;
	}
	public String getCreditoCertConformidad() {
		return creditoCertConformidad;
	}
	public void setCreditoCertConformidad(String creditoCertConformidad) {
		this.creditoCertConformidad = creditoCertConformidad;
	}
	public String getContadoConstAnalisisGases() {
		return contadoConstAnalisisGases;
	}
	public void setContadoConstAnalisisGases(String contadoConstAnalisisGases) {
		this.contadoConstAnalisisGases = contadoConstAnalisisGases;
	}
	public String getCreditoConstAnalisisGases() {
		return creditoConstAnalisisGases;
	}
	public void setCreditoConstAnalisisGases(String creditoConstAnalisisGases) {
		this.creditoConstAnalisisGases = creditoConstAnalisisGases;
	}
	
	public ReporteConvenioBean validarCampos(ReporteConvenioBean convenioBean, List<ConceptoPrecioBean> precioBean){
		
		for(ConceptoPrecioBean bean : precioBean){
			switch (bean.getConcepto()) {
			case "1":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCamionetaRural((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCamionetaRural((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "2":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoParticularLiv((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoParticularLiv((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "3":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoTaxi((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoTaxi((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "4":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCamionetaPickUp((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCamionetaPickUp((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "5":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCamionetaPanel((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCamionetaPanel((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "6":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoMercancia((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoMercancia((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "7":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoTuristicoBus((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoTuristicoBus((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "8":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoTuristicoCombCouster((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoTuristicoCombCouster((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "9":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoInterPasaBus((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoInterPasaBus((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "10":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoMaterialesRp((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoMaterialesRp((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "11":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCarretaM((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCarretaM((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "12":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoConsCaract((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoConsCaract((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "13":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoTrabCarreta((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoTrabCarreta((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "14":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCisternaM((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCisternaM((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "15":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoEscolarBus((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoEscolarBus((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "16":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoEscolarCombi((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoEscolarCombi((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "17":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoUrbInterBus((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoUrbInterBus((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "18":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoUrbInterCombCoust((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoUrbInterCombCoust((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "20":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoInterPasaj((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoInterPasaj((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "21":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCamionetaPickUp((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCamionetaPickUp((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "23":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCamionetaPanelM((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCamionetaPanelM((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "25":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCertHermeticidad((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCertHermeticidad((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "26":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCertOpacidad((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCertOpacidad((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "27":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCertVelocidad((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCertVelocidad((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "28":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCamionetaMicroComb((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCamionetaMicroComb((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "29":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCamionetaRuralPub((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCamionetaRuralPub((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "30":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoAmbulancia((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoAmbulancia((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "31":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoAmbulancia((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoAmbulancia((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "32":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCamionetaFurgonM((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCamionetaFurgonM((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "33":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCamionetaRuralM((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCamionetaRuralM((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "34":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCamionRemolcadorM((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCamionRemolcadorM((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "35":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCamionN2Rp((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCamionN2Rp((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "36":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCamionPickUpRp((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCamionPickUpRp((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "37":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCarretaRp((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCarretaRp((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "38":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCisternaCombusRp((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCisternaCombusRp((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "39":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCamionetaRuralSuv((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCamionetaRuralSuv((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "40":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoFarenetExpress((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoFarenetExpress((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "41":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoCertConformidad((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoCertConformidad((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "42":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoConstAnalisisGases((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoConstAnalisisGases((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "43":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoComplementario((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoComplementario((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "44":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoMotoLineal((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoMotoLineal((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			case "45":
				if(bean.getFormapago().equals("contado"))
					convenioBean.setContadoMotoTaxi((!bean.getMonto().equals("0"))?bean.getMonto():"");
				if(bean.getFormapago().equals("credito"))
					convenioBean.setCreditoMotoTaxi((!bean.getMonto().equals("0"))?bean.getMonto():"");
				break;
			default:
				break;
			}
			
		}
		
		return convenioBean;
		
	}
	
	
}
