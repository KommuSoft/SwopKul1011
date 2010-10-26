package projectswop20102011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import projectswop20102011.controllers.MainController;

/**
 * @author Willem Van Onsem, Pieter-Jan Vuylsteke and Jonas Vanthornhout
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        World world = new World();
        MainController mainController = new MainController(world);
        //print the project header
        System.out.println("Project SWOP v. 1.618");
        String curline;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);
        while (true) {
            System.out.print("> ");
            curline = in.readLine();
            if (curline.toLowerCase().equals("quit")) {
                break;
            }
            try {
                mainController.readInput(curline);
            }
            catch(Throwable t) {
                System.out.println(String.format("ERROR: %s",t));
            }
        }
    }
}