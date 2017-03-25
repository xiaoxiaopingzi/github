package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.PageBean;

import com.opensymphony.xwork2.ActionContext;

/**
 * 辅助拼接hql语句的工具类
 * @author Administrator
 */
public class HQLHelper {

	private String fromClause; // from字句
	private String whereClause = ""; // whereClause字句
	private String orderByClause = ""; // order by字句
	private List<Object> parameters = new ArrayList<Object>();// 参数列表

	/**
	 * 生成form子句
	 * @param clazz
	 * @param alias
	 *            别名
	 */
	public HQLHelper(Class clazz, String alias) {
		fromClause = "from " + clazz.getSimpleName() + " " + alias;
	}

	
	/**
	 * 拼接where子句
	 * @param args
	 * @param condition
	 */
	public HQLHelper addWhereCondition(String condition, Object... args) {
		if (whereClause.length() == 0) { //第一次拼接whereClause为空字符串
			whereClause = " where " + condition;
		} else {
			whereClause += " and " + condition;
		}
		//处理参数
		if (args != null && args.length > 0) {
			for (Object arg : args) {
				parameters.add(arg);
			}
		}
		return this;
	}
	
	public HQLHelper addWhereCondition(boolean append ,String condition, Object... args) {
		if (append) {
			if (whereClause.length() == 0) {
				whereClause = " where " + condition;
			} else {
				whereClause += " and " + condition;
			}
			if (args != null && args.length > 0) {
				for (Object arg : args) {
					parameters.add(arg);
				}
			}
		}
		
		return this;
	}

	
	/**
	 * 拼接order by子句
	 * @param propertyName
	 * @param asc
	 *            true表示升序，false表示降序
	 */
	public HQLHelper addOrderByProperty(String propertyName, boolean asc) {
		if (orderByClause.length() == 0) {  //第一次拼接orderByClause为空字符串
			orderByClause = " order by " + propertyName
					+ (asc ? " asc" : " desc");
		} else {
			orderByClause += ", " + propertyName + (asc ? " asc" : " desc");
		}
		return this;
	}
	public HQLHelper addOrderByProperty(boolean append ,String propertyName, boolean asc) {
		if (append) {
			if (orderByClause.length() == 0) {
				orderByClause = " order by " + propertyName
						+ (asc ? " asc" : " desc");
			} else {
				orderByClause += ", " + propertyName + (asc ? " asc" : " desc");
			}
		}
		return this;
	}

	
	/**
	 * 获取查询记录列表的的hql语句
	 * @return
	 */
	public String getQueryListHql() {
		return fromClause + whereClause + orderByClause;
	}
	
	/**
	 * 获取查询总记录的的hql语句(没有order by语句)
	 * @return
	 */
	public String getCountHql() {
		return "select count(*) " + fromClause + whereClause;
	}

	/**
	 * 获取参数列表
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}
	
	/**
	 * 准备PageBean对象到struts2的栈顶
	 * @param service    
	 * @param pageNumber 
	 */
	public void preparePageBean(DaoSupport<?> service , int pageNumber){
		PageBean pageBean = service.getPageBean(pageNumber, this);// pageNumber在BaseAction中定义
		ActionContext.getContext().getValueStack().push(pageBean); //放到栈顶，方便拿pageBean中的属性
	}
}
