package lv.javaguru.java2.library.core.requests;

public class SearchBooksRequest {

	private String title;
	private String author;

	private String orderBy;
	private String orderDirection;

	public SearchBooksRequest(String title, String author) {
		this.title = title;
		this.author = author;
	}

	public SearchBooksRequest(String title,
							  String author,
							  String orderBy,
							  String orderDirection) {
		this.title = title;
		this.author = author;
		this.orderBy = orderBy;
		this.orderDirection = orderDirection;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public boolean isTitleProvided() {
		return this.title != null && !this.title.isEmpty();
	}

	public boolean isAuthorProvided() {
		return this.author != null && !this.author.isEmpty();
	}

	public String getOrderBy() {
		return orderBy;
	}

	public String getOrderDirection() {
		return orderDirection;
	}
}
