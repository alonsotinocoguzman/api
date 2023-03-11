package com.farenet.nodo.maestro.api.inspeccion.domain;

import javax.persistence.*;

@Entity

@Table(name = "detcaja")
public class DetCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length=20)
    private String concepto_key;

    @Column(nullable = false, length=20)
    private String categoria_key;

    @Column(nullable = false, length=20)
    private String tipoinspeccion_key;

    @Column(nullable = false, length=20)
    private String tipocertificado_key;

    @Column(nullable = false, length=20)
    private String tipoautorizacion_key;
}
