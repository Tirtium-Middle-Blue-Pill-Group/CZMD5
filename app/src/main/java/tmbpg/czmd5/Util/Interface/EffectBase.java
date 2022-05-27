package tmbpg.czmd5.Util.Interface;

import tmbpg.czmd5.Util.People;

public abstract class EffectBase {
  protected int timeLast = 0;

  public int getTimeLast() {
    return this.timeLast;
  }

  public void resetTimeLast() {
    this.timeLast = 0;
  }

  public int getTimeRemain() {
    return this.getTime() - this.timeLast;
  }

  public void addTime() {
    this.timeLast -= this.getTime();
  }

  public abstract String getName();

  public void tick() {
    timeLast++;
  }

  public boolean isTimeOut() {
    return timeLast >= getTime();
  }

  public abstract int getTime();

  public abstract void apply(People people);

  public abstract void remove(People people);

  public abstract void execute(People people, People source);
}
