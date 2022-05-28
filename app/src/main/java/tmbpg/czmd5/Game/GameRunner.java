package tmbpg.czmd5.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tmbpg.czmd5.Util.LogUtil;
import tmbpg.czmd5.Util.Pair;
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
    // 检查这回合是否能进行
    if (!this.canTick()) {
      LogUtil.formatLog("第%d回合，无事发生", TextColor.YELLOW, tick);
      return true;
    }
    List<People> dead = new ArrayList<>();
    // 执行攻击
    Pair<People, People> people = this.chooseTwoPeople();
    int damage = people.getFirst().getDamageAmount(people.getSecond());
    people.getFirst().addScore(damage);
    people.getSecond().damage(damage);
    LogUtil.log(String.format("第%d回合：%s 攻击了 %s ，血量%d->%d", tick, people.getFirst().getName(),
        people.getSecond().getName(), people.getSecond().getHp() + damage, people.getSecond().getHp()),
        TextColor.YELLOW);
    // 执行技能
    for (SkillBase skill : people.getFirst().getSkills())
      if (people.getFirst().nextInt(skill.getTriggerProb()) == 0) {
        if (!skill.shouldExecute(people.getFirst(), people.getSecond(), damage)) {
          LogUtil.log(String.format("%s 额外触发技能 %s ， 但是没效果", people.getFirst().getName(), skill.getName()),
              TextColor.CYAN);
          continue;
        }
        skill.execute(people.getFirst(), people.getSecond());
        LogUtil.log(String.format("%s 额外触发技能 %s ，", people.getFirst().getName(), skill.getName())
            + skill.getMessage(people.getFirst(), people.getSecond()), TextColor.CYAN);
        if (skill.getEffectsForSource(people.getFirst(), people.getSecond()) != SkillBase.EMPTY)
          for (EffectBase effect : skill.getEffectsForSource(people.getFirst(), people.getSecond()))
            people.getFirst().addEffect(effect);
        if (skill.getEffectsForTarget(people.getFirst(), people.getSecond()) != SkillBase.EMPTY)
          for (EffectBase effect : skill.getEffectsForTarget(people.getFirst(), people.getSecond()))
            people.getSecond().addEffect(effect);
      }
    if (people.getSecond().isDead()) {
      LogUtil.log(String.format("%s 被 %s 杀死了", people.getSecond().getName(), people.getFirst().getName()),
          TextColor.RED);
      alivePeoples.remove(people.getSecond());
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

  private boolean canTick() {
    int canExecutePeople = 0;
    for (People people : alivePeoples) {
      if (!people.isHidden())
        canExecutePeople++;
      if (canExecutePeople > 1)
        return true;
    }
    return false;
  }

  private Pair<People, People> chooseTwoPeople() {
    int index1 = random.nextInt(alivePeoples.size());
    while (alivePeoples.get(index1).isHidden())
      index1 = random.nextInt(alivePeoples.size());
    int index2 = random.nextInt(alivePeoples.size());
    while (index1 == index2 || alivePeoples.get(index2).isHidden())
      index2 = random.nextInt(alivePeoples.size());
    return new Pair<>(alivePeoples.get(index1), alivePeoples.get(index2));
  }

  public People getWinner() {
    if (alivePeoples.size() == 1)
      return alivePeoples.get(0);
    throw new RuntimeException("游戏还没有结束，没有胜利者");
  }
}
