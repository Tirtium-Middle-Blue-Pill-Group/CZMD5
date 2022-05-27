package tmbpg.czmd5.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tmbpg.czmd5.Data.Skill.AddHp;
import tmbpg.czmd5.Data.Skill.CopperHydrometallurgy;
import tmbpg.czmd5.Data.Skill.HaveDailyTalk;
import tmbpg.czmd5.Data.Skill.OneEyeMethod;
import tmbpg.czmd5.Data.Skill.RelativityTheory;
import tmbpg.czmd5.Data.Skill.RememberTextbook;
import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.SkillBase;

public class DataManager {
  private final List<People> peoples = new ArrayList<>();
  private final List<SkillBase> positiveSkills = new ArrayList<>();

  public DataManager(Random random) {
    peoples.add(new People("顾志刚", random.nextLong(), 500, Subject.Math, 8, 12, new OneEyeMethod()));
    peoples.add(
        new People("王华", random.nextLong(), 500, Subject.English, 8, 12, new HaveDailyTalk(), new RememberTextbook()));
    peoples.add(new People("丁燕华", random.nextLong(), 500, Subject.IT, 8, 12));
    peoples.add(new People("龚翠萍", random.nextLong(), 500, Subject.Chemistry, 8, 12, new OneEyeMethod(),
        new CopperHydrometallurgy()));
    peoples.add(new People("徐军", random.nextLong(), 500, Subject.Physics, 8, 12, new RelativityTheory()));
    this.addPositiveSkill(new AddHp());
  }

  public List<People> getPeople() {
    return peoples;
  }

  public List<SkillBase> getPositiveSkills() {
    return positiveSkills;
  }

  public void addPositiveSkill(SkillBase skill) {
    positiveSkills.add(skill);
  }
}
