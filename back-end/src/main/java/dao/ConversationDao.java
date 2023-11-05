package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import dto.ConversationDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.Document;

public class ConversationDao extends BaseDao<ConversationDto> {

  private static ConversationDao instance;

  private ConversationDao(MongoCollection<Document> collection){
    super(collection);
  }

  public static ConversationDao getInstance(){
    if(instance != null){
      return instance;
    }
    instance = new ConversationDao(MongoConnection.getCollection("ConversationDao"));
    return instance;
  }

  public static ConversationDao getInstance(MongoCollection<Document> collection){
    instance = new ConversationDao(collection);
    return instance;
  }

  @Override
  public void put(ConversationDto conversationDto) {
    collection.insertOne(conversationDto.toDocument());
  }

  @Override
  public List<ConversationDto> query(Document filter) {
    return collection.find(filter)
        .into(new ArrayList<>())
        .stream()
        .map(ConversationDto::fromDocument)
        .collect(Collectors.toList());
  }


  public boolean delete(ConversationDto conversationDto){
    DeleteResult result =  collection.deleteOne(conversationDto.toDocument());
    System.out.println(result.getDeletedCount());
    if(result.getDeletedCount() != 0){
      return true;
    }
    return false;
  }
}
