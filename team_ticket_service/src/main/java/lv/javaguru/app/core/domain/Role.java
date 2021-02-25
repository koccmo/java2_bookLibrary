package lv.javaguru.app.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "authorities")
public class Role {
	@Id
	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "authority", nullable = false)
	private String authority;

	public Role (String username, String authority) {
		this.username = username;
		this.authority = authority;
	}
}
