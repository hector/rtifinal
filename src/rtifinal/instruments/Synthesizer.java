package rtifinal.instruments;

import rtifinal.effects.*;
import rtifinal.graphics.MagicCube;

public class Synthesizer extends Instrument {

  Delay delay;
  Distortion distortion;
  Reverb reverb;
  
  MagicCube cube;
  int color;


  public Synthesizer() {
    delay = null;
    distortion = null;
    reverb = null;
    color = p5.color(230,100,30);
    createCube();
  }

  private void createCube() {
    cube = new MagicCube(p5.height/4);
    cube.setColor(color);
    cube.setBPM(30);
  }
  
  public void addReverb(Reverb reverb) {
    this.reverb = reverb;
    cube.addPyramid(0);
  }
  
  public void removeReverb() {
    this.reverb = null;
    cube.removePyramid(0);
  }
  
  public void addDelay(Delay delay) {
    this.delay = delay;
    cube.addPyramid(1);
  }
  
  public void removeDelay() {
    this.delay = null;
    cube.removePyramid(1);
  }
  
  public void addDistortion(Distortion distortion) {
    this.distortion = distortion;
    cube.addPyramid(2);
  }
  
  public void removeDistortion() {
    this.distortion = null;
    cube.removePyramid(2);
  }  

  public void draw() {
    cube.draw();
    
  }


}
