package tmbpg.czmd5.Util.Interface;

import tmbpg.czmd5.Util.People;

public abstract class EffectBase {
  protected int timeLast;
  private final People source, target;

  /**
   * 新建一个效果
   * 
   * @param source 施加者
   * @param target 施加目标
   */
  public EffectBase(People source, People target) {
    this.source = source;
    this.target = target;
    timeLast = this.getTime();
  }

  /**
   * 获取效果剩余时间
   */
  public int getTimeLast() {
    return this.timeLast;
  }

  /**
   * 加一份时间
   */
  public void addTime() {
    this.timeLast += this.getTime();
  }

  /**
   * 时间-1
   */
  public void tick() {
    timeLast--;
  }

  /**
   * 是否结束
   */
  public boolean isTimeOut() {
    return timeLast < 0;
  }

  /**
   * 获取施加者
   */
  public People getSource() {
    return source;
  }

  /**
   * 获取施加目标
   */
  public People getTarget() {
    return target;
  }

  /**
   * 获取效果名字
   */
  public abstract String getName();

  /**
   * 获取效果持续时间
   */
  public abstract int getTime();

  /**
   * 效果给予时调用
   */
  public abstract void apply(People people);

  /**
   * 效果结束时调用
   */
  public abstract void remove(People people);

  /**
   * 每tick调用
   */
  public abstract void execute(People people, People source);
}
