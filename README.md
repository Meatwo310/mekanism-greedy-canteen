# Mekanism: Greedy Canteen

Mekanism: Greedy Canteen lets Mekanism's Canteen and Nutritional Injection Unit
keep feeding you until your saturation reaches a configurable target.

![Canteen tooltip](https://github.com/user-attachments/assets/d80f8f29-61f1-4269-b2e8-d10375ce6d33)

## Features

- The Canteen can drink more Nutritional Paste even when the hunger bar is full.
- Sneak while using the Canteen to temporarily use Mekanism's normal behavior.
- The Nutritional Injection Unit can continue feeding you up to the same target.
- Extra injection can pause while your health is full.
- All behavior and the target saturation level are configurable by the server.

### Normal Canteen behavior

![Normal behavior](https://github.com/user-attachments/assets/f921cd8d-fc3b-4f75-8fc8-00491b4ddb6e)

### Greedy Canteen behavior

![Greedy behavior](https://github.com/user-attachments/assets/d2493d6d-7992-4a82-aa5c-89e7d05167a5)

## Supported versions

| Minecraft | Mod loader |
|------------|------------|
| 1.18.2 | Forge |
| 1.19.2 | Forge |
| 1.20.1 | Forge |
| 1.21.1 | NeoForge |

## Installation

1. Install the matching Forge or NeoForge version.
2. Install Mekanism for the same Minecraft version.
3. Place Mekanism: Greedy Canteen in the `mods` folder.

Both the client and server should have the mod installed. Mekanism is required.

## Configuration

The server configuration contains these options:

| Option | Default | Description |
|--------|---------|-------------|
| `targetSaturation` | `18.4` | Saturation level that extra drinking and injection aim for. |
| `canteen.enableExtraDrinking` | `true` | Enables the Canteen's greedy behavior. |
| `injectionUnit.enableExtraInjection` | `true` | Enables extra feeding from the Nutritional Injection Unit. |
| `injectionUnit.pauseWhenFullHealth` | `true` | Pauses extra injection while health is full. |

The configuration file is named `greedycanteen-server.toml` and is stored in the
world's `serverconfig` directory.
