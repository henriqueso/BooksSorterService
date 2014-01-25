package br.com.henriqueso.training.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * @author henrique
 * @email henrique@henriqueso.com.br
 * @since 23/01/2014
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

	private int		code;
	private String	title;
	private String	author;
	private int		editionYear;

	public Book() {
		super();
	}

	public Book(final int code, final String title, final String author, final int editionYear) {
		this.code = code;
		this.title = title;
		this.author = author;
		this.editionYear = editionYear;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [code=" + this.code + ", title=" + this.title + ", author=" + this.author + ", editionYear=" + this.editionYear + "]";
	}

	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		return this.author;
	}

	public int getEditionYear() {
		return this.editionYear;
	}

	public int getCode() {
		return this.code;
	}

}
