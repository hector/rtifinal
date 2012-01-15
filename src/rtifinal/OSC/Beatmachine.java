package rtifinal.OSC;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import oscP5.OscMessage;

public class Beatmachine {
  
  HashMap<String,Control> controls;
  
  public Beatmachine() {
    controls = new HashMap<String,Control>();
    createControls();
  }
  
  private void createControls() {
    createControlsPage1();
    createControlsPage2();
    createControlsPage3();
    createControlsPage4();
    addControl(new Accelerometer());
  }

  private void createControlsPage1() {
    addControl(new Fader("/1",1));
    addControl(new Fader("/1",2));
    addControl(new Toggle("/1",1));
    addControl(new Toggle("/1",2));
    for(int i=1; i <= 12; i++) {
      addControl(new Push("/1",i));
    }
  }

  private void createControlsPage2() {
    for(int i=1; i<=6; i++) {
      for(int j=1; j<=16; j++) {
        addControl(new Multitoggle("/2",i,j));
      }
    }
    for(int k=1; k<=16; k++) {
      addControl(new Multifader("/2",k));
    }
  }

  private void createControlsPage3() {
    for(int i=1; i<=6; i++) {
      addControl(new Rotary("/3",i));
    }
    for(int j=1; j<=5; j++) {
      addControl(new Toggle("/3",j));
    }
  }

  private void createControlsPage4() {
    addControl(new XYPad("/4", null));
    for(int j=1; j<=5; j++) {
      addControl(new Toggle("/4",j));
    }
  }
    
  private void addControl(Control ctrl) {
    controls.put(ctrl.getOscAddr(), ctrl);
  }
  
  public void oscEvent(OscMessage msg) throws Exception {
    // Send osc message to the corresponding control
    Control control = controls.get(msg.addrPattern());
    if(control != null) control.oscEvent(msg);
  }
  
  public ArrayList<OscMessage> oscMessages() {
    ArrayList<OscMessage> msgs = new ArrayList();
    OscMessage msg;
    for(Control control : controls.values()) {
      msg = control.oscMessage();
      if(msg != null) msgs.add(msg);
    }
    return msgs;
  }
  
  public Control getControl(String oscAddr) {
    return controls.get(oscAddr);
  }

}
