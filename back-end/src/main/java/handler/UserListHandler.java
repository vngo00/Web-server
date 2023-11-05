package handler;

import dao.ConversationDao;
import dto.BaseDto;
import dto.ConversationDto;
import org.bson.Document;
import request.ParsedRequest;
import response.HttpResponseBuilder;
import response.RestApiAppResponse;

import java.util.ArrayList;
import java.util.List;

public class UserListHandler implements BaseHandler {
    @Override
    public HttpResponseBuilder handleRequest(ParsedRequest request) {
        ConversationDao conversationDao = ConversationDao.getInstance();
        AuthFilter.AuthResult authResult = AuthFilter.doFilter(request);
        if(!authResult.isLoggedIn){
            return new HttpResponseBuilder().setStatus(StatusCodes.UNAUTHORIZED);
        }

        var filter = new Document("userName", authResult.userName);

        var res = new RestApiAppResponse<>(true, getUsers(authResult.userName, conversationDao.query(filter)), null);
        return new HttpResponseBuilder().setStatus("200 OK").setBody(res);
    }

    List<FriendDto> getUsers(String userName, List<ConversationDto> query){
        List<FriendDto> users = new ArrayList<>();
        query.forEach(conversationDto -> {
            String[] names = conversationDto.getConversationId().split("_");
            String name = "";
            for(String s : names){
                System.out.println(s);
            }
            if(names[0].compareTo(userName) == 0){

                name= names[1];
            }
            else{
                name = names[0];
            }
            users.add(new FriendDto(name, conversationDto.getConversationId()));
        });
        return users;
    }

    public static class FriendDto extends BaseDto{
        String conversationId;
        String friendName;

        FriendDto(String friendName, String conversationId){
            this.friendName = friendName;
            this.conversationId = conversationId;
        }


        @Override
        public Document toDocument() {
            return new Document().append("friendName", friendName).append("conversationId", conversationId);
        }
    }
}
