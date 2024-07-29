package com.runemate.doublepatty.api;

import com.runemate.doublepatty.progressivewoodcutter.Main;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.pathfinder.Pathfinder;
import com.runemate.pathfinder.path.MateWebPath;

public class MovementUtils {
    static BotGUI gui = BotGUI.getInstance();

    private static Pathfinder pathfinder;

    public static void init(Main bot) {
        pathfinder = Pathfinder.create(bot);
    }

    public static void walkTo(Coordinate destination) {
        Path path;
        if (pathfinder.getLastPath() != null && ((MateWebPath) pathfinder.getLastPath()).isValid()) {
            path = pathfinder.getLastPath();
        } else {
            path = pathfinder.pathBuilder().preferAccuracy().destination(destination).findPath();
        }

        if (path != null) {
            gui.setAction("Walking to " + destination);
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