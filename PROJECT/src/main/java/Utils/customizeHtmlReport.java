package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class customizeHtmlReport {

	public static void updateResult(int indexSI, String testCaseName, String testCaseStatus,String scriptName)throws IOException {
		
		String startDateTime = new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss").format(new GregorianCalendar().getTime());
		String Date = new SimpleDateFormat("MMM-dd-yyyy_HH").format(new GregorianCalendar().getTime());
		String resultFile = System.getProperty("user.dir")+"//HTMLReports//";
		File file = new File(resultFile + Date + ".html");
		
		System.out.println(file.exists());
		
		if(!file.exists()) {
		 FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		 BufferedWriter bw = new BufferedWriter(fw);
		 bw.write("<html>" + "\n");
		 bw.write("<head><title>" + "Module: Risk HTML Report" + "</title>" + "\n");
		 bw.write("</head>" + "\n");
		 bw.write("<body>");
		 bw.write("<font face='Times New Roman'size-'2'>"+"\n");
		 bw.write("<u><h1 align='center'>" + "Module: Risk HTML Report" + "</h1></u>" + "\n");
		 bw.flush();
		 bw.close();
		}
		
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(file, true));
		
		if (indexSI ==1) {
			bw1.write("<table align='center' border='0' width='70%' height='10'>");
			bw1.write("<tr><td width='70%' </td</tr>");
			bw1.write("<table align='center' border='1' width='70%' height='47'>");
			bw1.write("<tr>");
			bw1.write("<td colspan='1' align='center'><b><font color='#000000' face='Tahoma' size='3'>ScriptName :&nbsp;&nbsp;&nbsp;"
					+ "</font><font color='#0000FF'' face='Tahoma' size='2'> Risk Module Test Automation </font></b></td>");
			bw1.write("<td colspan='3' align='center'><b><font color='#000000' face='Tahoma' size='3'>Start Time :"
					+ "&nbsp;</font></b><font color='#0000FF'' face='Tahoma' size='2'>" + startDateTime + " </font></b></td>");
			bw1.write("</tr>");
			bw1.write("</tr>");
			bw1.write("<td bgcolor='#0099ff' align='center'><b><font color='#000000' face='Arial' size='2'>S. No: </font></b></td>"); 
			bw1.write("<td bgcolor='#0099ff' align='center'><b><font color='#000000' face='Arial' size='2'>Test case ID : Test case Description </font></b></td>");
			bw1.write("<td bgcolor='#0099ff' align='center'><b><font color='#000000' face='Arial' size='2'>Test No.</font></b></td>"); 
			bw1.write("<td bgcolor='#0099ff' align='center'><b><font color='#000000' face='Arial' size='2'>Result </font></b></td>");
			bw1.write("</tr>");
		}
			bw1.write("<tr>" +"\n");
			bw1.write("<td bgcolor='#000000'align='Center'><font color='#ffffff' face='Arial' size='2'>" + indexSI + "</font></td>" + "\n");
			bw1.write("<td bgcolor='#000000' valign='middle' align='center'><b><font color='#ffffff' face='Arial' size='2'>" + testCaseName + "</font></b></td>" + "\n");
			bw1.write("<td bgcolor='#000000' valign='middle' align='center'><b><font color='#ffffff' face='Arial' size='2'>" + scriptName + "</font></b></td>" + "\n");
		 if (testCaseStatus=="Pass") {
			 bw1.write("<td bgcolor='#008000' valign='middle' align='center'><b><font color='White' face='Arial' size='2'>" + testCaseStatus + "</font></b></td>" +"\n");
			 }
		 else { 
			 bw1.write("<td bgcolor='#008000' valign='middle' align='center'><b><font color='red' face='Arial' size'2'>" + testCaseStatus + "</font></b></td>" +"\n");
		 }
			 bw1.write("</tr>" + "\n");	
			 bw1.write("</body>" + "\n");
			 bw1.write("</html>");
			 bw1.flush();
			 bw1.close();
		}
		

}