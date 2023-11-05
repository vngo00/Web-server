package handler;

import dto.RPSDto;
import request.ParsedRequest;
import response.HttpResponseBuilder;
import response.RestApiAppResponse;

public class RPSHandler implements BaseHandler {
  @Override
  public HttpResponseBuilder handleRequest(ParsedRequest request) {
    RPSDto rpsDto = GsonTool.gson.fromJson(request.getBody(), dto.RPSDto.class);

    AuthFilter.AuthResult authResult = AuthFilter.doFilter(request);
    if (!authResult.isLoggedIn) {
      return new HttpResponseBuilder().setStatus(StatusCodes.UNAUTHORIZED);
    }


    switch (rpsDto.getRps()){
      case "Rock":
        if(rpsDto.getCpu().equals("Rock")){
          var res = new RestApiAppResponse<>(true, null, "cpu picked Rock: game tied");
          return new HttpResponseBuilder().setStatus("200 OK").setBody(res);
        }else if(rpsDto.getCpu().equals("Paper")){
          var res = new RestApiAppResponse<>(true, null, "cpu picked Paper: you lost");
          return new HttpResponseBuilder().setStatus("200 OK").setBody(res);
        }else if(rpsDto.getCpu().equals("Scissor")){
          var res = new RestApiAppResponse<>(true, null, "cpu picked Scissor: you won");
          return new HttpResponseBuilder().setStatus("200 OK").setBody(res);
        }
      case "Paper":
        if(rpsDto.getCpu().equals("Rock")){
          var res = new RestApiAppResponse<>(true, null, "cpu picked Scissor: you won");
          return new HttpResponseBuilder().setStatus("200 OK").setBody(res);
        }else if(rpsDto.getCpu().equals("Paper")){
          var res = new RestApiAppResponse<>(true, null, "cpu picked Rock: game tied");
          return new HttpResponseBuilder().setStatus("200 OK").setBody(res);
        }else if(rpsDto.getCpu().equals("Scissor")){
          var res = new RestApiAppResponse<>(true, null, "cpu picked Paper: you lost");
          return new HttpResponseBuilder().setStatus("200 OK").setBody(res);
        }
      case "Scissor":
        if(rpsDto.getCpu().equals("Rock")){
          var res = new RestApiAppResponse<>(true, null, "cpu picked Paper: you lost");
          return new HttpResponseBuilder().setStatus("200 OK").setBody(res);
        }else if(rpsDto.getCpu().equals("Paper")){
          var res = new RestApiAppResponse<>(true, null, "cpu picked Scissor: you won");
          return new HttpResponseBuilder().setStatus("200 OK").setBody(res);
        }else if(rpsDto.getCpu().equals("Scissor")){
          var res = new RestApiAppResponse<>(true, null, "cpu picked Rock: game tied");
          return new HttpResponseBuilder().setStatus("200 OK").setBody(res);
        }
    }


    var res = new RestApiAppResponse<>(true, null, "Error");
    return new HttpResponseBuilder().setStatus("200 OK").setBody(res);
  }
}