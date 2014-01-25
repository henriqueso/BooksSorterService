package br.com.henriqueso.training.sorter.comparators;

import java.util.Comparator;
import br.com.henriqueso.training.domain.Book;

public interface BookComparator extends Comparator<Book>{

	public static final String TITLE_COMPARISON = "title";
	public static final String AUTHOR_COMPARISON = "author";
	public static final String EDITION_YEAR_COMPARISON = "editionYear";

	public void setNextComparator(BookComparator nextComparator);
}
