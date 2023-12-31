package handler;

import request.ParsedRequest;

public class HandlerFactory {
    // routes based on the path. Add your custom handlers here
    public static BaseHandler getHandler(ParsedRequest request) {
        switch (request.getPath()) {
            case "/createUser":
                return new CreateUserHandler();
            case "/login":
                return new LoginHandler();
            case "/getConversations":
                return new GetConversationsHandler();
            case "/getConversation":
                return new GetConversationHandler();
            case "/createMessage":
                return new CreateMessageHandler();
            case "/getUserList":
                return new UserListHandler();
            case "/deleteConversation":
                return new DeleteHandler();
            case "/friendRequest":
                return new FriendRequestHandler();
            case "/getFriendRequest":
                return new GetFriendRequestHandler();
            case "/getRPS":
                return new RPSHandler();
            default:
                return new FallbackHandler();
        }
    }
}
