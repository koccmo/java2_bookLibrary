package lv.javaguru.app.core.request;


public class UserEditRequest {
	private Long id;

	public UserEditRequest () {
	}

	public UserEditRequest (Long id) {
		this.id = id;
	}

	public Long getId () {
		return id;
	}


	public class Name {
		private final String name;

		public Name (String name) {
			this.name = name;
		}

		public String getName () {
			return name;
		}

		public Long getId () {
			return id;
		}
	}

	public class Surname {
		private final String surname;

		public Surname (String surname) {
			this.surname = surname;
		}

		public String getSurname () {
			return surname;
		}

		public Long getId () {
			return id;
		}
	}
}
