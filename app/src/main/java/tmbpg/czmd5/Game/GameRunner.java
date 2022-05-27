package tmbpg.czmd5.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tmbpg.czmd5.Data.GlobalSettings;
import tmbpg.czmd5.Util.DeadCycleException;
import tmbpg.czmd5.Util.LogUtil;
import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase;
import tmbpg.czmd5.Util.LogUtil.TextColor;

public class GameRunner {
  private final Random random;
  private final List<People> peoples = new ArrayList<>();
  private final List<People> alivePeoples = new ArrayList<>();
  private final DataManager dataManager;
  private int tick = 0;

  public GameRunner(long seed) {
    random = new Random(seed);
    dataManager = new DataManager(random);
  }

  public void run() {
    LogUtil.log("游戏开始", TextColor.GREEN);
    for (People people : dataManager.getPeople()) {
      peoples.add(people);
      alivePeoples.add(people);
    }
    while (this.tick())
      ;
    LogUtil.log("游戏结束！");
    LogUtil.log(String.format("%s 胜利", this.getWinner().getName()), TextColor.GREEN);
    for (People people : peoples)
      LogUtil.log(String.format("%s 的得分为 %d", people.getName(), people.getScore()), TextColor.WHITE);
  }

  /**
   * 主要的计算代码
   * 
   * @return 游戏是否仍在运行，false表示计算结束
   */
  private boolean tick() {
    if (alivePeoples.size() == 1)
      throw new RuntimeException("就一个人了你玩个锤子");
    tick++;
    List<People> dead = new ArrayList<>();
    // 选择
    People source = alivePeoples.get(random.nextInt(alivePeoples.size()));
    int count = 0;
    while (source.getSkipTurns() > 0) {
      source = alivePeoples.get(random.nextInt(alivePeoples.size()));
      if (count++ > GlobalSettings.cycleTimeOut)
        throw new DeadCycleException();
    }
    People target = alivePeoples.get(random.nextInt(alivePeoples.size()));
    count = 0;
    while (target.getName().equals(source.getName()) || target.getSkipTurns() > 0 || target.isHidden()) {
      target = alivePeoples.get(random.nextInt(alivePeoples.size()));
      if (count++ > GlobalSettings.cycleTimeOut)
        throw new DeadCycleException();
    }
    // 执行
    int damage = source.getDamageAmount(target);
    source.addScore(damage);
    target.damage(damage);
    LogUtil.log(String.format("第%d回合：%s 攻击了 %s ，血量%d->%d", tick, source.getName(), target.getName(),
        target.getHp() + damage, target.getHp()), TextColor.YELLOW);
    // 攻击别人的技能
    for (SkillBase skill : source.getSkills())
      if (source.nextInt(skill.getTriggerProb()) == 0) {
        if (!skill.shouldExecute(source, target, damage)) {
          LogUtil.log(String.format("%s 额外触发技能 %s ， 但是没效果", source.getName(), skill.getName()), TextColor.CYAN);
          continue;
        }
        skill.execute(source, target);
        LogUtil.log(
            String.format("%s 额外触发技能 %s ，", source.getName(), skill.getName()) + skill.getMessage(source, target),
            TextColor.CYAN);
        for (EffectBase effect : skill.getEffects()) {
          LogUtil.log(String.format("%s 获得效果： %s ，持续 %d 回合", target.getName(), effect.getName(), effect.getTime()),
              TextColor.MAGENTA);
          target.addEffect(effect, source);
        }
      }
    // 给自己的技能
    for (SkillBase skill2 : dataManager.getPositiveSkills())
      if (source.nextInt(skill2.getTriggerProb()) == 0)
        if (skill2.shouldExecute(source, target, damage)) {
          skill2.execute(source, target);
          LogUtil.log(
              String.format("%s 额外触发技能 %s ，", source.getName(), skill2.getName()) + skill2.getMessage(source, target),
              TextColor.CYAN);
          for (EffectBase effect : skill2.getEffects()) {
            LogUtil.log(String.format("%s 获得效果： %s ，持续 %d 回合", source.getName(), effect.getName(), effect.getTime()),
                TextColor.MAGENTA);
            source.addEffect(effect, source);
          }
        }
    if (target.isDead()) {
      LogUtil.log(String.format("%s 被 %s 杀死了", target.getName(), source.getName()), TextColor.RED);
      alivePeoples.remove(target);
    }
    // 运行每个人的效果
    for (People p : alivePeoples) {
      p.tickEffects();
      if (p.isDead())
        dead.add(p);
    }
    alivePeoples.removeAll(dead);
    return alivePeoples.size() > 1;
  }

  public People getWinner() {
    if (alivePeoples.size() == 1)
      return alivePeoples.get(0);
    throw new RuntimeException("游戏还没有结束，没有胜利者");
  }
}
