package com.example.myapp.login.actions;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;

import com.example.myapp.crud.GenericManager;
import com.example.myapp.login.helpers.UsersManager;
import com.example.myapp.main.entity.User;
import com.example.myapp.main.enums.BooleanYN;
import com.example.myapp.main.util.ApplicationProperties;
import com.example.myapp.main.util.MailManager;
import com.example.myapp.main.util.SessionBean;

/**
 * Send an email to user to confirm password reset.
 * 
 * @author Luca Vercelli
 *
 */
@WebServlet("/ui/doRegisterServlet")
public class DoRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = -1853319438734405659L;

	@Inject
	ApplicationProperties appProps;
	@Inject
	SessionBean sessionBean;
	@Inject
	UsersManager usersManager;
	@Inject
	Logger LOG;
	@Inject
	GenericManager genericManager;

	public final static String ERROR_MESSAGE = "error_message_register";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.removeAttribute(ERROR_MESSAGE);
		System.out.println("SONO IN REGISTRAZIONE");
		String pwd = request.getParameter("pwd"); // request's data not reliable
		String email = request.getParameter("email");
		String userId = request.getParameter("userId");
		
		System.out.println("SONO IN REGISTRAZIONE userId="+userId+ " email="+email+" pwd="+pwd);
		try {
			User user = new User();
			user.setEmail(email);
			user.setName(userId);
			BooleanYN active = BooleanYN.Y;
			user.setActive(active);
			user.setPersonName("Name");
			usersManager.setPassword(user, pwd);
			User a = genericManager.save(user);
			request.login(a.getName(),pwd);
			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			LOG.error("Exception while register account ", e);
			session.setAttribute(ERROR_MESSAGE, "err.register.form");
			response.sendRedirect(request.getContextPath() + appProps.getProperty("register.uri"));
		}
		
//		try {
//			System.out.println("SONO IN REGISTRAZIONE");
//			String address = request.getParameter("pwd"); // request's data not reliable
//			String email = request.getParameter("email");
//			String userId = request.getParameter("userId");
//			
//			if (email.isEmpty()) {
//				session.setAttribute(ERROR_MESSAGE, "err.missing.email"); 
//				// FIXME
//				// won't
//				// be
//				// seen
//				response.sendRedirect(request.getContextPath() + appProps.getProperty("password.recovery.uri"));
//				return;
//				// response.sendRedirect(request.getContextPath());
//			}
//
//			User user = usersManager.getUserByEmail(email);
//			System.out.println(user.toString());
//			if (user == null) {
//				session.setAttribute(ERROR_MESSAGE, "err.unknown.email");// FIXME
//																			// won't
//																			// be
//																			// seen
//				response.sendRedirect(request.getContextPath() + appProps.getProperty("password.recovery.uri"));
//				return;
//				// response.sendRedirect(request.getContextPath());
//			}
//
//			String pwdRecCode = usersManager.createPasswordRecoveryCode(user);
//			System.out.println(pwdRecCode);
//			// TODO some more clever text
//			mailManager.sendSimpleTextMail(email, "Password reset",
//					"You have requested to reset " + address + " login password.\r\n"
//							+ "If you really want to proceed, follow this link:\r\n" + address
//							+ "/ui/confirmPasswordRecovery?code=" + pwdRecCode + "\r\n");
//
//			session.setAttribute(ERROR_MESSAGE, "email.recovery.sent");
//			response.sendRedirect(request.getContextPath() + appProps.getProperty("password.recovery.uri"));
//
//		} catch (Exception e) {
//			LOG.error("Exception while sending email", e);
//
//			// FIXME Should send back some message? internal error
//			session.setAttribute(ERROR_MESSAGE, "err.cannot.send.email");
//			response.sendRedirect(request.getContextPath() + appProps.getProperty("login.uri"));
//		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
