package com.farenet.nodo.maestro.api.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import com.farenet.nodo.maestro.api.caja.domain.Cierre;
import com.farenet.nodo.maestro.api.caja.domain.CondicionvigenciaCertificado;
import com.farenet.nodo.maestro.api.inspeccion.domain.Inspeccion;
import com.farenet.nodo.maestro.api.inspeccion.domain.SeriedocumentoBase;
import com.farenet.nodo.maestro.api.inspeccion.domain.Tipocertificado;
import com.farenet.nodo.maestro.api.inspeccion.domain.Vehiculo;
import com.farenet.nodo.maestro.api.linea.domain.Defecto;
import com.farenet.nodo.maestro.api.linea.domain.ResultadoMaquina;

public class UIUtils {

    public static final String hourStarWork = "06:00";
    public static final String FORMATO_HORA_AM_PM = "hh:mm:ss a";
    public static final String FORMATO_HORA_24_H = "HH:mm:ss";
    public static final String FORMATO_FECHA_SHORT = "yyyy-MM-dd";
    public static final String FORMATO_HORA_WITH_MILISECONDS = "hh:mm:ss.SSS"; 
    public static final String FORMATO_FECHA_PERU = "dd/MM/yyyy";
    public static final String FORMATO_FECHA_MEDIUM = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATO_FECHA = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String FORMATO_FECHA_SHORT_HOME = "dd-MM-yyyy";
    public static final String FORMATO_FECHA_SQL = "yyyy-MM-dd";

    public static final int CEROS_9IZQUIERDA = 9;
    public static final int CEROS_8IZQUIERDA = 8;

    
    public static Long milisecondsDateSinHora(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        String currentHour = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":"
                + calendar.get(Calendar.SECOND);
        Long milisecondsCurrentDateSinHora = date.getTime() - hourToMiliseconds(currentHour);
        return milisecondsCurrentDateSinHora;
    }

    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static int milisecondsToDay(Long milliseconds) {
        int days = (int) (milliseconds / (1000 * 60 * 60 * 24));
        return days;
    }

    public static String formatoNroComprobante(String serie, Long nroNuevo) {
        return serie + "-" + leadingZeros(nroNuevo, CEROS_9IZQUIERDA);
    }
    

    public static String formato8NroComprobante(String serie, Long nroNuevo) {
        return serie + "-" + leadingZeros(nroNuevo, CEROS_8IZQUIERDA);
    }

    
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static Long monthToMiliseconds(int months) {
        Calendar today = Calendar.getInstance();
        Calendar sixMonthsAhead = Calendar.getInstance();
        sixMonthsAhead.add(Calendar.MONTH, months);
        long differenceInMilis = sixMonthsAhead.getTimeInMillis() - today.getTimeInMillis();
        return differenceInMilis;
    }

    public static Long hourToMiliseconds(String hour) {
        String[] time = hour.split(":");
        Long miliseconds = (Long.valueOf(time[0]) * 60 * 60 * 1000) + (Long.valueOf(time[1]) * 60 * 1000);
        if (time.length > 2) {
            miliseconds = miliseconds + ((Long.valueOf(time[2]) * 1000));
        }
        return miliseconds;
    }

