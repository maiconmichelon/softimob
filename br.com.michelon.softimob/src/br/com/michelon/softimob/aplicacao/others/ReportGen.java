package br.com.michelon.softimob.aplicacao.others;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.internal.adaptor.ContextFinder;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class ReportGen {

	public static void main(String[] args) {

	}

	public static JasperPrint generateReport() {
		Connection conn = null;
		JasperPrint myJPrint = null;

		// Taking backup of the default classloader
		ClassLoader systemClassLoader = Thread.currentThread().getContextClassLoader();

		try {
			// Loading my custom classloader
			Thread.currentThread().setContextClassLoader(new ContextFinder(Thread.currentThread().getContextClassLoader()));

			// Connecting to the database
			conn = DBConnector.getConnection();

			// Loading my jasper file
			JasperReport jasperReport = null;
			jasperReport = (JasperReport) net.sf.jasperreports.engine.util.JRLoader.loadObject(ReportGen.class.getClassLoader().getResourceAsStream("reports/Balancete.jasper"));

			// Passing parameters to the report
			Map parameters = new HashMap();
			parameters.put("dataInicial", new Date());
			parameters.put("dataFinal", new Date());

			// Filling the report with data from
			// the database based on the parameters passed.
			myJPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

			// Closing the database connection
			conn.close();

		} catch (JRException jrExp) {
			jrExp.printStackTrace();
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {

			// Replacing my custom classloader with the original classloader.
			Thread.currentThread().setContextClassLoader(systemClassLoader);
		}
		return myJPrint;
	}
}