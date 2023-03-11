package com.farenet.nodo.maestro.api.util;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;

import com.farenet.nodo.maestro.api.inspeccion.domain.Tarjetapropiedad;

public class TarjetaIdOrGenerate extends IdentityGenerator {

    public Serializable generate(SessionImplementor session, Object obj) throws HibernateException {
        if (obj == null) throw new HibernateException(new NullPointerException()) ;

         return ((Tarjetapropiedad) obj).getId();//id is not null so using assigned id.

        
    }
}