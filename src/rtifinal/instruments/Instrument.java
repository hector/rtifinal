package rtifinal.instruments;

import java.util.ArrayList;
import oscP5.OscMessage;
import rtifinal.OSC.Beatmachine;
import rtifinal.graphics.Drawable;

public abstract class Instrument extends Drawable {
  
  Beatmachine layout;
  
  public Instrument() {
    super();
    layout = new Beatmachine();
  }
  
  public void oscEvent(OscMessage msg) throws Exception {
    layout.oscEvent(msg);
  }

  public ArrayList<OscMessage> oscLayoutMessages() {
    return layout.oscMessages();
  }

}
