/*
 * Created on Feb 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Conny
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

// This test case is used to test the DOM API and unicode support feature

import org.jdesktop.jdic.browser.WebBrowser;
import org.jdesktop.jdic.browser.WebBrowserEvent;
import org.jdesktop.jdic.browser.WebBrowserListener; 

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout; 

import java.net.URL;
import java.net.MalformedURLException;

import java.awt.AWTException;
import java.awt.Robot;

public class BrowserApis 
{
	public BrowserApis()
	{		
	}
	
	private static void DOMApis(WebBrowser wb)
	{
		String htmlContent = 
			"<html>" + 
			"<head>" + 
			"<title>" + "JDIC Browser API Test Case" + "</title>" +
			"<script>" +
			"function browserPrint()" +
			"{" +
				"if (confirm (\"Print this page?\"))" +
				"{" +
					"window.print();" +
					"return \"The current page is printed!\";" +
				"}" +
				"return \"Nothing was printed!\";" +
			"}" +
			"function getSel()" +
			"{" +	
				"var txt ='';" +
				"txt = window.getSelection();" +
				"alert(txt + \" was selected\");" +
			"}" +
			"</script>" +
			"</head>" +
			"<body>" +
			"<input type = \"button\" name =\"print\" onClick = \"browserPrint()\" value = \"Print\" >" + 
			"<p>" +
			"<input type = \"button\" name =\"print\" onClick = \"browserPrint()\" value = \"��ӡ\" >" +
			"<p>" +
			"This page is generated by the setContent() API;" + 
			"<p>" +
			"<input type = \"button\" name = \"selected\" onclick = \"getSel()\" value = \"Show Selection\" >" +
			"</body>" +
			"</html>";
			
		wb.setContent(htmlContent);
		String s = wb.getContent();
		System.out.println(s);
		
		String result = wb.executeScript("alert(\"executeScript() test\"); browserPrint();");
		System.out.println(result);
	}
	
	public static void main(String[] args)
	{
		JFrame f = new JFrame("JDIC Browser API Testing");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p = new JPanel();
		p.setSize(700,500);
		p.setLayout(new BorderLayout());
		
		final WebBrowser wb = new WebBrowser();
		
		wb.addWebBrowserListener(
				new WebBrowserListener(){
					
					boolean once = true;

					public void downloadStarted(WebBrowserEvent arg0) {;
						// TODO Auto-generated method stub
						
					}

					public void downloadCompleted(WebBrowserEvent arg0) {;
						// TODO Auto-generated method stub
						
					}

					public void downloadProgress(WebBrowserEvent arg0) {;
						// TODO Auto-generated method stub
						
					}

					public void downloadError(WebBrowserEvent arg0) {;
						// TODO Auto-generated method stub
						
					}

					public void documentCompleted(WebBrowserEvent arg0) {;
						// TODO Auto-generated method stub
					
						while (once)
						{											
							wb.executeScript("alert(\"Download Completed!\");");
							DOMApis(wb);
							
							once = false;
						}						
					}

					public void titleChange(WebBrowserEvent arg0) {;
						// TODO Auto-generated method stub
						
					}

					public void statusTextChange(WebBrowserEvent arg0) {;
						// TODO Auto-generated method stub
						
					}
		});
		
		try
		{
			wb.setURL(new URL("http://www.yahoo.com"));
			
		}catch (MalformedURLException mue)
		{
			mue.printStackTrace();
		}
		
		p.add(wb, BorderLayout.CENTER);
		
		f.getContentPane().add(p, BorderLayout.CENTER);
		f.setSize(700, 500);
		f.setVisible(true);
		
		try
		{
			Robot robot = new Robot();
		}catch (AWTException awte)
		{
			awte.printStackTrace();
			System.out.println("The test was not completed.");
		}
		
	}
}