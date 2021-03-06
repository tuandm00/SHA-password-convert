/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import dtos.UserDTO;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebFilter(filterName = "FilterLogin", urlPatterns = {"/GetAllUserController"})
public class FilterUser implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public FilterUser() {
    }    
    
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //check login
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");

        if (user != null) {
            if (user.getRoleID().equals("US")) {
                chain.doFilter(request, response);
            } else   res.sendRedirect("login.html");           
        } else  res.sendRedirect("login.html");
        
    }

    
    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        
        
    }
    
    
}
