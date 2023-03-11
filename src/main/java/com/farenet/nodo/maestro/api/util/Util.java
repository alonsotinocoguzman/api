package com.farenet.nodo.maestro.api.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.farenet.nodo.maestro.api.caja.domain.Cierre;

public class Util {
	public static final String hourStarWork = "06:00";
	
	public static String leadingZeros(String s, int length) {
		if (s.length() >= length)
			return s;
		else
			return String.format("%0" + (length - s.length()) + "d%s", 0, s);
	}

	public static Timestamp getFechMaxCierre(Cierre currentCierre) {
		Long milisecondsCierre = Util.hourToMiliseconds(currentCierre.getHoraMax());
		Long milisecondsCurrentDateSinHora = Util.milisecondsCurrentDateSinHora();
		Timestamp fechaMax = new Timestamp(milisecondsCurrentDateSinHora + milisecondsCierre);
		return fechaMax;
	}

	public static Timestamp getFechMaxCierre(Cierre currentCierre, Date date) {
		Long milisecondsCierre = Util.hourToMiliseconds(currentCierre.getHoraMax());
		Long milisecondsDateSinHora = Util.milisecondsDateSinHora(date);
		Timestamp fechaMax = new Timestamp(milisecondsDateSinHora + milisecondsCierre);
		return fechaMax;
	}

	public static Timestamp getFechMinCierre(List<Cierre> cierresOrdenados, Cierre currentCierre) {
		Long milisecondsCurrentDateSinHora = Util.milisecondsCurrentDateSinHora();
		return logicaMinCierre(cierresOrdenados, currentCierre, milisecondsCurrentDateSinHora);
	}

	public static Timestamp getFechMinCierre(List<Cierre> cierresOrdenados, Cierre currentCierre, Date date) {
		Long milisecondsDateSinHora = Util.milisecondsDateSinHora(date);
		return logicaMinCierre(cierresOrdenados, currentCierre, milisecondsDateSinHora);
	}

	private static Timestamp logicaMinCierre(List<Cierre> cierresOrdenados, Cierre currentCierre,
			Long milisecondsDateSinHora) {
		Timestamp fechaMin = new Timestamp(milisecondsDateSinHora + Util.hourToMiliseconds(hourStarWork));
		// Como los cierres estan ordenados por horario, en este algoritmo
		// verificamos que cierre pertecene al cierre de caja ya calculado por
		// la linea, si se compara y ambos cierres son iguales, para obtener la
		// fecha minima
		// debemos obtenerla con el cierre anterior
		for (int i = 0; i < cierresOrdenados.size(); i++) {
			if (cierresOrdenados.get(i).getKey().equals(currentCierre.getKey())) {
				if (i != 0) {
					Long milisecondsFechaMin = milisecondsDateSinHora
							+ Util.hourToMiliseconds(cierresOrdenados.get(i - 1).getHoraMax());
					fechaMin = new Timestamp(milisecondsFechaMin);
					break;
				}
			}
		}
		return fechaMin;
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public static float randFloat(long min, long max) {
		Random rand = new Random();
		float randomNum = rand.nextInt((int) ((max - min) + 1)) + min;
		return randomNum;
	}

	public static Long hourToMiliseconds(String hour) {
		String[] time = hour.split(":");
		Long miliseconds = (Long.valueOf(time[0]) * 60 * 60 * 1000) + (Long.valueOf(time[1]) * 60 * 1000);
		if (time.length > 2) {
			miliseconds = miliseconds + ((Long.valueOf(time[2]) * 1000));
		}
		return miliseconds;
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
		Long milisecondsCurrentDateSinHora = date.getTime() - Util.hourToMiliseconds(currentHour);
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
		Long milisecondsCurrentDateSinHora = date.getTime() - Util.hourToMiliseconds(currentHour);
		Long milisecondsMaxHora = Util.hourToMiliseconds("23:59");
		Timestamp finDay = new Timestamp(milisecondsCurrentDateSinHora + milisecondsMaxHora);
		return finDay;
	}

	public static Long milisecondsCurrentDateSinHora() {
		Date date = new Date();
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		String currentHour = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":"
				+ calendar.get(Calendar.SECOND);
		Long milisecondsCurrentDateSinHora = date.getTime() - Util.hourToMiliseconds(currentHour);
		return milisecondsCurrentDateSinHora;
	}

	public static Long milisecondsDateSinHora(Date date) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		String currentHour = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":"
				+ calendar.get(Calendar.SECOND);
		Long milisecondsCurrentDateSinHora = date.getTime() - Util.hourToMiliseconds(currentHour);
		return milisecondsCurrentDateSinHora;
	}

	public static String printError(Exception e) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);

		return sw.toString();
	}
	
	public static String getUserNameContext()
	{
		 String userName = null;
	    	if (SecurityContextHolder.getContext().getAuthentication() != null) {
		    	  
	    	  	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	        HashMap<String, String> principal = (HashMap<String, String>) authentication.getPrincipal();

	        userName = principal.get("username");
	    	}
	    	
	    	return userName;
	}
	
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
