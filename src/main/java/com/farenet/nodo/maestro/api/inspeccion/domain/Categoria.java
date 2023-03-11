package com.farenet.nodo.maestro.api.inspeccion.domain;

import javax.persistence.*;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.farenet.nodo.maestro.api.caja.domain.view.ComprobanteViews;
import com.farenet.nodo.maestro.api.util.Auditoria;
import com.farenet.nodo.maestro.api.util.UIUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Table(name = "categoria")
public class Categoria {

	@JsonView(ComprobanteViews.CrudComprobante.class)
	@Column(nullable = false, length=150)
	private String nombre;
	
	@Id
	@Column(length=20)
	private String key;

	@Column(name = "keymtc")
	private Integer keyMTC;
	
	
	public Integer getKeyMTC() {
		return keyMTC;
	}

	public void setKeyMTC(Integer keyMTC) {
		this.keyMTC = keyMTC;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean esCarreta() {
        boolean esCarreta = false;
        for (String tipoCarreta : UIUtils.tipoCarreta()) {
            esCarreta = (key.toUpperCase().equals(tipoCarreta));
            if (esCarreta) {
                break;
            }
        }
        return esCarreta;
    }
	
	public boolean esMoto() {
		boolean esMoto = false;
		for (String tipoMoto : UIUtils.tipoMoto()) {
			esMoto = (key.toUpperCase().equals(tipoMoto));
			if (esMoto) {
				break;
			}
		}
	    return esMoto;
	}
	 
	public boolean esMotoCarro() {
		boolean esMotoCarro = false;
	    for (String tipoMotoCarro : UIUtils.tipoMotoCarro()) {
	    	esMotoCarro = (key.toUpperCase().equals(tipoMotoCarro));
	        if (esMotoCarro) {
	        	break;
	        }
	    }
	    return esMotoCarro;
	}
	
	
}