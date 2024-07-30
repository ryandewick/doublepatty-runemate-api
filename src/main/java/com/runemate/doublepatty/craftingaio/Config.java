package com.runemate.doublepatty.craftingaio;

import com.runemate.doublepatty.craftingaio.enums.Battlestaffs;
import com.runemate.doublepatty.craftingaio.enums.Gems;
import com.runemate.doublepatty.craftingaio.enums.Types;
import com.runemate.ui.setting.annotation.open.DependsOn;
import com.runemate.ui.setting.annotation.open.Setting;
import com.runemate.ui.setting.annotation.open.SettingsGroup;
import com.runemate.ui.setting.open.Settings;

@SettingsGroup(group = "Crafting")
public interface Config extends Settings {

    @Setting(key = "type", title = "Type", order = 1)
    default Types getType() {
        return Types.BATTLESTAFFS;
    }

    @DependsOn(group = "Crafting", key = "type", value = "BATTLESTAFFS")
    @Setting(key = "battlestaff", title = "Select Battlestaff", order = 2)
    default Battlestaffs getBattlestaff() {
        return Battlestaffs.AIR_BATTLESTAFF;
    }

    @DependsOn(group = "Crafting", key = "type", value = "GEMS")
    @Setting(key = "gem", title = "Select Gem", order = 3)
    default Gems getGem() {
        return Gems.OPAL;
    }

}
