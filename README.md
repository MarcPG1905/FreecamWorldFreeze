# Freecam World Freeze

Extension to [the Freecam mod](https://modrinth.com/mod/freecam) which freezes or slows down the world when the freecam is activated.

> **Freecam (or a derivative of it) is required for this mod to run!**

By default when the extension is set to freeze, only the world is frozen and the player keeps moving.  
You can **enable `Freeze Player` in Freecam's options to also freeze the player**.

## Installation

To install, first **download one of these base mods**:

- [Freecam](https://modrinth.com/mod/freecam)
- [Fair Freecam](https://modrinth.com/mod/fairfreecam)
- [Freecam (Fair Play)](https://modrinth.com/mod/legacyfreecam) (only for 1.21.X)

Now, you can **download this mod** and drop it into your `./mods/` folder aswell.

That's it! You can **start Minecraft and enjoy the mod/extension**.

## Features

### Configure Freeze/Slow-Motion

Using the **mod menu** of your platform (on Fabric, you need to install "Mod Menu" for this), you can access the extension's **configuration screen**.  
There, you can slide the slider for three different modes:

1. **Left:** Freeze the world
2. **Middle:** Slow down the world (configurable intensity)
3. **Right:** Do nothing (not recommended)

### Toggle the Extension while in Freecam

**The default key to toggle is `F6`.**
> This can be modified in Minecraft's keybind settings.

Pressing the set key will **enable or disable the freeze/slow-motion** in your world.

## Planned Features

- Integration with other popular freecam mods.

## How this mod works

This mod works the same functionality that the `/tick` commands use under the hood, but hooks into the Freecam mod to automatically do this whenever using the freecam.
In addition to this, it will keep track of previous values to not override any manual usage of the `/tick` command, and it handles all possible side cases, configuration, etc. for you.

## Contact

You can join my [Discord server](https://discord.gg/HvWhqY3kRG) for support, message me directly on Discord (`@marcpg1905`), or email me at [marcpg@proton.me](mailto:marcpg@proton.me).
