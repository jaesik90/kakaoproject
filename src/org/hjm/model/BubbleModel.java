package org.hjm.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.hjm.entity.Chat;
/**
 * <code>BubbleModel</code> 
 * 
 * @author Jimmy
 * @since v1.0.0 (Oct 15, 2013)
 */
public class BubbleModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private List<Chat> mLstData;
	
	public BubbleModel() {
		mLstData = new ArrayList<Chat>();
	}
	
	public void addRow(Chat imMsg) {
		mLstData.add(imMsg);
		int iLen = mLstData.size();
		this.fireTableRowsInserted(iLen - 1, iLen - 1);
	}
	
	@Override
	public int getRowCount() {
		return mLstData.size();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return mLstData.get(rowIndex);
	}
	
	
}
