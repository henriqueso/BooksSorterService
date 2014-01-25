package br.com.henriqueso.training.enums;

/**
 * @author henrique
 * @email henrique@henriqueso.com.br
 * @since 24/01/2014
 *
 */
public enum SortDirection {

	ASCENDENTING, DESCENDENTING;

	/**
	 * @author henrique
	 * @email henrique@henriqueso.com.br
	 * @since 24/01/2014
	 * @param string
	 */
	public static SortDirection parse(final String direction) {
		SortDirection sortDirection = null;

		if ("ASC".equalsIgnoreCase(direction)) {
			sortDirection = ASCENDENTING;

		} else if ("DESC".equalsIgnoreCase(direction)) {
			sortDirection = DESCENDENTING;
		} else {
			throw new IllegalArgumentException(direction + " is not a valid direction.");
		}

		return sortDirection;
	}
}
