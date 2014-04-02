package org.quinn.accounts.service.base;

import org.quinn.accounts.model.base.User;

public interface IUserService {

	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);
}
