package com.douane.exception;

import com.douane.requesthttp.RequestFilter;
import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hasina on 11/28/17.
 */
@ManagedBean(name="customaccessBean")
@SessionScoped
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public static final Logger LOG
            = Logger.getLogger(CustomAccessDeniedHandler.class);



    private String message;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exc) throws IOException, ServletException {

        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            setMessage( "L' utilisateur immatriculé " + auth.getName()
                    + " a tenté d'accéder à une page protégée: "
                    + request.getRequestURI());
            RequestFilter.getSession().setAttribute("messageAccessDenied", "L' utilisateur immatriculé  " + auth.getName()
                    + " a tenté d'accéder à une page protégée: "
                    + request.getRequestURI());
            LOG.warn(getMessage());
        }
        PrintWriter out = response.getWriter();
        //out.write(message);
        response.sendRedirect(request.getContextPath() + "/pages/unsecure/error_403.xhtml");
    }

    public String getMessage() {
        return (String) RequestFilter.getSession().getAttribute("messageAccessDenied");
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
