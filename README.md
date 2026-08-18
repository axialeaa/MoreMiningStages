![Vanilla and More Mining Stages comparison, block of netherrack being destroyed](https://raw.githubusercontent.com/axialeaa/Axialeaa-BrandingAssets/refs/heads/main/MODS/MoreMiningStages/More%20Mining%20Stages.gif)

<p align=center>
  <!-- FAPI -->
  <a href=https://modrinth.com/mod/fabric-api>
    <img alt="fabric-api" height="64" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/requires/fabric-api_vector.svg">
  </a>

  <!-- GitHub -->
  <a href=https://github.com/axialeaa/MoreMiningStages>
    <img alt="github" height="64" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/github_vector.svg">
  </a>

  <!-- Ko-Fi -->
  <a href=https://ko-fi.com/axialeaa>
      <img alt="kofi-singular" height="64" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy-minimal/donate/kofi-singular_vector.svg">
  </a>

  <!-- Discord -->
  <a href=https://discord.gg/hfVmpeQhe8>
      <img alt="discord-singular" height="64" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy-minimal/social/discord-singular_vector.svg">
  </a>
</p>

<p align=center>
    <b>More Mining Stages</b> allows for full, resource-driven customization of the block breaking animation frames! It also contains a built-in resource pack which doubles the number of frames in the animation in a very vanilla-friendly style.
</p>

<h2><p align=center>📂 Resource Pack Guide</p></h2>

Making a resource pack for **More Mining Stages** is very, very easy.

Under your `assets/` directory, add a new folder called `moreminingstages`. Create a new JSON file inside that folder, and call it `mining_stages`. That file should have a single array object inside named "textures" which contains a list of texture locations.

In the following example, 3 frames have been added to the array. You can add as many frames as you'd like here: they will spread out evenly across the entire mining animation.

```JSON5
{
  "textures": [
    "block/destroy_stage_0", // vanilla reference with implicit namespace
    "minecraft:block/destroy_stage_1", // vanilla reference with explicit namespace
    "example_pack:my_texture" // non-vanilla reference for outside textures/block/
  ]
}
```

For what it's worth, this is the same JSON schema that is used for particle references!