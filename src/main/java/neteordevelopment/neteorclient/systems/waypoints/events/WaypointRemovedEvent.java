/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.systems.waypoints.events;

import neteordevelopment.neteorclient.systems.waypoints.Waypoint;

public record WaypointRemovedEvent(Waypoint waypoint) {
}
