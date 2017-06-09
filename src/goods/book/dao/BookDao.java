package goods.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import goods.book.domain.Book;
import goods.category.domain.Category;
import goods.page.Expression;
import goods.page.PageBean;
import goods.page.PageConstants;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import tools.commons.CommonUtils;
import tools.jdbc.TxQueryRunner;

public class BookDao {
	private QueryRunner qr=new TxQueryRunner();
	/**
	 * 通用查询方法
	 */
	private PageBean<Book> findByCriteria(List<Expression> exprList, int currentPage) throws SQLException{
		int pageSize = PageConstants.BOOK_PAGE_SIZE;//每页记录数
		StringBuilder whereSql = new StringBuilder(" where 1=1"); 
		List<Object> params = new ArrayList<Object>();//SQL中有问号，它是对应问号的值
		for(Expression expr : exprList) {
			whereSql.append(" and ").append(expr.getName())
				.append(" ").append(expr.getOperator()).append(" ");
			// where 1=1 and bid = ?
			if(!expr.getOperator().equals("is null")) {
				whereSql.append("?");
				params.add(expr.getValue());
			}
		}

		String sql = "select count(*) from t_book" + whereSql;
		Number number = (Number)qr.query(sql, new ScalarHandler(), params.toArray());
		int totalRecords = number.intValue();//得到了总记录数
		sql = "select * from t_book" + whereSql + " order by orderBy limit ?,?";
		params.add((currentPage-1) * pageSize);//当前页首行记录的下标
		params.add(pageSize);//一共查询几行，就是每页记录数
		
		List<Book> beanList = qr.query(sql, new BeanListHandler<Book>(Book.class), 
				params.toArray());
		PageBean<Book> pb = new PageBean<Book>();
		pb.setBeanList(beanList);
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		pb.setTotalRecords(totalRecords);
		
		return pb;
	}
	
	/**
	 * 按照目录查询
	 * @param cid
	 * @param currentPage
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Book> findByCategory(String cid, int currentPage) throws SQLException {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("cid", "=", cid));
		return findByCriteria(exprList, currentPage);
	}
	/**
	 * 按书名模糊查询
	 * @param bname
	 * @param currentPage
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Book> findByBname(String bname, int currentPage) throws SQLException {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("bname", "like", "%" + bname + "%"));
		return findByCriteria(exprList, currentPage);
	}
	
	/**
	 * 按bid查询
	 * @param bid
	 * @return
	 * @throws SQLException
	 */
	public Book findByBid(String bid) throws SQLException {
		String sql = "select * from t_book where bid=?";
		Map<String,Object> map = qr.query(sql, new MapHandler(), bid);
		Book book = CommonUtils.toBean(map, Book.class);
		Category category = CommonUtils.toBean(map, Category.class);
		book.setCategory(category);
		return book;
	}

}
