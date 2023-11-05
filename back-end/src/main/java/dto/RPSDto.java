package dto;

import org.bson.Document;

public class RPSDto extends BaseDto {
  private String rps;
  private String cpu;

  public RPSDto(String rps, String cpu){
    this.rps = rps;
    this.cpu = cpu;
  }

  public String getRps(){return rps;}

  public String getCpu(){return cpu;}

  @Override
  public Document toDocument() {
    return new Document().append("rps", rps).append("cpu", cpu);
  }
}
