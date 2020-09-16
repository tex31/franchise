package com.douane.exception;

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.FactoryFinder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;



/**
 * Phase listener for the Restore View Phase which manages display of login page.
 */
public class SecurityPhaseListener implements PhaseListener
{

    public void afterPhase(PhaseEvent event) { ; }

    public void beforePhase(PhaseEvent event) {
        FacesContext fc = event.getFacesContext();

        String loginPage = (String) fc.getExternalContext().getRequestMap().
            get("web.secfilter.authenticator.showLogin");
        if (StringUtils.isNotBlank(loginPage)) {
            doRedirect(fc, loginPage);
        }
    }

    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    /**
     * Does a regular or ajax redirect.
     */
    public void doRedirect(FacesContext fc, String redirectPage) 
        throws FacesException {
        ExternalContext ec = fc.getExternalContext();

        try {
            if (ec.isResponseCommitted()) {
                // redirect is not possible
                return;
            }

            // fix for renderer kit (Mojarra's and PrimeFaces's ajax redirect)
            if ((RequestContext.getCurrentInstance().isAjaxRequest()
                || fc.getPartialViewContext().isPartialRequest())
                && fc.getResponseWriter() == null
                && fc.getRenderKit() == null) {
                    ServletResponse response = (ServletResponse) ec.getResponse();
                    ServletRequest request = (ServletRequest) ec.getRequest();
                    response.setCharacterEncoding(request.getCharacterEncoding());

                    RenderKitFactory factory = (RenderKitFactory)  
                     FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);

                    RenderKit renderKit = factory.getRenderKit(fc,
                     fc.getApplication().getViewHandler().calculateRenderKitId(fc));

                    ResponseWriter responseWriter =
                        renderKit.createResponseWriter(
                        response.getWriter(), null, request.getCharacterEncoding());
                        fc.setResponseWriter(responseWriter);
            }
            System.out.println("context path :"+ ec.getRequestContextPath());
            String redirection = ec.getRequestContextPath() + 
                    (redirectPage != null ? redirectPage : "");
            ec.redirect(redirection);
        } catch (IOException e) {
            System.out.println("Redirect to the specified page '" + 
                    redirectPage + "' failed");
            throw new FacesException(e);
        }catch(NullPointerException n) {
        	System.out.println("Redirect to the specified page '" + 
                    redirectPage + "' failed");
            throw new FacesException(n);
        }
    }
}
