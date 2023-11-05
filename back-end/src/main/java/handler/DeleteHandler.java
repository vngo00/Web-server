package handler;

import dao.ConversationDao;
import dto.ConversationDto;
import request.ParsedRequest;
import response.HttpResponseBuilder;
import response.RestApiAppResponse;

public class DeleteHandler implements BaseHandler{
    @Override
    public HttpResponseBuilder handleRequest(ParsedRequest request) {
        ConversationDao conversationDao = ConversationDao.getInstance();

        AuthFilter.AuthResult authResult = AuthFilter.doFilter(request);
        if(!authResult.isLoggedIn){
            return new HttpResponseBuilder().setStatus(StatusCodes.UNAUTHORIZED);
        }

        String conversationId = CreateMessageHandler.makeConvoId(authResult.userName, request.getQueryParam("name"));

        ConversationDto conversationDto = new ConversationDto();
        conversationDto.setUserName(authResult.userName);
        conversationDto.setConversationId(conversationId);


        if(conversationDao.delete(conversationDto))
            return new HttpResponseBuilder()
                    .setStatus(StatusCodes.OK)
                    .setBody(new RestApiAppResponse<>(true, null, "Conversation deleted"));

        return new HttpResponseBuilder()
                .setStatus(StatusCodes.OK)
                .setBody(new RestApiAppResponse<>(true, null, "conversation not found"));

    }
}
