package rtifinal.OSC;

public class Multitoggle extends Toggle {
  
  public Multitoggle(String prefix, int row, int column) {
    super(prefix, "/"+String.valueOf(row)+"/"+String.valueOf(column));
  }

  @Override
  protected String oscStr() {
    return "/multitoggle";
  }    
  
}
