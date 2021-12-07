package models;

import classes.Auteur;
import classes.Client;
import classes.Personne;
import main.Main;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AuteurTableModel extends AbstractTableModel {

	private String[] header = { "Nom", "Prénom", "Sélection" };
	private Object[][] data;

	public AuteurTableModel() {

		data = new Object[Main.auteurs.size()][];
		int i = 0;
		for (Auteur a : Main.auteurs) {

				data[i] = new Object[3];
				data[i][0] = a.getNom();
				data[i][1] = a.getPrenom();
				data[i][2] = false;
				i++;

		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return col == 2;
	}

	@Override
	public Class<?> getColumnClass(int column) {
		if (column == 2) {
			return Boolean.class;
		} else {
			return String.class;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 2) {
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
