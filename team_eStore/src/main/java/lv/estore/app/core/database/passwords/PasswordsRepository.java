package lv.estore.app.core.database.passwords;

import lv.estore.app.core.domain.Password;

public interface PasswordsRepository {
    Long addPassword(Password password);
    boolean addPasswordByUserId(Long id, String password);
    boolean updatePasswordByUserId(Long id, String password);
}
