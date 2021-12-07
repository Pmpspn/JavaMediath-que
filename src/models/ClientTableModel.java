package models;

import javax.swing.table.AbstractTableModel;

import classes.Client;
import classes.Personne;
import main.Main;

import java.util.ArrayList;
import java.util.List;

public class ClientTableModel extends AbstractTableModel {

	private String[] header = { "Nom", "Prénom", "Mail", "Sélection" };
	private Object[][] data;

	public ClientTableModel() {
		data = new Object[Main.clients.size()][];
		int i = 0;
		for (Client c : Main.clients) {
			data[i] = new Object[4];
			data[i][0] = c.getNom();
			data[i][1] = c.getPrenom();
			data[i][2] = ((Client) c).getMail();
			data[i][3] = false;
			i++;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return col == 3;
	}

	@Override
	public Class<?> getColumnClass(int column) {
		if (column == 3) {
			return Boolean.class;
		} else {
			return String.class;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 3) {
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
