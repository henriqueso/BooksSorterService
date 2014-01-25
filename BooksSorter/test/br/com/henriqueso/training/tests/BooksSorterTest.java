/**
 *
 */
package br.com.henriqueso.training.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
 * @since 23/01/2014
 *
 */
public class BooksSorterTest {

	private Book	book1;
	private Book	book2;
	private Book	book3;
	private Book	book4;
	private List<Book>	books;
	private BooksSorter	booksSorter;

	/**
	 * @author henrique
	 * @email henrique@henriqueso.com.br
	 * @since 23/01/2014
	 */
	@Before
	public void setUp() {
		this.book1 = new Book(1, "Java How to Program", "Deitel & Deitel", 2007);
		this.book2 = new Book(2, "Patterns of Enterprise Application Architecture", "Martin Fowler", 2002);
		this.book3 = new Book(3, "Head First Design Patterns", "Elisabeth Freeman", 2004);
		this.book4 = new Book(4, "Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007);

		this.books = new ArrayList<Book>();
		this.books.add(this.book1);
		this.books.add(this.book2);
		this.books.add(this.book3);
		this.books.add(this.book4);
	}

	/**
	 *
	 * @author henrique
	 * @email henrique@henriqueso.com.br
	 * @since 23/01/2014
	 */
	@Test
	public void testTitleAscending() {
		/**
		 * GIVEN the order rule is "Title ascending"
		 */
		BookComparator comparator = new BooksSorterByTitle(SortDirection.ASCENDENTING);

		this.booksSorter = new BooksSorterImpl(comparator);

		/**
		 * WHEN sorting the book list
		 */
		List<Book> actual = this.booksSorter.sort(this.books);

		/**
		 * THEN the expected order should be Books 3,4,1,2
		 */
		List<Book> expected = Arrays.asList(this.book3, this.book4, this.book1, this.book2);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testAuthorAscendingTitleDescending() {
		/**
		 * GIVEN the order rule is "Author Ascending, Title descending"
		 */
		BookComparator comparator =
				new BooksSorterByAuthor(SortDirection.ASCENDENTING,
						new BooksSorterByTitle(SortDirection.DESCENDENTING));

		this.booksSorter = new BooksSorterImpl(comparator);

		/**
		 * WHEN sorting the book list
		 */
		List<Book> actual = this.booksSorter.sort(this.books);

		/**
		 * THEN the expected order should be Books 1,4,3,2
		 */
		List<Book> expected = Arrays.asList(this.book1, this.book4, this.book3, this.book2);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testEditionYearDescendingAuthorDescendingTitleAscending() {
		/**
		 * GIVEN the order rule is "Edition Year Descending, Author Descending,Title Ascending"
		 */
		BookComparator comparator =
				new BooksSorterByEditionYear(SortDirection.DESCENDENTING,
						new BooksSorterByAuthor(SortDirection.DESCENDENTING,
								new BooksSorterByTitle(SortDirection.ASCENDENTING)));

		this.booksSorter = new BooksSorterImpl(comparator);

		/**
		 * WHEN sorting the book list
		 */
		List<Book> actual = this.booksSorter.sort(this.books);

		/**
		 * THEN the expected order should be Books 4,1,3,2
		 */
		List<Book> expected = Arrays.asList(this.book4, this.book1, this.book3, this.book2);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDefaultComparator() {
		/**
		 * GIVEN comparator is null, assuming the default comparator
		 */
		this.booksSorter = new BooksSorterImpl(null);

		/**
		 * WHEN sorting the book list
		 */
		List<Book> actual = this.booksSorter.sort(this.books);

		/**
		 * THEN the expected order should be Books 3,4,1,2
		 */
		List<Book> expected = Arrays.asList(this.book3, this.book4, this.book1, this.book2);
		Assert.assertEquals(expected, actual);
	}

	@Test(expected = OrderingException.class)
	public void testNullBookList() {
		/**
		 * GIVEN the book list is empty
		 */
		this.books = null;
		this.booksSorter = new BooksSorterImpl(null);

		/**
		 * WHEN sorting the book list
		 */
		this.booksSorter.sort(this.books);

		/**
		 * THEN an OrderingException should be thrown
		 */
		Assert.fail("OrderingException expected. Should not reach this point");
	}

	@Test
	public void testEmptyBookList() {
		/**
		 * GIVEN the book list is empty
		 */
		this.books = Collections.emptyList();
		this.booksSorter = new BooksSorterImpl(null);

		/**
		 * WHEN sorting the book list
		 */
		List<Book> actual = this.booksSorter.sort(this.books);

		/**
		 * THEN an empty list returns
		 */
		List<Book> expected = Collections.emptyList();
		Assert.assertEquals(expected, actual);
	}

}
