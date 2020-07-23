package com.lxk.design.pattern.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * male 男的，过滤出来男的
 *
 * @author LiXuekai on 2020/7/23
 */
public class CriteriaMale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<>();
        for (Person person : persons) {
            if (person.getGender().equalsIgnoreCase("MALE")) {
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
