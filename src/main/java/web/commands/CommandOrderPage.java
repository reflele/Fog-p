package web.commands;

import business.exceptions.UserException;
import web.commands.Command;
import web.commands.CommandUnprotectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandOrderPage extends Command {

    public String pageToShow;



    public CommandOrderPage(String pageToShow)
    {
        this.pageToShow = pageToShow;

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {


        return "orderpage";
    }

}