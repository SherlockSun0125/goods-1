package goods.book.service;

import java.sql.SQLException;

import goods.book.dao.BookDao;
import goods.book.domain.Book;
import goods.page.PageBean;

public class BookService {
	private BookDao bookDao=new BookDao();
	
	public PageBean<Book> findByCategory(String cid, int currentPage) {
		try {
			return bookDao.findByCategory(cid, currentPage);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public PageBean<Book> findByBname(String bname, int currentPage) {
		try {
			return bookDao.findByBname(bname, currentPage);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//按id查询加载图书
	public Book loadBook(String bid){
		try {
			return bookDao.findByBid(bid);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	

}
