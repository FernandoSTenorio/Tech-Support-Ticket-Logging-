
package om;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset; 
import java.sql.*;
import SystemTech.*;

import javax.swing.JPanel;
 

public class BarChart extends JPanel{
	
	String st = Login.controll.checkTech2();
	Integer rs = Integer.valueOf(st);
	
	String sc = Login.controll.checkTech();
	Integer in = Integer.valueOf(sc);
	
	String sg = Login.controll.checkTech1();
	Integer it = Integer.valueOf(sg);
	
	
	
	public void run(){
		new BarChart().setVisible(true);
	}
	
	public BarChart(){
		try{
			this.setVisible(true);
			this.setSize(620, 400);
			this.validate();

			DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
			dataSet.setValue(it, "Profit", "John");
			dataSet.setValue(in, "Profit", "James");
			dataSet.setValue(rs, "Profit", "Johan");


			JFreeChart chart = ChartFactory.createBarChart("Ticketing", "Sales", "Profit", dataSet, PlotOrientation.VERTICAL,false,true,false);
			ChartPanel cp = new ChartPanel(chart);
			 cp.setMouseWheelEnabled(true);
			 cp.setDomainZoomable(true);
			this.add(cp);
		}catch(Exception e){
			
		}

	}

}


