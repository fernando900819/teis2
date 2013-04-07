package org.colswe.junitlab.logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static junit.framework.Assert.assertEquals;

import org.colswe.junitlab.logica.imp.ControlFestivo;

import junit.framework.TestCase;
import org.colswe.junitlab.modelo.Festivo;
import org.colswe.junitlab.modelo.TipoDia;

public class ControlFestivoHorasTest extends TestCase {

	public ControlFestivoHorasTest(String name) {
		super(name);
	}
        
        public void testContarHoras(){
            ControlFestivo cf = new ControlFestivo();
            
            Calendar hasta = Calendar.getInstance();
            Calendar desde = Calendar.getInstance();
            hasta.setTimeInMillis(0);
            desde.setTimeInMillis(0);
            desde.set(Calendar.YEAR, 2013);
            hasta.set(Calendar.YEAR, 2013);
            desde.set(Calendar.MONTH, Calendar.JANUARY);
            desde.set(Calendar.DATE, 1);
            hasta.set(Calendar.MONTH, Calendar.JANUARY);
            hasta.set(Calendar.DATE, 31);
            
            assertEquals(new Integer(200), cf.contarHoras(desde.getTime(), hasta.getTime()));
        }
        
        public void testContarHorasFestivosLaborablesyNoLaborables(){
            List<Festivo> festivos = new ArrayList<Festivo>();
            Festivo festivo = produceFestivo(4, true);
            Festivo festivo1 = produceFestivo(8, false);
            Festivo festivo2 = produceFestivo(30, true);
            festivos.add(festivo);
            festivos.add(festivo1);
            festivos.add(festivo2);

            ControlFestivo cf = new ControlFestivo();
            cf.sistema.setEntidades(festivos);
            
            Calendar hasta = Calendar.getInstance();
            Calendar desde = Calendar.getInstance();
            hasta.setTimeInMillis(0);
            desde.setTimeInMillis(0);
            desde.set(Calendar.YEAR, 2013);
            hasta.set(Calendar.YEAR, 2013);
            desde.set(Calendar.MONTH, Calendar.JANUARY);
            desde.set(Calendar.DATE, 1);
            hasta.set(Calendar.MONTH, Calendar.JANUARY);
            hasta.set(Calendar.DATE, 31);

//            Map<TipoDia, Integer> dias = cf.obtenerDias(desde.getTime(), hasta.getTime() );
            
            assertEquals(new Integer(208), cf.contarHoras(desde.getTime(), hasta.getTime()));            
        }
        
        public void testContarHorasDomingosLaborables(){
            List<Festivo> festivos = new ArrayList<Festivo>();
            Festivo festivo = produceFestivo(6, true);
            Festivo festivo1 = produceFestivo(13, true);
            festivos.add(festivo);
            festivos.add(festivo1);
            
            ControlFestivo cf = new ControlFestivo();
            cf.sistema.setEntidades(festivos);
            
            Calendar hasta = Calendar.getInstance();
            Calendar desde = Calendar.getInstance();
            hasta.setTimeInMillis(0);
            desde.setTimeInMillis(0);
            desde.set(Calendar.YEAR, 2013);
            hasta.set(Calendar.YEAR, 2013);
            desde.set(Calendar.MONTH, Calendar.JANUARY);
            desde.set(Calendar.DATE, 1);
            hasta.set(Calendar.MONTH, Calendar.JANUARY);
            hasta.set(Calendar.DATE, 31);
            
            assertEquals(new Integer(232), cf.contarHoras(desde.getTime(), hasta.getTime()));
        }
        
        private Festivo produceFestivo(int date, boolean laborable) {
		Festivo festivo = new Festivo();
		Calendar fecha = Calendar.getInstance();
		fecha.setTimeInMillis(0);
		fecha.set(Calendar.YEAR, 2013);
		fecha.set(Calendar.MONTH, Calendar.JANUARY);
		fecha.set(Calendar.DATE, date);
		festivo.setFecha(fecha.getTime());
		festivo.setLaborable(laborable);
		return festivo;
	}

}
