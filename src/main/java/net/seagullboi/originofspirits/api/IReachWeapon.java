package net.seagullboi.originofspirits.api;

    /*
    @credit Bioplethora - [missing link]
     */

public interface IReachWeapon {

    /**
     * @return Total Reach Distance (Default Distance + Additional Reach Distance)::
     * Default Distance is 5 (supposedly 3, but added 2 more since this reach is somehow kinda wonky)
     */
    double getReachDistance();
}
