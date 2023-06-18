package br.com.rankbet.filter;


import br.com.rankbet.model.UserModel;


import jakarta.faces.context.FacesContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class AuthenticationFilter implements Filter {

    /**
     * Default constructor.
     */
    public AuthenticationFilter() {

    }


    /**
     * @see Filter#destroy()
     */
    public void destroy() {

    }


    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
        throws IOException,
        ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        UserModel dummyUser = (UserModel) httpServletRequest.getSession().getAttribute( "user" );

        if ( dummyUser == null ) {
            //chain.doFilter( request, response );
            //
            // httpServletResponse.sendRedirect( httpServletRequest.getContextPath().toString() + "/login.xhtml" );
            RequestDispatcher rd = request.getRequestDispatcher("/login.xhtml");
            rd.forward(request,response);
        } else
            chain.doFilter( request, response );

    }


    /**
     * @see Filter#init(FilterConfig)
     */
    public void init( FilterConfig fConfig )
        throws ServletException {

    }

}
