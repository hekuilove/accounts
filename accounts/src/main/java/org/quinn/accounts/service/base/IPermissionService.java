package org.quinn.accounts.service.base;

import java.util.List;
import java.util.Set;

import org.quinn.accounts.model.base.Permission;

public interface IPermissionService {

	Set<Permission> findByRoles(List<String> roleIds);

	Set<String> findPermissionNameByRoles(List<String> roleIds);
}
