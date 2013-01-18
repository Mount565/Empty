package mount.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LiteDao {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	Context ctx = null;
	DataSource ds = null;

	Statement st = null;
	PreparedStatement ps = null;

	public LiteDao() {
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql");
		} catch (NamingException e) {
			logger.error("NamingException:" + e.getMessage());
			e.printStackTrace();
		}
	}

	public int doUpdate(String sql) {
		int c = 0;
		try {
			Connection con = ds.getConnection();
			Statement st = con.createStatement();
			c = st.executeUpdate(sql);
		} catch (SQLException e) {
			logger.error("Sql Exception:" + e.getMessage());
			e.printStackTrace();
		} finally {
			this.closeStatement(st);
		}
		return c;
	}

	public List<Map<String, Object>> doSelectList(String sql) {

		List<Map<String, Object>> list = null;
		try {
			Connection con = ds.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			list = SqlDaoUtils.loadDataList(rs);
		} catch (SQLException e) {
			logger.error("Sql Exception:" + e.getMessage());
			e.printStackTrace();
		} finally {
			this.closeStatement(st);
		}
		return list;
	}

	public Map<String, Object> doSelectOne(String sql) {
		Map<String, Object> map = null;
		try {
			Connection con = ds.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			map = SqlDaoUtils.loadDataMap(rs, false);
		} catch (SQLException e) {
			logger.error("Sql Exception:" + e.getMessage());
			e.printStackTrace();
		} finally {
			this.closeStatement(st);
		}
		return map;
	}

	public Map<String, Object> doSelectOne(String sql,
			Map<Integer, Object> params) {
		Map<String, Object> map = null;
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			this.setSqlParams(ps, params);
			ResultSet rs = ps.executeQuery();
			map = SqlDaoUtils.loadDataMap(rs, false);
		} catch (SQLException e) {
			logger.error("Sql Exception:" + e.getMessage());
			e.printStackTrace();
		} finally {
			this.closeStatement(st);
		}
		return map;
	}

	public List<Map<String, Object>> doSelectList(String sql,
			Map<Integer, Object> params) {
		List<Map<String, Object>> list = null;
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			this.setSqlParams(ps, params);
			ResultSet rs = ps.executeQuery();
			list = SqlDaoUtils.loadDataList(rs);
		} catch (SQLException e) {
			logger.error("Sql Exception:" + e.getMessage());
			e.printStackTrace();
		} finally {
			this.closeStatement(st);
		}
		return list;
	}

	public int doUpdate(String sql, Map<Integer, Object> params) {
		int c = 0;
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			this.setSqlParams(ps, params);
			c = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Sql Exception:" + e.getMessage());
			e.printStackTrace();
		} finally {
			this.closeStatement(st);
		}
		return c;
	}

	public int callProcedure(String sql, Map<Integer, Object> params) {
		int c = 0;
		try {
			Connection con = ds.getConnection();
			CallableStatement cs = con.prepareCall(sql);
			this.setSqlParams(cs, params);
			c = cs.executeUpdate();
		} catch (SQLException e) {
			logger.error("Sql Exception:" + e.getMessage());
			e.printStackTrace();
		} finally {
			this.closeStatement(st);
		}
		return c;
	}

	private void setSqlParams(PreparedStatement ps, Map<Integer, Object> params)
			throws SQLException {
		if (params == null || params.size() == 0) {
			return;
		}

		Iterator<Integer> it = params.keySet().iterator();

		for (int i = 0; i < params.size(); i++) {
			int paramIndex = (Integer) it.next();
			Object obj = params.get(paramIndex);
			if (obj instanceof String) {
				ps.setString(paramIndex, (String) obj);
			} else if (obj instanceof Date) {
				Timestamp ts = new Timestamp(((Date) obj).getTime());
				ps.setTimestamp(paramIndex, ts);
			} else if (obj instanceof Integer) {
				ps.setInt(paramIndex, (Integer) obj);
			} else if (obj instanceof Float) {
				ps.setFloat(paramIndex, (Float) obj);
			} else if (obj instanceof BigInteger || obj instanceof Long) {
				ps.setLong(paramIndex, Long.valueOf(obj.toString()));
			} else if (obj instanceof BigDecimal) {
				ps.setBigDecimal(paramIndex, (BigDecimal) obj);
			} else {
				ps.setObject(paramIndex, obj);
			}

		}

	}

	private void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				st = null;
				e.printStackTrace();
			}
		}
	}

}