    public static Long milisecondsCurrentDateSinHora() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        String currentHour = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":"
                + calendar.get(Calendar.SECOND);
        Long milisecondsCurrentDateSinHora = date.getTime() - UIUtils.hourToMiliseconds(currentHour);
        return milisecondsCurrentDateSinHora;
    }

    public static Long milisecondsCurrentDate() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        Long milisecondsCurrentDateSinHora = date.getTime();
        return milisecondsCurrentDateSinHora;
    }

    public static Timestamp initDay() {
        Date date = new Date();
        return initDay(date);
    }

    public static Timestamp initDay(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        String currentHour = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":"
                + calendar.get(Calendar.SECOND);
        Long milisecondsCurrentDateSinHora = date.getTime() - hourToMiliseconds(currentHour);
        Timestamp initDay = new Timestamp(milisecondsCurrentDateSinHora);
        return initDay;
    }

    public static Timestamp finDay() {
        Date date = new Date();
        return finDay(date);
    }

    public static Timestamp finDay(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        String currentHour = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":"
                + calendar.get(Calendar.SECOND);
        Long milisecondsCurrentDateSinHora = date.getTime() - hourToMiliseconds(currentHour);
        Long milisecondsMaxHora = hourToMiliseconds("23:59");
        Timestamp finDay = new Timestamp(milisecondsCurrentDateSinHora + milisecondsMaxHora);
        return finDay;
    }

    public static String formatoNroInspeccion(String planta, Long nroNuevo) {
        return "INS-" + planta + "-" + leadingZeros(nroNuevo, CEROS_9IZQUIERDA);
    }
    
    public static String formatoNroCertificadoByPlanta(SeriedocumentoBase seriedocumentoBase, Long nroNuevo) {
        // return seriedocumento.getCodigoproveedor() + "-" +
        // PropertyUtils.getProperty("planta_key") + "-"
        // + seriedocumento.getLinea().getKey() + "-" + leadingZeros(nroNuevo,
        // CEROS_IZQUIERDA);
        return seriedocumentoBase.getCodigoproveedor() + "-" + seriedocumentoBase.getPlanta().getKeyMtc() + "-"
                + leadingZeros(nroNuevo, CEROS_9IZQUIERDA);
    }
    
    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    public static Timestamp getFechMaxCierre(Cierre currentCierre, Date date) {
        Long milisecondsCierre = hourToMiliseconds(currentCierre.getHoraMax());
        Long milisecondsDateSinHora = milisecondsDateSinHora(date);
        Timestamp fechaMax = new Timestamp(milisecondsDateSinHora + milisecondsCierre);
        return fechaMax;
    }

    public static Timestamp getFechMinCierre(List<Cierre> cierresOrdenados, Cierre currentCierre) {
        Long milisecondsCurrentDateSinHora = milisecondsCurrentDateSinHora();
        return logicaMinCierre(cierresOrdenados, currentCierre, milisecondsCurrentDateSinHora);
    }

    public static Timestamp getFechMinCierre(List<Cierre> cierresOrdenados,  Cierre currentCierre, Date date) {
        Long milisecondsDateSinHora = milisecondsDateSinHora(date);
        return logicaMinCierre(cierresOrdenados, currentCierre, milisecondsDateSinHora);
    }
    

    private static Timestamp logicaMinCierre(List<Cierre> cierresOrdenados, Cierre currentCierre,
                                             Long milisecondsDateSinHora) {
        Timestamp fechaMin = new Timestamp(milisecondsDateSinHora + hourToMiliseconds(hourStarWork));
        // Como los cierres estan ordenados por horario, en este algoritmo
        // verificamos que cierre pertecene al cierre de caja ya calculado por
        // la linea, si se compara y ambos cierres son iguales, para obtener la
        // fecha minima
        // debemos obtenerla con el cierre anterior
        for (int i = 0; i < cierresOrdenados.size(); i++) {
            if (cierresOrdenados.get(i).getKey().equals(currentCierre.getKey())) {
                if (i != 0) {
                    Long milisecondsFechaMin = milisecondsDateSinHora
                            + hourToMiliseconds(cierresOrdenados.get(i - 1).getHoraMax());
                    fechaMin = new Timestamp(milisecondsFechaMin);
                    break;
                }
            }
        }
        return fechaMin;
    }

    public static int daysBetween(Calendar day1, Calendar day2){
        Calendar dayOne = (Calendar) day1.clone(),
                dayTwo = (Calendar) day2.clone();

        if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)) {
            return Math.abs(dayOne.get(Calendar.DAY_OF_YEAR) - dayTwo.get(Calendar.DAY_OF_YEAR));
        } else {
            if (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
                //swap them
                Calendar temp = dayOne;
                dayOne = dayTwo;
                dayTwo = temp;
            }
            int extraDays = 0;

            int dayOneOriginalYearDays = dayOne.get(Calendar.DAY_OF_YEAR);

            while (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
                dayOne.add(Calendar.YEAR, -1);
                // getActualMaximum() important for leap years
                extraDays += dayOne.getActualMaximum(Calendar.DAY_OF_YEAR);
            }

            return extraDays - dayTwo.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays ;
        }
    }
    
    public static String printError(Exception e) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);

		return sw.toString();
	}
    public static String leadingZeros(Long s, int length) {
        if (s.toString().length() >= length)
            return s.toString();
        else
            return String.format("%0" + (length - s.toString().length()) + "d%s", 0, s);
    }
    
    public static double calcularIgv(double baseImponible, double totalPagar) {
        return round((totalPagar - baseImponible), 2);
    }

    public static double calcularBaseImponible(double totalPagar) {
        return round((totalPagar / 1.18), 2);
    }
    
    public static double calcularMontoDetraccion(double total) {
        return round((total * 0.12), 2);
    }

    public static String[] tipoCarreta() {
    	String[] tiposCarreta = {"O2", "O3", "O4"};
    	return tiposCarreta;
    }
    
    public static String[] tipoMoto() {
    	String[] tiposMoto = {"L1","L3"};
    	return tiposMoto;
    }
 
    public static String[] tipoMotoCarro() {
    	String[] tiposMoto = { "L2","L4", "L5"};
    	return tiposMoto;
    }
    
    public static String[] tipoMaquinasTestLine() {
        String[] testLine = {"1", "2", "3"};
        return testLine;
    }
    
    public static CondicionvigenciaCertificado findOneByTipocertificado(Tipocertificado tipocertificado, List<CondicionvigenciaCertificado> lstCondicion) {
    	for(CondicionvigenciaCertificado cc : lstCondicion) {
    		if(cc.getTipocertificado().getKey().equals(tipocertificado.getKey()))
    			return cc;
    	}
    	return null;
    }
    
    public static int mesesVigencia(Inspeccion inspeccion, List<CondicionvigenciaCertificado> list) {
    	
    	CondicionvigenciaCertificado condicionvigenciaCertificado = findOneByTipocertificado(inspeccion.getTipocertificado(), list);
        Calendar calendar = Calendar.getInstance();
        Vehiculo vehiculo = inspeccion.getVehiculo();
        int yearActual = calendar.get(Calendar.YEAR);
        int mesesVigencia = inspeccion.getTipocertificado().getMesesvigencia();
        int edadVehiculo = yearActual - vehiculo.getAniofabricacion();
        if (condicionvigenciaCertificado != null) {
            if (condicionvigenciaCertificado.getCondicion().equals(">=")) {
                mesesVigencia = (edadVehiculo >= condicionvigenciaCertificado.getAnio()) ? condicionvigenciaCertificado.getMesVigencia() : mesesVigencia;
            } else if (condicionvigenciaCertificado.getCondicion().equals("<=")) {
                mesesVigencia = (edadVehiculo <= condicionvigenciaCertificado.getAnio()) ? condicionvigenciaCertificado.getMesVigencia() : mesesVigencia;
            } else if (condicionvigenciaCertificado.getCondicion().equals("<")) {
                mesesVigencia = (edadVehiculo < condicionvigenciaCertificado.getAnio()) ? condicionvigenciaCertificado.getMesVigencia() : mesesVigencia;
            } else if (condicionvigenciaCertificado.getCondicion().equals(">")) {
                mesesVigencia = (edadVehiculo > condicionvigenciaCertificado.getAnio()) ? condicionvigenciaCertificado.getMesVigencia() : mesesVigencia;
            } else if (condicionvigenciaCertificado.getCondicion().equals("=")) {
                mesesVigencia = (edadVehiculo == condicionvigenciaCertificado.getAnio()) ? condicionvigenciaCertificado.getMesVigencia() : mesesVigencia;
            } else if (condicionvigenciaCertificado.getCondicion().equals("<>")) {
                mesesVigencia = (edadVehiculo != condicionvigenciaCertificado.getAnio()) ? condicionvigenciaCertificado.getMesVigencia() : mesesVigencia;
            }
        }
        return mesesVigencia;
    }

	public static boolean validaSiRemueveTestLine(Inspeccion inspeccionResult) {
		for (ResultadoMaquina resultadoMaquina : inspeccionResult.getResultadosMaquina()) {
            if (resultadoMaquina.getResultado().equals("D")) {
                if (inspeccionResult.getVehiculo().isLiviano()) {
                    if (isTestLine(resultadoMaquina)) {
                        return true;
                    }
                }
            }
        }
		return false;
	}
	
	public static boolean isTestLine(ResultadoMaquina resultadoMaquina) {
        boolean isTestLine = false;
        for (String tipomaquinaId : UIUtils.tipoMaquinasTestLine()) {
            if (resultadoMaquina.getMaquina().getTipomaquina().getKey().equals(tipomaquinaId)) {
                isTestLine = true;
            }
        }
        return isTestLine;
    }

	public static List<ResultadoMaquina> pasarResultadosSinFoto(List<ResultadoMaquina> resultadosMaquinas) {
		// TODO Auto-generated method stub
		List<ResultadoMaquina> result = new ArrayList<>();
		for(ResultadoMaquina resultadoMaquina : resultadosMaquinas) {
			if(resultadoMaquina.getMaquina().getTipomaquina() != null) {
				if( !(resultadoMaquina.getMaquina().getTipomaquina().getKey().equals("12") ||
						resultadoMaquina.getMaquina().getTipomaquina().getKey().equals("13") ||
						resultadoMaquina.getMaquina().getTipomaquina().getKey().equals("15") ||
						resultadoMaquina.getMaquina().getTipomaquina().getKey().equals("11") ) ) {
					
					ResultadoMaquina bean = new ResultadoMaquina();
					bean.setData(resultadoMaquina.getData());
					bean.setFechainicio(resultadoMaquina.getFechainicio());
					bean.setFechafin(resultadoMaquina.getFechafin());
					bean.setInspVisual(resultadoMaquina.getInspVisual());
					bean.setManual(resultadoMaquina.getManual());
					bean.setPostdata(resultadoMaquina.getPostdata());
					bean.setResultado(resultadoMaquina.getResultado());
					
					List<Defecto> defectos = new ArrayList<>();
					for(Defecto defecto : resultadoMaquina.getDefectos()) {
						Defecto defecto2 = new Defecto();
						defecto2.setCodigovalor(defecto.getCodigovalor());
						defecto2.setNivelpeligro(defecto.getNivelpeligro());
						defecto2.setNombrevalor(defecto.getNombrevalor());
						defectos.add(defecto2);
					}
					
					bean.setDefectos(defectos);
					
					result.add(bean);
				}
			}
		} 
		return result;
	}

	public static int getDiasTranscurridos(String fechapago, String fechaactual){
        int difdias=0;
        SimpleDateFormat df= new SimpleDateFormat(FORMATO_FECHA_SHORT);
        try{
            Date date1 =df.parse(fechapago);
            Date date2 = df.parse(fechaactual);
            long difference= date2.getTime()-date1.getTime();
            float daysdifference=(difference/(1000*60*60*24));
            difdias= Math.round(daysdifference);
        }catch(Exception ex){

        }
        return difdias;
    }
    
}
