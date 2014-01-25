/**
 * 
 */
package br.com.henriqueso.training.sorter;

import java.util.List;

import br.com.henriqueso.training.domain.Book;


/**
 * @author henrique
 * @email henrique@henriqueso.com.br
 * @since 23/01/2014
 *
 */
public interface BooksSorter {

	/**
	 * @author henrique
	 * @email henrique@henriqueso.com.br
	 * @since 23/01/2014
	 * @param books
	 * @return
	 */
	List<Book> sort(List<Book> books);

}
