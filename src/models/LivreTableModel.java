package models;

import javax.swing.table.AbstractTableModel;

import classes.Client;
import classes.Livre;
import classes.Personne;
import main.Main;

import java.util.ArrayList;
import java.util.List;

public class LivreTableModel extends AbstractTableModel {

    private String[] header = { "Référence", "Désignation", "Prix", "ISBN","Nombre de pages","Auteur","Sélection" };
    private Object[][] data;

    public LivreTableModel() {
        data = new Object[Main.livres.size()][];
        int i = 0;
        for (Livre l : Main.livres) {
            data[i] = new Object[7];
            data[i][0] = l.getReference();
            data[i][1] = l.getDesignation();
            data[i][2] = l.getPrix()+"";
            data[i][3] = l.getIsbn();
            data[i][4]=l.getNbPages()+"";
            data[i][5]=l.getAuteur().getPrenom()+" "+l.getAuteur().getNom();
            data[i][6]=false;

            i++;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 6;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (column == 6) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 6) {
            if (aValue instanceof Boolean) {
                data[rowIndex][columnIndex] = aValue;
                fireTableCellUpdated(rowIndex, columnIndex);
            }
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
}
