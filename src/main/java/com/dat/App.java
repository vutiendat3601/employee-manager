package com.dat;

import java.io.Console;
import java.util.Scanner;

import com.dat.console.HomeConsole;

public class App {

    public static Scanner systemScanner = new Scanner(System.in);
    public static Console systemConsole = System.console();
    public static void main(String[] args) {
        HomeConsole.showMainMenu();
    }
}
