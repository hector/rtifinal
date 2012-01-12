package rtifinal.instruments;

import java.text.DecimalFormat;
import java.util.ArrayList;
import oscP5.OscMessage;
import processing.core.PVector;
import rtifinal.OSC.*;
import rtifinal.graphics.*;

public abstract class Instrument extends Drawable {
  
  Beatmachine layout;
  MagicCube cube;
  int color, size;
  boolean track, accelerometer;  
  
  public Instrument() throws Exception {
    this(null);
  }

  public Instrument(Integer size) throws Exception {
    super();
    layout = new Beatmachine();
    mapLayout();
    volumeFader().setValues((float)1);    
    color = p5.color(230,100,30);
    track = false;
    accelerometer = false;
    this.size = size != null ? size.intValue() : p5.height/4;
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
    layout.getControl("/1/fader2").map(this, "volume");
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
    layout.getControl("/4/toggle3").map(this, "trackAccelerometer");
    layout.getControl("/4/xy").map(this, "setPositionFromXY");
    layout.getControl("/accxyz").map(this, "angleY");
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
    if(value == 1) bump();
  }
  
  @Override
  public void setScale(float scale) {
    super.setScale(scale);
    cube.setScale(scale);
  }

  public void mute(boolean mute) {
    if(mute) setAlpha(25);
    else volume(volumeFader().getValue());
  }
  
  public void volume(float volume) {
    if(!muteToggle().boolValue()) setAlpha(25+(255-25)*volume);
  }
  
  @Override
  public void setAlpha(float alpha) {
    super.setAlpha(alpha);
    cube.setAlpha(alpha);
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
  
  public void trackAccelerometer(boolean track) {
    this.accelerometer = track;
  }
  
  public void angleY(float y, float x) {
    if (accelerometer) {
      DecimalFormat format = new DecimalFormat("#.#");
      float angle = Float.valueOf(format.format(-x * p5.PI));
      this.setAngleY(angle);
    }
  }
  
  @Override
  public void setAngleY(float angle) {
    super.setAngleY(angle);
    cube.setAngleY(angle);
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
  
  @Override
  public void bump(float bumpScale) {
    float volume = volumeFader().getValue();
    bumpScale *= volume;
    super.bump(bumpScale);
    cube.bump(bumpScale);
  }  
  
  public Beatmachine getLayout() {
    return layout;
  }
  
  protected Fader volumeFader() {
    return (Fader) layout.getControl("/1/fader2");
  }  
  
  protected Toggle muteToggle() {
    return (Toggle) layout.getControl("/1/toggle2");
  }

}
