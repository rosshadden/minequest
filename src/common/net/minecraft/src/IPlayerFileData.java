package net.minecraft.src;

public interface IPlayerFileData
{
    /**
     * Writes the player data to disk from the specified PlayerEntityMP.
     */
    void writePlayerData(EntityPlayer var1);

    /**
     * Reads the player data from disk into the specified PlayerEntityMP.
     */
    void readPlayerData(EntityPlayer var1);

    /**
     * Gets an array of Usernames there are available player.dat files for.
     */
    String[] getAvailablePlayerDat();
}
