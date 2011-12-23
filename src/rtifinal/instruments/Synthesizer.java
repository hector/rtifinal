package rtifinal.instruments;

import rtifinal.effects.*;
import rtifinal.graphics.Cube;

public class Synthesizer extends Instrument {

  Delay delay;
  Distortion distortion;
  Reverb reverb;
  Cube cube;
  int color;


  public Synthesizer() {
    delay = null;
    distortion = null;
    reverb = null;
    color = p5.color(200,100,30);
    createCube();
  }

  private void createCube() {
    cube = new Cube(p5.height/4);
    cube.setColor(color);
    cube.setBPM(30);
  }

  public void draw() {
    cube.draw();
    
  }


}
