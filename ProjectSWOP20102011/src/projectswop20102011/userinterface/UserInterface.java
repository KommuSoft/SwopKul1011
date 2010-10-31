package projectswop20102011.userinterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An abstract class, that represents all textual user interfaces used in this project.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class UserInterface {

    private int indentation;
    private InputStreamReader reader;
    private OutputStreamWriter writer;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String indentationString;

    protected UserInterface() {
        setIndentation(0);
        this.setReaderStream(System.in);
        this.setWriterStream(System.out);
    }

    public abstract void HandleUserInterface();

    /**
     * @return the indentation
     */
    public int getIndentation() {
        return indentation;
    }

    /**
     * @param indentation the indentation to set
     */
    public void setIndentation(int indentation) {
        this.indentation = indentation;
        String indentationS = "";
        for (int i = 0; i < indentation; i++) {
            indentationS += "\t|";
        }
        this.indentationString = indentationS;
    }

    public String readInput() {
        try {
            this.getBufferedWriter().write(this.indentationString + "> ");
            this.getBufferedWriter().flush();
            return this.getBufferedReader().readLine();
        } catch (IOException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public void writeOutput(String message) {
        try {
            this.getBufferedWriter().write(this.indentationString + message + "\n");
            this.getBufferedWriter().flush();
        } catch (IOException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setWriterStream(OutputStream stream) {
        this.writer = new OutputStreamWriter(stream);
        this.bufferedWriter = new BufferedWriter(this.writer);
    }

    public void setReaderStream(InputStream stream) {
        this.reader = new InputStreamReader(stream);
        this.bufferedReader = new BufferedReader(this.reader);
    }

    /**
     * @return the bufferedReader
     */
    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    /**
     * @return the bufferedWriter
     */
    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }
}