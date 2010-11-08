package projectswop20102011.userinterface;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidViewTableNameException;
import projectswop20102011.exceptions.InvalidViewTableSizeException;

/**
 * A class to print a table to the user interface with columns.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ViewTable {

    private final ArrayList<ViewTableColumn> columns;
    //private final ArrayList<Hashtable<String,String>> elements;

    public ViewTable() {
        this.columns = new ArrayList<ViewTableColumn>();
    }

    /**
     * Add's an entry to the ViewTable
     * @param tableEntry The element to add.
     */
    public void addEntry(Hashtable<String,String> tableEntry) {
        //Set<Entry<String,String>> tableData = tableEntry.entrySet();
        //for(Entry<String,String> entry : tableData) {
        //
        //}
    }

    private class ViewTableColumn {

        private final String name;
        private int size;

        /**
         * Creates a new column for a ViewTable.
         * @param name The name of the column.
         * @throws InvalidViewTableNameException
         * @throws InvalidViewTableSizeException
         */
        public ViewTableColumn(String name) throws InvalidViewTableNameException {
            if (!isValidName(name)) {
                throw new InvalidViewTableNameException("The name of the column must be effective");
            }
            this.name = name;
            try {
                this.setSize(name.length());
            } catch (InvalidViewTableSizeException ex) {
                //We assume this can't be thrown.
                Logger.getLogger(ViewTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * Tests if the given name can be a name of a column.
         * @param name The name of the column to check.
         * @return True if the given name is effective, otherwise false.
         */
        public boolean isValidName(String name) {
            return (name != null);
        }

        /**
         * Tests if the given column size is valid.
         * @param size The given size to check.
         * @return True if the given size is larger or equal to zero, otherwise false.
         */
        public boolean isValidSize(int size) {
            return (size >= 0);
        }

        /**
         * Sets the size of the column to the given column.
         * @param size The new size.
         * @throws InvalidViewTableSizeException If the given size is invalid.
         * @post The size of this column is equal to the given column | size.equals(this.getSize())
         */
        private void setSize(int size) throws InvalidViewTableSizeException {
            if (!isValidSize(size)) {
                throw new InvalidViewTableSizeException("The size of a column must be larger or equal to zero.");
            }
            this.size = size;
        }

        /**
         * Ensures the content can hold the given item.
         * @param item The item that must be contained by the column.
         */
        public void contentMustHold(String item) {
            if (item != null) {
                try {
                    this.setSize(Math.max(item.length(), getSize()));
                } catch (InvalidViewTableSizeException ex) {
                    //We assume this can't happen
                    Logger.getLogger(ViewTable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        /**
         * Returns the name of the column.
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * Returns the size of the column.
         * @return the size of the column.
         */
        public int getSize() {
            return size;
        }
    }
}
