package us.supercheng.emall.service;

import us.supercheng.emall.common.ServerResponse;
import us.supercheng.emall.pojo.User;

public interface IUserService {
    ServerResponse<User> login(String username, String password);

    ServerResponse<String> check_valid(String inText, String acctType);

    ServerResponse<String> register(User inUser);

    ServerResponse<String> getUserQuestion(String username);

    ServerResponse<String> checkQuestionAnswer(String username, String question, String answer);
}