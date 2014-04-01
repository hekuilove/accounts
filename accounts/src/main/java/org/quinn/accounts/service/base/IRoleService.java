package org.quinn.accounts.service.base;

import java.util.List;

import org.quinn.accounts.model.Role;

public interface IRoleService {

	List<Role> findByUsername(String username);
}
