/**
 *
 */
package br.com.henriqueso.training.business;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import br.com.henriqueso.training.constants.ApplicationConstants;
import br.com.henriqueso.training.domain.Book;
import br.com.henriqueso.training.enums.SortDirection;
import br.com.henriqueso.training.exceptions.OrderingException;
import br.com.henriqueso.training.sorter.BooksSorter;
import br.com.henriqueso.training.sorter.BooksSorterImpl;
import br.com.henriqueso.training.sorter.comparators.BookComparator;
import br.com.henriqueso.training.sorter.comparators.BooksSorterByAuthor;
import br.com.henriqueso.training.sorter.comparators.BooksSorterByEditionYear;
import br.com.henriqueso.training.sorter.comparators.BooksSorterByTitle;


/**
 * @author henrique
 * @email henrique@henriqueso.com.br
 * @since 24/01/2014
 *
 */
public class BooksSorterBOImpl implements BooksSorterBO {

	private static final Logger LOGGER = Logger.getLogger(BooksSorterBOImpl.class);

	private BooksSorter	booksSorter;

	/* (non-Javadoc)
	 * @see br.com.henriqueso.training.business.BooksSorterBO#sort(java.util.List)
	 */
	@Override
	public List<Book> sort(final List<Book> books) {
		try {

			String comparisonOrder = getComparisonOrder();

			BookComparator comparator = getBookComparatorByComparisonConfiguration(comparisonOrder.split(","), 0);

			this.booksSorter = new BooksSorterImpl(comparator);

			return this.booksSorter.sort(books);

		} catch (IOException exception) {
			String message = "Error reading comparison configuration";
			LOGGER.error(message, exception);
			throw new OrderingException(message, exception);
		}
	}

	/**
	 * Builds the chain of Book comparators according with the order of comparisons.
	 *
	 * Ex: {"title[asc]", "author[desc]"}
	 *
	 * @param comparisons the array of comparisons
	 * @param index the index of comparisons array
	 * @return the book comparator chain
	 * @author henrique
	 * @email henrique@henriqueso.com.br
	 * @since 24/01/2014
	 */
	private BookComparator getBookComparatorByComparisonConfiguration(final String[] comparisons, int index) {
		BookComparator comparator = null;

		comparator = getBookComparatorByComparison(comparisons[index]);

		index +=1;

		if (index < comparisons.length) {

			// Recursively gets the next comparator
			comparator.setNextComparator(getBookComparatorByComparisonConfiguration(comparisons, index));

		}

		return comparator;
	}

	/**
	 * Gets the Book comparator based on comparison parameter.
	 *
	 * Ex. "author[asc]"
	 *
	 * @author henrique
	 * @email henrique@henriqueso.com.br
	 * @since 24/01/2014
	 * @param comparison
	 * @return
	 */
	private BookComparator getBookComparatorByComparison(final String comparison) {
		BookComparator nextComparator = null;

		Pattern pattern = Pattern.compile(ApplicationConstants.REGEX_COMPARISON_ORDER);

		Matcher matcher = pattern.matcher(comparison);
		if (matcher.matches() ){

			String attributeName = matcher.group(1);
			String direction = matcher.group(2);

			SortDirection sortDirection = SortDirection.parse(direction);

			nextComparator = getComparatorByAttributeNameAndSortDirection(attributeName, sortDirection);

		}

		return nextComparator;
	}

	/**
	 * Gets the Book Comparator based on attribute name and sort direction
	 *
	 * Ex: title, ASCENDING
	 *
	 * @author henrique
	 * @email henrique@henriqueso.com.br
	 * @since 24/01/2014
	 * @param attribute
	 * @param sortDirection
	 * @return
	 */
	private BookComparator getComparatorByAttributeNameAndSortDirection(final String attribute, final SortDirection sortDirection) {
		BookComparator comparator = null;

		if (BookComparator.TITLE_COMPARISON.equalsIgnoreCase(attribute)) {
			comparator = new BooksSorterByTitle(sortDirection);
		} else if (BookComparator.AUTHOR_COMPARISON.equalsIgnoreCase(attribute)) {
			comparator = new BooksSorterByAuthor(sortDirection);
		} else if (BookComparator.EDITION_YEAR_COMPARISON.equalsIgnoreCase(attribute)) {
			comparator = new BooksSorterByEditionYear(sortDirection);
		} else {
			throw new IllegalArgumentException(attribute + " is not a valid book attribute");
		}

		return comparator;
	}

	/**
	 * Reads the comparison order from the comparison configuration file
	 *
	 * @author henrique
	 * @return
	 * @throws IOException
	 * @email henrique@henriqueso.com.br
	 * @since 24/01/2014
	 */
	private String getComparisonOrder() throws IOException {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(ApplicationConstants.COMPARISON_CONFIGURATION_FILE);
		Properties properties = new Properties();
		properties.load(inputStream);

		return properties.getProperty(ApplicationConstants.COMPARISON_ORDER, "");
	}

}
