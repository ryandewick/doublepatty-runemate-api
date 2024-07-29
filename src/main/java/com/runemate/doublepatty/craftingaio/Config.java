package com.runemate.doublepatty.craftingaio;

import com.runemate.doublepatty.craftingaio.enums.*;
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
    //For some weird reason this is defaulting to the firstr option in the enum when it shouldn't
    //need to fix this but for now it works
    //TODO: If Green leather is selected, Show only Green leather craftables

    @DependsOn(group = "Crafting", key = "type", value = "CRAFT_HIDES")
    @Setting(key = "craftHides", title = "What would you like to craft", order = 4)
    default CraftHides craftHides() {
        return CraftHides.GREEN_DRAGON_BODY;
    }

}
