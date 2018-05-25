/*
* WebTemplate 1.0
* Luca Vercelli 2017
* Released under MIT license 
*/
package com.example.myapp.main.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import com.example.myapp.main.entity.Menu;
import com.example.myapp.main.entity.Role;
import com.example.myapp.main.entity.User;

/**
 * This bean contains all data that need to be stored in session (user,
 * authorizations, ...)
 * 
 * @author Luca Vercelli
 *
 */
@Named("sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

	private static final long serialVersionUID = -2545698895043737577L;

	private User user;
	private Set<Role> roles = new HashSet<Role>();
	private List<Menu> menus = new ArrayList<Menu>();
	private String language;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "SessionBean [user=" + user + ", roles=" + roles + ", menus=" + menus + ", language=" + language + "]";
	}
}
