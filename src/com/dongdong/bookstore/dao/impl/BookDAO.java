package com.dongdong.bookstore.dao.impl;

import java.util.Collection;
import java.util.List;

import com.dongdong.bookstore.domain.Book;
import com.dongdong.bookstore.domain.ShoppingCartItem;
import com.dongdong.bookstore.web.CriteriaBook;
import com.dongdong.bookstore.web.Page;


public interface BookDAO {

	/**
	 * ���� id ��ȡָ�� Book ����
	 * @param id
	 * @return
	 */
	public abstract Book getBook(int id);

	/**
	 * ���ݴ���� CriteriaBook ���󷵻ض�Ӧ�� Page ����
	 * @param cb
	 * @return
	 */
	public abstract Page<Book> getPage(CriteriaBook cb);

	/**
	 * ���ݴ���� CriteriaBook ���󷵻����Ӧ�ļ�¼��
	 * @param cb
	 * @return
	 */
	public abstract long getTotalBookNumber(CriteriaBook cb);

	/**
	 * ���ݴ���� CriteriaBook �� pageSize ���ص�ǰҳ��Ӧ�� List 
	 * @param cb
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract List<Book> getPageList(CriteriaBook cb,int pageSize);

	/**
	 * ����ָ�� id �� book �� storeNumber �ֶε�ֵ
	 * @param id
	 * @return
	 */
	public abstract int getStoreNumber(Integer id);

	/**
	 * ���ݴ���� ShoppingCartItem �ļ���, 
	 * �������� books ���ݱ�� storenumber �� salesnumber �ֶε�ֵ
	 * @param items
	 */
	public abstract void batchUpdateStoreNumberAndSalesAmount(
			Collection<ShoppingCartItem> items);

}