package mount.dao;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SqlDaoUtils {

	public static List<Map<String, Object>> loadDataList(java.sql.ResultSet rs)
			throws SQLException {

		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();

		Map<String, Object> data = null;

		while (rs.next()) {
			data = loadDataMap(rs, true);
			list.add(data);
		}

		return list;
	}

	public static Map<String, Object> loadDataMap(java.sql.ResultSet rs,
			boolean cursorMoved) throws SQLException {

		Map<String, Object> data = new HashMap<String, Object>();
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		boolean hasNext = false;
		if (!cursorMoved) {
			hasNext = rs.next();
		} else {
			hasNext = true;
		}
		
		if (hasNext) {
			for (int i = 1; i <= columnCount; i++) {
				String colLable = metaData.getColumnLabel(i);
				int type = metaData.getColumnType(i);
				int scale = metaData.getScale(i);
				if (type == Types.DECIMAL || type == Types.DOUBLE
						|| type == Types.NUMERIC || type == Types.FLOAT
						|| type == Types.REAL) {
					if (scale > 0) {
						double tempDbl = rs.getDouble(i);
						if (rs.wasNull()) {
							data.put(colLable, null);
						} else {
							data.put(colLable, new Double(tempDbl));
						}
					} else {
						long tempDbl = rs.getLong(i);
						if (rs.wasNull()) {
							data.put(colLable, null);
						} else {
							data.put(colLable, new Long(tempDbl));
						}
					}
				} else if (type == Types.INTEGER) {
					int tempInt = rs.getInt(i);
					if (rs.wasNull()) {
						data.put(colLable, null);
					} else {
						data.put(colLable, new Integer(tempInt));
					}
				} else if (type == Types.VARCHAR || type == Types.CHAR) {
					String tempString = rs.getString(i);
					data.put(colLable, tempString);
				} else if (type == Types.TIMESTAMP || type == Types.TIME
						|| type == Types.DATE) {
					Timestamp tmpTime = rs.getTimestamp(i);
					if (tmpTime != null)
						data.put(colLable, new Date(tmpTime.getTime()));
				} else if (type == Types.CLOB) {

					data.put(colLable, rs.getString(i));
				} else {

					data.put(colLable, rs.getObject(i));
				}
			}
		}
		return data;

	}

}
