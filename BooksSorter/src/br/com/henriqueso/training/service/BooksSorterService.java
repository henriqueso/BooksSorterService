/**
 *
 */
package br.com.henriqueso.training.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import br.com.henriqueso.training.business.BooksSorterBO;
import br.com.henriqueso.training.business.BooksSorterBOImpl;
import br.com.henriqueso.training.domain.Book;


/**
 * @author henrique
 * @email henrique@henriqueso.com.br
 * @since 24/01/2014
 *
 */
@WebService(name = "BooksSorterService")
public class BooksSorterService {

	private BooksSorterBO booksSorterBO = new BooksSorterBOImpl();

	/**
	 * Sorts the book list.
	 *
	 * This method will rely on comparison order configured in the comparison configuration file
	 *
	 * @param books the books
	 * @return the list
	 */
	@WebMethod(operationName = "sort")
	@WebResult(name = "sortedList")
	public List<Book> sort(@WebParam(name = "books") final List<Book> books) {
		return this.booksSorterBO.sort(books);
	}

}