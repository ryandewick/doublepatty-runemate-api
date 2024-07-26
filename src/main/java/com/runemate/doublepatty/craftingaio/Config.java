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

    @Setting(key = "type", title = "type", order = 1)
    default Types type() {
        return Types.BATTLESTAFFS;
    }

    @DependsOn(group = "Crafting", key = "type", value = "BATTLESTAFFS")
    @Setting(key = "staffs", title = "Battlestaffs", order = 2)
    default Battlestaffs staffs() {
        return Battlestaffs.AIR_BATTLESTAFF;
    }
    @DependsOn(group = "Crafting", key = "type", value = "GEMS")
    @Setting(key = "gems", title = "Gems", order = 3)
    default Gems gems() {
        return Gems.OPAL;
    }
}
