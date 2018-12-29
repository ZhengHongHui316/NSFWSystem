package cn.itcast.nsfw.complain.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;

import cn.itcast.core.dao.impl.BaseDaoImpl;
import cn.itcast.nsfw.complain.dao.ComplainDao;
import cn.itcast.nsfw.complain.entity.Complain;

public class ComplainDaoImpl extends BaseDaoImpl<Complain> implements ComplainDao {
	@Override
	public List<Object[]> getAnnualStatisticDataByYear(int year) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT imonth, COUNT(comp_id)")
		.append(" FROM tmonth LEFT JOIN complain ON imonth=MONTH(comp_time)")
		.append(" AND YEAR(comp_time)=?")
		.append(" GROUP BY imonth ")
		.append(" ORDER BY imonth");
		SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
		sqlQuery.setParameter(0, year);
		return sqlQuery.list();
	}
}
