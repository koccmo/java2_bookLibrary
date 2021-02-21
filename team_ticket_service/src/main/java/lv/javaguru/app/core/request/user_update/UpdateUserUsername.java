package lv.javaguru.app.core.request.user_update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserUsername {

	private Long id;

	private String userNewUsername;

}
