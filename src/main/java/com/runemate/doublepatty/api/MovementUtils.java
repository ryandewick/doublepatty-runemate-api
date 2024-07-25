package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.web.WebPath;

public class MovementUtils {
    public static void walkTo(Coordinate destination) {
        WebPath path = WebPath.buildTo(destination);
        if (path != null) {
            path.step();
        }
    }

    public static boolean isAtLocation(Coordinate location) {
        Player player = Players.getLocal();
        return player != null && player.getPosition().equals(location);
    }

    public static boolean isAtLocationProximity(Coordinate location, int range) {
        Player player = Players.getLocal();
        return player != null && player.getPosition().distanceTo(location) <= range;
    }
}