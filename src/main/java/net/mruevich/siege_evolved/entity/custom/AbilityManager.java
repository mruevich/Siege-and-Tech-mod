package net.mruevich.siege_evolved.entity.custom;

import com.google.common.collect.Sets;

import java.util.Set;

public class AbilityManager {
    private final Set<Ability> abilitySet = Sets.newLinkedHashSet();

    public  AbilityManager(){
        return;
    }

    void UpdateAbilities() {
        for (Ability ability : abilitySet){
            ability.TickAbility();
        }
    }

    void AddAbility(Ability ability) {
        abilitySet.add(ability);
    }

    void RemoveAbility(Ability ability){
        abilitySet.remove(ability);
    }
}
