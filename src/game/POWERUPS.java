package game;

/**
 * This interface defines how power-ups are activated,
 * how the game detects if the power-up is active, and how it determines if the power-up has been triggered.
 * 
 * @author Nishna Makala
 *  @version 21.0.6 LTS (2025-01-21) - OpenJDK Runtime Environment Temurin-21.0.6+7
 */
public interface POWERUPS {
	
    /**
     * Activates the power-up by changing attributes for the given player object.
     * 
     * @param mainCharacter The player object to apply the power-up to.
     */
	void activatePowerUp(Player mainCharacter);

	
	
    /**
     * Checks if the power-up is currently active for the given player.
     * 
     * @param mainCharacter The player object to check the power-up status for.
     * @return   true if the power-up is active, false otherwise.
     */
	boolean isPowerUpActive(Player mainCharacter);
	
	
	
    /**
     * Determines if the power-up has been triggered for the given player.
     * This method activates the power-up block and, 
     * if needed, disables its power-up ability once triggered.
     * 
     * @param mainCharacter The player object to check if the power-up has been activated.
     * @return true if the power-up is activated, false otherwise.
     */
	boolean isActivated(Player mainCharacter);
	
	
	
}
