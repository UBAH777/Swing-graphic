package com.company;

import javafx.util.Pair;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import javax.swing.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class YourBuilder implements PlotBuilder  {
    @Override
    public void functionGenerator(File file) {

        try(FileWriter writer = new FileWriter(file))
        {
            int x = 0, y = 0;
            for (int i = 0; i < 20; i++) {
                x = (int)(Math.random() * 100);
                y = (int)(Math.random() * 100);
                writer.write( x + " " + y + "\n");
            }
            writer.flush();  // пусть будет
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }



    @Override
    public ArrayList< Pair<Integer, Integer> > functionLoader(File file) {

        ArrayList< Pair<Integer, Integer> > XY = new ArrayList<>(0);

        try {
            Scanner scan = new Scanner(file);
            int a = 0, b = 0;

            while (scan.hasNext()) {
                a = scan.nextInt();
                b = scan.nextInt();
                XY.add(new Pair<>(a,b));
            }
            scan.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return XY;
    }

    @Override
    public void plotPainter(ArrayList<Pair<Integer, Integer>> XY, JPanel panel) {
        if (panel == null) {

            XYSeries s1 = new XYSeries("graph");

            for (int j = 0; j < XY.size(); j++) {
                s1.add(XY.get(j).getKey(), XY.get(j).getValue());
            }

            XYDataset dataset = new XYSeriesCollection(s1);
            JFreeChart chart = ChartFactory.createXYLineChart("graph", "x", "y",
                    dataset, PlotOrientation.VERTICAL, true, true, false);


            JFrame frame1 = new JFrame("Graphic");

            frame1.getContentPane().add(new ChartPanel(chart));
            frame1.setSize(1000,600);
            frame1.show();
        }
    }
}

