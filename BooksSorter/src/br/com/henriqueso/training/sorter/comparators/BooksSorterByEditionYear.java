package br.com.henriqueso.training.sorter.comparators;

import br.com.henriqueso.training.constants.ApplicationConstants;
import br.com.henriqueso.training.domain.Book;
import br.com.henriqueso.training.enums.SortDirection;


/**
 * @author henrique
 * @email henrique@henriqueso.com.br
 * @since 24/01/2014
 *
 */
public class BooksSorterByEditionYear implements BookComparator {

	private SortDirection	sortDirection;
	private BookComparator	nextComparator;

	/**
	 * Instantiates a new books sorter by edition year.
	 *
	 * @param sortDirection the sort direction
	 */
	public BooksSorterByEditionYear(final SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}

	/**
	 * Instantiates a new books sorter by edition year with next comparator in the chain
	 *
	 * @param sortDirection the sort direction
	 * @param nextComparator the next comparator
	 */
	public BooksSorterByEditionYear(final SortDirection sortDirection, final BookComparator nextComparator) {
		this.sortDirection = sortDirection;
		this.nextComparator = nextComparator;
	}

	@Override
	public void setNextComparator(final BookComparator nextComparator) {
		this.nextComparator = nextComparator;
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final Book book1, final Book book2) {
		int comparisonResult = ApplicationConstants.OBJECTS_EQUAL;

		if (SortDirection.ASCENDENTING.equals(this.sortDirection)) {
			comparisonResult = Integer.compare(book1.getEditionYear(), book2.getEditionYear());

		} else if (SortDirection.DESCENDENTING.equals(this.sortDirection)) {
			comparisonResult = Integer.compare(book2.getEditionYear(), book1.getEditionYear());

		}

		if (comparisonResult == ApplicationConstants.OBJECTS_EQUAL && this.nextComparator != null) {
			comparisonResult = this.nextComparator.compare(book1, book2);
		}


		return comparisonResult;
	}

}
