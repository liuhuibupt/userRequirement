package com.charmingglobe.gr.builder;

import com.charmingglobe.gr.entity.Cavalier;
import com.charmingglobe.gr.entity.UserAction;
import com.charmingglobe.gr.entity.UserRequest;

import java.util.Date;

/**
 * Created by PANZHENG on 2018/2/8.
 */
public class UserActionBuilder {

    public static UserAction create() {
        UserAction userAction = new UserAction();
        userAction.setActionName("Submit User Request");
        userAction.setExecutor(null);
        return userAction;
    }

    public static UserAction createSubmitUserRequestAction(UserRequest userRequest, Cavalier executor) {
        UserAction userAction = new UserAction();
        userAction.setActionName("Submit User Request [RequestName=" + userRequest.getRequestName() + "]");
        userAction.setExecutor(executor);
        userAction.setJumpLink("userRequest?userRequestId=" + userRequest.getId());
        userAction.setActionTime(new Date());
        return userAction;
    }
}
