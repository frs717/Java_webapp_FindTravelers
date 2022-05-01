package ru.rsreu.FindingTravelCrappo.filters;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.rsreu.FindingTravelCrappo.commands.CommandType;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String commandName = request.getParameter("command");
        User user = (User) request.getSession().getAttribute("user");

        if (commandName == null) {
            if (user == null) {
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "?command=showLoginPage");
            } else {
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "?command=showMainPage");
            }

        } else {
            CommandType commandType = CommandType.getCommandType(commandName);
            if (commandType != CommandType.LOGIN && commandType != CommandType.SHOW_LOGIN_PAGE) {
                if (user == null) {
                    response.sendRedirect(request.getContextPath() + request.getServletPath() + "?command=showLoginPage");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }
}
