package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.AbstractBot;
import com.runemate.pathfinder.Pathfinder;
import com.runemate.pathfinder.path.MateWebPath;
import com.runemate.ui.DefaultUI;

public class MovementUtils {
    private static Pathfinder pathfinder;

    public static <Bot> void init(Bot bot) {
        pathfinder = Pathfinder.create((AbstractBot) bot);
    }

    public static void walkTo(Coordinate destination) {
        Path path;
        if (pathfinder.getLastPath() != null && ((MateWebPath) pathfinder.getLastPath()).isValid()) {
            path = pathfinder.getLastPath();
        } else {
            path = pathfinder.pathBuilder().preferAccuracy().destination(destination).findPath();
        }

        if (path != null) {
            DefaultUI.setStatus("Walking to " + destination);
            path.step(Path.TraversalOption.USE_DIRECT_INPUT);
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