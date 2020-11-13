package com.company;

import java.util.Scanner;
import java.io.File;


import javafx.util.Pair;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        PlotBuilder builder = new YourBuilder();
        Scanner scan = new Scanner(System.in);

        File file1 = new File("C://Users//Boss//Desktop//Java_projects//GoodFiles//data.txt");
        builder.functionGenerator(file1);                           // передать файловую переменную

        ArrayList<Pair<Integer, Integer>> XY = builder.functionLoader(file1);

        builder.plotPainter(XY, null);
    }
}
