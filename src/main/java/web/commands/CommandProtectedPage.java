package web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommandProtectedPage extends Command
{
    public String role;
    public int id;
    public String email;
    public String pageToShow;

    public CommandProtectedPage(String pageToShow, String role)
    {
        this.pageToShow = pageToShow;
        this.role = role;
        this.id = id;

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
    {

        return pageToShow;

    }

    public int getId(){
        return id;
    }

    public String getRole()
    {
        return role;
    }
    public String getEmail(){
        return email;
    }

}
