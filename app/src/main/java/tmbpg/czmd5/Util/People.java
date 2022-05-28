package tmbpg.czmd5.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase;
import tmbpg.czmd5.Util.LogUtil.TextColor;

public class People {
  public static final List<SkillBase> commonSkills = new ArrayList<>();
  private final String name;
  private final Random random;
  private int hp, maxHp;
  private final Subject subject;
  private final int damageBaseMin, damageBaseMax;
  private final List<SkillBase> skills = new ArrayList<>();
  private final List<EffectBase> effects = new ArrayList<>();
  private int score = 0;
  private float damageMul = 1;
  private boolean hidden = false;

  /**
   * 创建一个新的人物
   * 
   * @param name          名字
   * @param seed          随机数种子
   * @param hp            初始血量
   * @param subject       学科
   * @param damageBaseMin 攻击力最小值
   * @param damageBaseMax 攻击力最大值
   * @param skills        技能
   */
  public People(String name, long seed, int hp, Subject subject, int damageBaseMin, int damageBaseMax,
      SkillBase... skills) {
    this.name = name;
    this.random = new Random(seed);
    this.hp = hp;
    this.subject = subject;
    this.damageBaseMin = damageBaseMin;
    this.damageBaseMax = damageBaseMax;
    for (SkillBase skill : skills)
      this.skills.add(skill);
  }

  /**
   * 获取攻击力的中间值
   */
  public int getAverageDamage() {
    return (damageBaseMin + damageBaseMax) / 2;
  }

  /**
   * 获取名字
   */
  public String getName() {
    return name;
  }

  /**
   * 获取攻击量
   */
  public int getDamageAmount(People target) {
    if (target.getSubject() == subject)
      return 0;
    return (int) ((random.nextInt(damageBaseMax - damageBaseMin + 1) + damageBaseMin) * damageMul);
  }

  /**
   * 效果计算
   */
  public void tickEffects() {
    List<EffectBase> timeout = new ArrayList<>();
    for (EffectBase effect : effects) {
      effect.execute(this, effect.getSource());
      effect.tick();
      if (effect.isTimeOut()) {
        LogUtil.formatLog("%s 的效果 %s 已失效", TextColor.MAGENTA, this.name, effect.getName());
        timeout.add(effect);
        effect.remove(this);
      }
      if (this.isDead()) {
        LogUtil.formatLog("%s 被 %s 施加的 %s 效果杀死了", TextColor.RED, this.name, effect.getSource().getName(),
            effect.getName());
        return;
      }
    }
    effects.removeAll(timeout);
  }

  /**
   * 添加效果
   * 
   * @param effect 效果
   */
  public void addEffect(EffectBase effect) {
    LogUtil.formatLog("%s 获得效果： %s ，持续 %d 回合", TextColor.MAGENTA, this.getName(), effect.getName(), effect.getTime());
    for (EffectBase e : effects)
      if (e.getClass().equals(effect.getClass())) {
        e.addTime();
        LogUtil.formatLog("效果 %s 叠加至 %d 回合", TextColor.BLUE, e.getName(), e.getTimeLast());
        return;
      }
    effects.add(effect);
    effect.apply(this);
  }

  /**
   * 获取血量
   */
  public int getHp() {
    return hp;
  }

  /**
   * 伤害
   * 
   * @param damage 伤害值
   */
  public void damage(int damage) {
    hp -= damage;
  }

  /**
   * 设置血量
   * 
   * @param hp 血量
   */
  public void setHp(int hp) {
    this.hp = hp;
  }

  /**
   * 获取最大血量
   */
  public int getMaxHp() {
    return maxHp;
  }

  /**
   * 获取学科
   */
  public Subject getSubject() {
    return subject;
  }

  /**
   * 获取技能
   */
  public List<SkillBase> getSkills() {
    List<SkillBase> result = new ArrayList<>();
    result.addAll(skills);
    result.addAll(commonSkills);
    return result;
  }

  /**
   * 是否死亡
   */
  public boolean isDead() {
    return hp <= 0;
  }

  /**
   * 获取下一个随机数
   */
  public int nextInt(int size) {
    return random.nextInt(size);
  }

  /**
   * 获取分数
   */
  public int getScore() {
    return this.score;
  }

  /**
   * 添加分数
   * 
   * @param score 分数
   */
  public void addScore(int score) {
    this.score += score;
  }

  /**
   * 添加技能
   * 
   * @param skills 技能
   */
  public void addSkillls(SkillBase... skills) {
    for (SkillBase skill : skills)
      this.skills.add(skill);
  }

  /**
   * 设置攻击力倍率
   * 
   * @param damageMul 倍率
   */
  public void setDamageMul(float damageMul) {
    this.damageMul = damageMul;
  }

  /**
   * 获取攻击力倍率
   */
  public float getDamageMul() {
    return damageMul;
  }

  /**
   * 设置是否隐身
   * 
   * @param hidden 是否隐身
   */
  public void setHidden(boolean hidden) {
    this.hidden = hidden;
  }

  /**
   * 是否隐身
   */
  public boolean isHidden() {
    return hidden;
  }
}
