/**
 *
 */
package br.com.henriqueso.training.business;

import java.util.List;
import br.com.henriqueso.training.domain.Book;


/**
 * @author henrique
 * @email henrique@henriqueso.com.br
 * @since 24/01/2014
 *
 */
public interface BooksSorterBO {

	List<Book> sort(final List<Book> books);
}
