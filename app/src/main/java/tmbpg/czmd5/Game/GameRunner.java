package tmbpg.czmd5.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tmbpg.czmd5.Data.Skill.HaveDailyTalk;
import tmbpg.czmd5.Data.Skill.OneEyeMethod;
import tmbpg.czmd5.Util.LogUtil;
import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase;
import tmbpg.czmd5.Util.LogUtil.TextColor;

public class GameRunner {
  private final Random random;
  private final List<People> peoples = new ArrayList<>();
  private int tick = 0;

  public GameRunner(long seed) {
    random = new Random(seed);
  }

  public void run() {
    peoples.add(new People("顾志刚", random.nextLong(), 500, Subject.Math, 8, 12, new OneEyeMethod()));
    peoples.add(new People("王华", random.nextLong(), 500, Subject.English, 8, 12, new HaveDailyTalk()));
    peoples.add(new People("丁燕华", random.nextLong(), 500, Subject.IT, 8, 12));

    while (this.tick())
      ;
    LogUtil.log("游戏结束！");
    LogUtil.log(String.format("%s 胜利", this.getWinner().getName()), TextColor.GREEN);
  }

  /**
   * 主要的计算代码
   * 
   * @return 游戏是否仍在运行，false表示计算结束
   */
  private boolean tick() {
    if (peoples.size() == 1)
      throw new RuntimeException("就一个人了你玩个锤子");
    tick++;
    List<People> dead = new ArrayList<>();
    People source = peoples.get(random.nextInt(peoples.size()));
    People target = peoples.get(random.nextInt(peoples.size()));
    while (target.getName().equals(source.getName()))
      target = peoples.get(random.nextInt(peoples.size()));
    int damage = source.getDamageAmount(target);
    target.damage(damage);
    LogUtil.log(String.format("第%d回合：%s 攻击了 %s ，血量%d->%d", tick, source.getName(), target.getName(),
        target.getHp() + damage, target.getHp()), TextColor.YELLOW);
    for (SkillBase skill : source.getSkills())
      if (source.nextInt(skill.getTriggerProb()) == 0) {
        target.damage(skill.getDamage(target));
        LogUtil.log(String.format("%s 额外触发技能 %s ，血量%d->%d", source.getName(), skill.getName(),
            target.getHp() + skill.getDamage(target), target.getHp()), TextColor.CYAN);
        for (EffectBase effect : skill.getEffects()) {
          LogUtil.log(String.format("%s 获得效果： %s ，持续%d秒", target.getName(), effect.getName(), effect.getTime()),
              TextColor.MAGENTA);
          target.addEffect(effect);
        }
      }
    if (target.isDead()) {
      LogUtil.log(String.format("%s 死了", target.getName()), TextColor.RED);
      dead.add(target);
    }
    for (People p : peoples)
      p.tickEffects();
    peoples.removeAll(dead);
    return peoples.size() > 1;
  }

  public People getWinner() {
    if (peoples.size() == 1)
      return peoples.get(0);
    throw new RuntimeException("游戏还没有结束，没有胜利者");
  }
}
