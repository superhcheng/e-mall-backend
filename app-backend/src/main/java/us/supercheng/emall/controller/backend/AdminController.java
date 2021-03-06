package us.supercheng.emall.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import us.supercheng.emall.common.Const;
import us.supercheng.emall.common.ResponseCode;
import us.supercheng.emall.common.ServerResponse;
import us.supercheng.emall.pojo.User;
import us.supercheng.emall.service.IUserService;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/user")
public class AdminController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> serverResponseUser = this.iUserService.login(username, password);
        if (serverResponseUser.getStatus() == ResponseCode.SUCCESS.getCode()) {
            if (serverResponseUser.getData().getRole() == Const.ROLE_ADMIN) {
                session.setAttribute(Const.CURRENT_USER, serverResponseUser.getData());
            } else {
                return ServerResponse.createServerResponseError("Username: " + username + " is not an Admin User");
            }
        }
        return serverResponseUser;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> login(HttpSession session) {
        if (session.getAttribute(Const.CURRENT_USER) != null) {
            session.removeAttribute(Const.CURRENT_USER);
        }
        return ServerResponse.createServerResponseSuccess("Logout Success");
    }
}