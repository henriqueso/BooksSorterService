/**
 *
 */
package br.com.henriqueso.training.sorter;

import java.util.Collections;
import java.util.List;
import br.com.henriqueso.training.domain.Book;
import br.com.henriqueso.training.enums.SortDirection;
import br.com.henriqueso.training.exceptions.OrderingException;
import br.com.henriqueso.training.sorter.comparators.BookComparator;
import br.com.henriqueso.training.sorter.comparators.BooksSorterByTitle;


/**
 * @author henrique
 * @email henrique@henriqueso.com.br
 * @since 23/01/2014
 *
 */
public class BooksSorterImpl implements BooksSorter {

	/**
	 * Default comparator is by Title ascending
	 */
	private BookComparator	comparator = new BooksSorterByTitle(SortDirection.ASCENDENTING, null);

	/**
	 * Constructor with defined sort order
	 *
	 * @param order
	 */
	public BooksSorterImpl(final BookComparator comparator) {
		if (comparator != null) {
			this.comparator = comparator;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.henriqueso.training.service.BooksSorter#sort(java.util.Set)
	 */
	@Override
	public List<Book> sort(final List<Book> books) {
		if (books == null) {
			throw new OrderingException("Book list cannot be null");
		}

		Collections.sort(books, this.comparator);

		return books;
	}

}