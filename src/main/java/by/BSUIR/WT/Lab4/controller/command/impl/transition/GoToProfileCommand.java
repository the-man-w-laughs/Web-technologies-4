package by.BSUIR.WT.Lab4.controller.command.impl.transition;

import by.BSUIR.WT.Lab4.controller.command.Command;
import by.BSUIR.WT.Lab4.controller.command.CommandResult;
import by.BSUIR.WT.Lab4.controller.command.CommandResultType;
import by.BSUIR.WT.Lab4.controller.context.RequestContext;
import by.BSUIR.WT.Lab4.controller.context.RequestContextHelper;
import by.BSUIR.WT.Lab4.entity.User;
import by.BSUIR.WT.Lab4.entity.UserInformation;
import by.BSUIR.WT.Lab4.service.ServiceFactory;
import by.BSUIR.WT.Lab4.service.exception.ServiceException;
import by.BSUIR.WT.Lab4.service.intrfc.UserInformationService;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class GoToProfileCommand implements Command{

    private static final String PAGE 				= "WEB-INF/view/profile.jsp";
    private static final String ERROR_PAGE 			= "WEB-INF/view/error.jsp";
    private static final String USER 				= "user";
    private static final String USER_INFORMATION 	= "userInformation";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        User user = (User) requestContext.getSessionAttribute(USER);
        if (user == null) {
            helper.updateRequest(requestContext);
            return new CommandResult(PAGE, CommandResultType.FORWARD);
        }
        try {
            int userInformationId = user.getUserInformationId();
            UserInformationService userInformationService = ServiceFactory.getInstance().getUserInformationService();
            Optional<UserInformation> userInformation = userInformationService.retriveUserInformationById(userInformationId);
            userInformation.ifPresent(information -> requestContext.addRequestAttributes(USER_INFORMATION, information));
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
		
}
