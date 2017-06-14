/*
* WebTemplate 1.0
* Luca Vercelli 2017
* Released under GPLv3 
*/
package com.example.myapp.login.actions;

import javax.inject.Inject;
import javax.security.auth.login.LoginContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.myapp.login.db.User;
import com.example.myapp.login.helpers.UsersHelper;
import com.example.myapp.main.util.SessionBean;
import com.sun.messaging.jmq.io.Status;

/**
 * REST authentication endpoint. This is not EE security, you may @see also
 * RolesAllowedDynamicFeature.
 * 
 * <b>It doesn't matter which type of authentication you decide to use. Always
 * do it on the top of a HTTPS connection to prevent the man-in-the-middle
 * attack.</b>
 * 
 * @author C�ssio Mazzochi Molin, Luca Vercelli
 * @see https://stackoverflow.com/questions/26777083
 *
 */
@Path("authentication")
public class AuthenticationEndpoint {

	@Inject
	SessionBean sessionBean;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("userId") String userId, @FormParam("pwd") String pwd) {

		try {
			sessionBean.setLoginContext(null);
			sessionBean.setUser(null);

			User user = null;

			if (userId == null || userId.equals("")) {
				// This can also happen when user go to "Login" address for the
				// first time

				// addActionError(getText("login.missing.parameters"));
				return Response.ok(Status.BAD_REQUEST).build();
			}

			LoginContext lc = UsersHelper.getInstance().authenticate(userId, pwd);

			sessionBean.setLoginContext(lc);
			if (lc != null && !lc.getSubject().getPrincipals().isEmpty()) {
				user = (User) lc.getSubject().getPrincipals().iterator().next();
				sessionBean.setUser(user);
			}

			if (user == null) {
				return Response.ok(Status.UNAUTHORIZED).build();

			}

			// At last, user is authenticated
			return Response.ok().build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}
}