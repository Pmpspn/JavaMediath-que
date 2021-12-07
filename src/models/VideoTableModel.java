package models;

import javax.swing.table.AbstractTableModel;

import classes.Client;
import classes.Livre;
import classes.Personne;
import classes.Video;
import main.Main;

import java.util.ArrayList;
import java.util.List;

public class VideoTableModel extends AbstractTableModel {

    private String[] header = { "Référence", "Désignation", "Prix", "Durée","Réalisateur","Sélection" };
    private Object[][] data;

    public VideoTableModel() {
        data = new Object[Main.videos.size()][];
        int i = 0;
        for (Video v : Main.videos) {
            data[i] = new Object[7];
            data[i][0] =v.getReference();
            data[i][1] = v.getDesignation();
            data[i][2] = v.getPrix()+"";
            data[i][3] = v.getDuree()+" minutes";
            data[i][4]=v.getRealisateur().getNom()+" "+v.getRealisateur().getPrenom();
            data[i][5]=false;

            i++;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 5;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (column == 5) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 5) {
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
