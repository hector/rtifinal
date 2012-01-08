package rtifinal.instruments;

import java.util.ArrayList;
import oscP5.OscMessage;
import processing.core.PVector;
import rtifinal.OSC.Beatmachine;
import rtifinal.graphics.Drawable;
import rtifinal.graphics.MagicCube;

public abstract class Instrument extends Drawable {
  
  Beatmachine layout;
  MagicCube cube;
  int color, size;
  boolean track;  
  
  public Instrument() throws Exception {
    super();
    layout = new Beatmachine();
    color = p5.color(230,100,30);
    track = false;
    mapLayout();
    size = p5.height/4;
    createCube();    
  }
  
  public void oscEvent(OscMessage msg) throws Exception {
    layout.oscEvent(msg);
  }

  public ArrayList<OscMessage> oscLayoutMessages() {
    return layout.oscMessages();
  }
  
  private void mapLayout() throws Exception {
    layout.getControl("/1/toggle1").map(this, "sequencer");
    layout.getControl("/1/toggle2").map(this, "mute");
    layout.getControl("/1/push1").map(this, "pushBump");
    layout.getControl("/1/push2").map(this, "pushBump");
    layout.getControl("/1/push3").map(this, "pushBump");
    layout.getControl("/1/push4").map(this, "pushBump");
    layout.getControl("/1/push5").map(this, "pushBump");
    layout.getControl("/1/push6").map(this, "pushBump");
    layout.getControl("/1/push7").map(this, "pushBump");
    layout.getControl("/1/push8").map(this, "pushBump");
    layout.getControl("/1/push9").map(this, "pushBump");
    layout.getControl("/3/toggle5").map(this, "effect", 0);
    layout.getControl("/3/toggle4").map(this, "effect", 1);
    layout.getControl("/3/toggle3").map(this, "effect", 2);
    layout.getControl("/3/toggle2").map(this, "effect", 3);
    layout.getControl("/3/toggle1").map(this, "effect", 4);
    layout.getControl("/4/toggle5").map(this, "effect", 5);
    layout.getControl("/3/rotary1").map(this, "effectParam", 0);
    layout.getControl("/3/rotary2").map(this, "effectParam", 1);
    layout.getControl("/3/rotary3").map(this, "effectParam", 2);
    layout.getControl("/3/rotary4").map(this, "effectParam", 3);
    layout.getControl("/3/rotary5").map(this, "effectParam", 4);
    layout.getControl("/3/rotary6").map(this, "effectParam", 5);
    layout.getControl("/4/toggle4").map(this, "trackXY");
    layout.getControl("/4/xy").map(this, "setPositionFromXY");
  }

  private void createCube() {
    cube = new MagicCube(size);
    cube.setColor(color);
    cube.setPosition(getPosition());
  }
  
  @Override
  public void setColor(int color) {
    this.color = color;
    cube.setColor(color);
  }  
  
  public void pushBump(float value) {
    if(value == 1) cube.bump();
  }
  
  @Override
  public void setScale(float scale) {
    super.setScale(scale);
    cube.setScale(scale);
  }

  public void mute(boolean mute) {
    if(mute) cube.setAlpha(150);
    else cube.setAlpha(255);
  }
  
  // Enable/disable effect
  public void effect(boolean enable, Integer effect) {
    cube.pyramidVisible(enable, effect);
  }
  
  public void effectParam(float param, Integer effect) {
    cube.pyramidHeight(param, effect);
  }
  
  public void trackXY(boolean track) {
    this.track = track;
  }
  
  public void setPositionFromXY(float y, float x) {
    if(track) setPosition(new PVector(p5.width*x, p5.height*(1-y)));
  }
  
  @Override
  public void setPosition(PVector position) {
    super.setPosition(position);
    cube.setPosition(position);
  }
  
  @Override
  public void setBPM(float bpm) {
    super.setBPM(bpm);
    cube.setBPM(bpm);
  }
  
  @Override
  public void sequencer(boolean on) {
    super.sequencer(on);
    cube.sequencer(on);
  }

  @Override
  public void draw() {
    cube.draw();
  } 
  
  public int getSize() {
    return size;
  }

}
