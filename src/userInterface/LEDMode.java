package userInterface;

enum LEDMode {
    /**
     * Active in case of not charging and not unter low-battery warning.
     */
    OFF,
    /**
     * Active in case of low-battery warning.
     */
    WARNING,
    /**
     * Active in case of charging.
     */
    CHARGING,
    /**
     * Active in case of completed charge.
     */
    FULL_CHARGE,
    /**
     * Active in case of voltage under 2.8.
     */
    UNDERVOLTAGE
}

