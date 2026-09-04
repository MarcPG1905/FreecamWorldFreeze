# Freecam World Freeze

Allows you to freeze or slow down your singleplayer world while in the freecam of almost any mod.

> **This is an extension, it does not provide a Freecam itself!**

By default when the extension is set to freeze, only the world is frozen and the player keeps moving.  
Some mods may have **a `Freeze Player` option to also freeze the player**. This will also be part of this extension in the future.

## Compatible Mods

| Mod                                                                                     | Freezing | Slow-Mo |
| --------------------------------------------------------------------------------------- | -------- | ------- |
| [Freecam](https://modrinth.com/mod/freecam) & derivatives                               | ✅       | 🟨¹     |
| [Freecam by Zergatul](https://www.curseforge.com/minecraft/mc-mods/freecam-by-zergatul) | ✅       | 🟨²     |
| [Freecam by Kapiteon](https://www.curseforge.com/minecraft/mc-mods/freecam)             | ✅       | 🟨¹     |
| [FreeCamMC](https://modrinth.com/mod/freecammc)                                         | ✅       | 🟨¹     |
| [Camera Tweaks](https://modrinth.com/mod/cameratweaks)                                  | ✅       | 🟨²     |
| [Camera Enhancements](https://modrinth.com/mod/camenh)                                  | ✅       | 🟨¹     |
| [(Easy) Freecam](https://modrinth.com/mod/easy-freecam)                                 | 🟨³      | ✅      |
| [WI Freecam (Wurst)](https://www.curseforge.com/minecraft/mc-mods/wi-freecam)           | ✅       | 🟨¹     |

<details>
<summary>Markings (¹²³)</summary>

- ¹ Freecam affected by slow-motion.<br>
- ² Input delay in freecam.<br>
- ³ Stuttering-like movement.<br>
</details>

## Installation

To install, first **download one of these base mods listed above**.

Now, you can **download this mod** and drop it into your `./mods/` folder aswell.

That's it! You can **start Minecraft and enjoy the extension**.

## Features

### Configure Freeze/Slow-Motion

Using the **mod menu** of your platform (on Fabric, you need to install "Mod Menu" for this), you can access the extension's **configuration screen**.  
There, you can move the slider for three different modes:

1. **Left:** Freeze the world
2. **Middle:** Slow down the world (configurable intensity)
3. **Right:** Do nothing (not recommended)

### Toggle the Extension while in Freecam

**The default key to toggle is `F6`.** It can be modified in Minecraft's keybind settings.

> Some freecam mods may use this to activate their freecam.
> You will need to manually remap the keybind in those cases case.

Pressing the set key will **enable or disable the freeze/slow-motion** in your world.

## Planned Features

- **Freeze the player** in freezing mode (if the freecam mod does not have this already).
- **Normal freecam flight speeds** when slowing down the game.

## How this mod works

This mod contains adapaters for the most downloaded freecam mods and automatically detects which of the mods is installed, loading only those adapters.  
Once this is done, it uses the same mechanisms as the `/tick` command does internally while hooking into the installed freecam mod to apply automatically when using the freecam.

There's also a bunch of other black magic going on which is kinda boring. If it still interests you, the whole project is open-source on GitHub for you to check out.

## Contact

You can join my [Discord server](https://discord.gg/HvWhqY3kRG) for support, message me directly on Discord (`@marcpg1905`), or email me at [marcpg@proton.me](mailto:marcpg@proton.me).
