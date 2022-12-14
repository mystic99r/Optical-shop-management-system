import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

class ResultModel extends AbstractTableModel
{
	public void setResultSet(ResultSet result)
	{
		try
		{
			ResultSetMetaData mData;
			mData=result.getMetaData();
			int cols=mData.getColumnCount();
			columns=new String[cols];
			for(int i=0;i<cols;i++)
			{
				columns[i]=mData.getColumnLabel(i+1);
			}
			RowData.clear();
			String[] rowData;
			while(result.next())
			{
				rowData=new String[cols];
				for(int i=0;i<cols;i++)
				{
					rowData[i]=result.getObject(i+1)+"";
					
				}
				RowData.addElement(rowData);
			}
			fireTableChanged(null);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public int getColumnCount()
	{
		return columns.length;
	}
	public int getRowCount()
	{
		return RowData==null?0:RowData.size();
	}
	public String getValueAt(int row,int col)
	{
		return RowData.elementAt(row)[col];
	}
	public String getColumnName(int col)
	{
		return columns[col]==null?"No Name":columns[col];
	}
	private String[] columns=new String[0];
	private Vector<String[]>RowData=new Vector<String[]>();
}


