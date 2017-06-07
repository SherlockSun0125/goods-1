package goods.cart.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import goods.book.domain.Book;
import goods.cart.domain.CartItem;
import goods.cart.service.CartItemService;
import goods.user.domain.User;

public class CartItemServlet extends BaseServlet{
	private CartItemService cartItemService=new CartItemService();
	
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cartItemIds=req.getParameter("cartItemIds");
		cartItemService.batchDelete(cartItemIds);
		return myCart(req,resp);
	}
	
	public String add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map map=req.getParameterMap();
		CartItem cartItem=CommonUtils.toBean(map,CartItem.class);
		Book book=CommonUtils.toBean(map,Book.class);
		User user=(User) req.getSession().getAttribute("sessionUser");
		cartItem.setBook(book);
		cartItem.setUser(user);
		
		cartItemService.add(cartItem);
		return myCart(req,resp);
	}
	
	public String myCart(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User)req.getSession().getAttribute("sessionUser");
		String uid = user.getUid();
		List<CartItem> cartItemLIst = cartItemService.myCart(uid);
		req.setAttribute("cartItemList", cartItemLIst);
		return "f:/jsps/cart/list.jsp";
	}
	
	
}
