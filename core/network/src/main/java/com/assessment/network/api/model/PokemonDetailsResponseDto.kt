package com.assessment.network.api.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class PokemonDetailsResponseDto(
    @field:Json(name = "abilities")
    val abilities: List<Ability> = listOf(),
    @field:Json(name = "base_experience")
    val baseExperience: Int = 0,
    @field:Json(name = "cries")
    val cries: Cries = Cries(),
    @field:Json(name = "forms")
    val forms: List<Form> = listOf(),
    @field:Json(name = "game_indices")
    val gameIndices: List<GameIndice> = listOf(),
    @field:Json(name = "height")
    val height: Int = 0,
    @field:Json(name = "held_items")
    val heldItems: List<HeldItem> = listOf(),
    @field:Json(name = "id")
    val id: Int = 0,
    @field:Json(name = "is_default")
    val isDefault: Boolean = false,
    @field:Json(name = "location_area_encounters")
    val locationAreaEncounters: String = "",
    @field:Json(name = "moves")
    val moves: List<Move> = listOf(),
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "order")
    val order: Int = 0,
    @field:Json(name = "past_abilities")
    val pastAbilities: List<PastAbility> = listOf(),
    @field:Json(name = "past_stats")
    val pastStats: List<PastStat> = listOf(),
    @field:Json(name = "past_types")
    val pastTypes: List<Any?> = listOf(),
    @field:Json(name = "species")
    val species: Species = Species(),
    @field:Json(name = "sprites")
    val sprites: Sprites = Sprites(),
    @field:Json(name = "stats")
    val stats: List<StatXX> = listOf(),
    @field:Json(name = "types")
    val types: List<Type> = listOf(),
    @field:Json(name = "weight")
    val weight: Int = 0
)

@JsonClass(generateAdapter = true)
data class Ability(
    @field:Json(name = "ability")
    val ability: AbilityX = AbilityX(),
    @field:Json(name = "is_hidden")
    val isHidden: Boolean = false,
    @field:Json(name = "slot")
    val slot: Int = 0
)

@JsonClass(generateAdapter = true)
data class Cries(
    @field:Json(name = "latest")
    val latest: String = "",
    @field:Json(name = "legacy")
    val legacy: String = ""
)

@JsonClass(generateAdapter = true)
data class Form(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class GameIndice(
    @field:Json(name = "game_index")
    val gameIndex: Int = 0,
    @field:Json(name = "version")
    val version: Version = Version()
)

@JsonClass(generateAdapter = true)
data class HeldItem(
    @field:Json(name = "item")
    val item: Item = Item(),
    @field:Json(name = "version_details")
    val versionDetails: List<VersionDetail> = listOf()
)

@JsonClass(generateAdapter = true)
data class Move(
    @field:Json(name = "move")
    val move: MoveX = MoveX(),
    @field:Json(name = "version_group_details")
    val versionGroupDetails: List<VersionGroupDetail> = listOf()
)

@JsonClass(generateAdapter = true)
data class PastAbility(
    @field:Json(name = "abilities")
    val abilities: List<AbilityXX> = listOf(),
    @field:Json(name = "generation")
    val generation: Generation = Generation()
)

@JsonClass(generateAdapter = true)
data class PastStat(
    @field:Json(name = "generation")
    val generation: Generation = Generation(),
    @field:Json(name = "stats")
    val stats: List<StatXX> = listOf()
)

@JsonClass(generateAdapter = true)
data class Species(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Sprites(
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_female")
    val backFemale: String = "",
    @field:Json(name = "back_shiny")
    val backShiny: String = "",
    @field:Json(name = "back_shiny_female")
    val backShinyFemale: String = "",
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_shiny_female")
    val frontShinyFemale: String = "",
    @field:Json(name = "other")
    val other: Other = Other(),
    @field:Json(name = "versions")
    val versions: Versions = Versions()
)

@JsonClass(generateAdapter = true)
data class StatXX(
    @field:Json(name = "base_stat")
    val baseStat: Int = 0,
    @field:Json(name = "effort")
    val effort: Int = 0,
    @field:Json(name = "stat")
    val stat: StatX = StatX()
)

@JsonClass(generateAdapter = true)
data class Type(
    @field:Json(name = "slot")
    val slot: Int = 0,
    @field:Json(name = "type")
    val type: TypeX = TypeX()
)

@JsonClass(generateAdapter = true)
data class AbilityX(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Version(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Item(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class VersionDetail(
    @field:Json(name = "rarity")
    val rarity: Int = 0,
    @field:Json(name = "version")
    val version: Version = Version()
)

@JsonClass(generateAdapter = true)
data class MoveX(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class VersionGroupDetail(
    @field:Json(name = "level_learned_at")
    val levelLearnedAt: Int = 0,
    @field:Json(name = "move_learn_method")
    val moveLearnMethod: MoveLearnMethod = MoveLearnMethod(),
    @field:Json(name = "order")
    val order: Int? = null,
    @field:Json(name = "version_group")
    val versionGroup: VersionGroup = VersionGroup()
)

@JsonClass(generateAdapter = true)
data class MoveLearnMethod(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class VersionGroup(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class AbilityXX(
    @field:Json(name = "ability")
    val ability: Any? = null,
    @field:Json(name = "is_hidden")
    val isHidden: Boolean = false,
    @field:Json(name = "slot")
    val slot: Int = 0
)

@JsonClass(generateAdapter = true)
data class Generation(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class StatX(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Other(
    @field:Json(name = "dream_world")
    val dreamWorld: DreamWorld = DreamWorld(),
    @field:Json(name = "home")
    val home: Home = Home(),
    @field:Json(name = "official-artwork")
    val officialArtwork: OfficialArtwork = OfficialArtwork(),
    @field:Json(name = "showdown")
    val showdown: Showdown = Showdown()
)

@JsonClass(generateAdapter = true)
data class Versions(
    @field:Json(name = "generation-i")
    val generationI: GenerationI = GenerationI(),
    @field:Json(name = "generation-ii")
    val generationIi: GenerationIi = GenerationIi(),
    @field:Json(name = "generation-iii")
    val generationIii: GenerationIii = GenerationIii(),
    @field:Json(name = "generation-iv")
    val generationIv: GenerationIv = GenerationIv(),
    @field:Json(name = "generation-ix")
    val generationIx: GenerationIx = GenerationIx(),
    @field:Json(name = "generation-v")
    val generationV: GenerationV = GenerationV(),
    @field:Json(name = "generation-vi")
    val generationVi: GenerationVi = GenerationVi(),
    @field:Json(name = "generation-vii")
    val generationVii: GenerationVii = GenerationVii(),
    @field:Json(name = "generation-viii")
    val generationViii: GenerationViii = GenerationViii()
)

@JsonClass(generateAdapter = true)
data class DreamWorld(
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: Any? = null
)

@JsonClass(generateAdapter = true)
data class Home(
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class OfficialArtwork(
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = ""
)

@JsonClass(generateAdapter = true)
data class Showdown(
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_female")
    val backFemale: String = "",
    @field:Json(name = "back_shiny")
    val backShiny: String = "",
    @field:Json(name = "back_shiny_female")
    val backShinyFemale: Any? = null,
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class GenerationI(
    @field:Json(name = "red-blue")
    val redBlue: RedBlue = RedBlue(),
    @field:Json(name = "yellow")
    val yellow: Yellow = Yellow()
)

@JsonClass(generateAdapter = true)
data class GenerationIi(
    @field:Json(name = "crystal")
    val crystal: Crystal = Crystal(),
    @field:Json(name = "gold")
    val gold: Gold = Gold(),
    @field:Json(name = "silver")
    val silver: Silver = Silver()
)

@JsonClass(generateAdapter = true)
data class GenerationIii(
    @field:Json(name = "emerald")
    val emerald: Emerald = Emerald(),
    @field:Json(name = "firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen = FireredLeafgreen(),
    @field:Json(name = "ruby-sapphire")
    val rubySapphire: RubySapphire = RubySapphire()
)

@JsonClass(generateAdapter = true)
data class GenerationIv(
    @field:Json(name = "diamond-pearl")
    val diamondPearl: DiamondPearl = DiamondPearl(),
    @field:Json(name = "heartgold-soulsilver")
    val heartgoldSoulsilver: HeartgoldSoulsilver = HeartgoldSoulsilver(),
    @field:Json(name = "platinum")
    val platinum: Platinum = Platinum()
)

@JsonClass(generateAdapter = true)
data class GenerationIx(
    @field:Json(name = "scarlet-violet")
    val scarletViolet: ScarletViolet = ScarletViolet()
)

@JsonClass(generateAdapter = true)
data class GenerationV(
    @field:Json(name = "black-white")
    val blackWhite: BlackWhite = BlackWhite()
)

@JsonClass(generateAdapter = true)
data class GenerationVi(
    @field:Json(name = "omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphire = OmegarubyAlphasapphire(),
    @field:Json(name = "x-y")
    val xY: XY = XY()
)

@JsonClass(generateAdapter = true)
data class GenerationVii(
    @field:Json(name = "icons")
    val icons: Icons = Icons(),
    @field:Json(name = "ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoon = UltraSunUltraMoon()
)

@JsonClass(generateAdapter = true)
data class GenerationViii(
    @field:Json(name = "brilliant-diamond-shining-pearl")
    val brilliantDiamondShiningPearl: BrilliantDiamondShiningPearl = BrilliantDiamondShiningPearl(),
    @field:Json(name = "icons")
    val icons: Icons = Icons()
)

@JsonClass(generateAdapter = true)
data class RedBlue(
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_gray")
    val backGray: String = "",
    @field:Json(name = "back_transparent")
    val backTransparent: String = "",
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_gray")
    val frontGray: String = "",
    @field:Json(name = "front_transparent")
    val frontTransparent: String = ""
)

@JsonClass(generateAdapter = true)
data class Yellow(
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_gray")
    val backGray: String = "",
    @field:Json(name = "back_transparent")
    val backTransparent: String = "",
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_gray")
    val frontGray: String = "",
    @field:Json(name = "front_transparent")
    val frontTransparent: String = ""
)

@JsonClass(generateAdapter = true)
data class Crystal(
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_shiny")
    val backShiny: String = "",
    @field:Json(name = "back_shiny_transparent")
    val backShinyTransparent: String = "",
    @field:Json(name = "back_transparent")
    val backTransparent: String = "",
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_shiny_transparent")
    val frontShinyTransparent: String = "",
    @field:Json(name = "front_transparent")
    val frontTransparent: String = ""
)

@JsonClass(generateAdapter = true)
data class Gold(
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_shiny")
    val backShiny: String = "",
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_transparent")
    val frontTransparent: String = ""
)

@JsonClass(generateAdapter = true)
data class Silver(
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_shiny")
    val backShiny: String = "",
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_transparent")
    val frontTransparent: String = ""
)

@JsonClass(generateAdapter = true)
data class Emerald(
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = ""
)

@JsonClass(generateAdapter = true)
data class FireredLeafgreen(
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_shiny")
    val backShiny: String = "",
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = ""
)

@JsonClass(generateAdapter = true)
data class RubySapphire(
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_shiny")
    val backShiny: String = "",
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = ""
)

@JsonClass(generateAdapter = true)
data class DiamondPearl(
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_female")
    val backFemale: String = "",
    @field:Json(name = "back_shiny")
    val backShiny: String = "",
    @field:Json(name = "back_shiny_female")
    val backShinyFemale: String = "",
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class HeartgoldSoulsilver(
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_female")
    val backFemale: String = "",
    @field:Json(name = "back_shiny")
    val backShiny: String = "",
    @field:Json(name = "back_shiny_female")
    val backShinyFemale: String = "",
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class Platinum(
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_female")
    val backFemale: String = "",
    @field:Json(name = "back_shiny")
    val backShiny: String = "",
    @field:Json(name = "back_shiny_female")
    val backShinyFemale: String = "",
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class ScarletViolet(
    @field:Json(name = "front_default")
    val frontDefault: Any? = null,
    @field:Json(name = "front_female")
    val frontFemale: Any? = null
)

@JsonClass(generateAdapter = true)
data class BlackWhite(
    @field:Json(name = "animated")
    val animated: Animated = Animated(),
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_female")
    val backFemale: String = "",
    @field:Json(name = "back_shiny")
    val backShiny: String = "",
    @field:Json(name = "back_shiny_female")
    val backShinyFemale: String = "",
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class Animated(
    @field:Json(name = "back_default")
    val backDefault: String = "",
    @field:Json(name = "back_female")
    val backFemale: String = "",
    @field:Json(name = "back_shiny")
    val backShiny: String = "",
    @field:Json(name = "back_shiny_female")
    val backShinyFemale: String = "",
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class OmegarubyAlphasapphire(
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class XY(
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class Icons(
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: Any? = null
)

@JsonClass(generateAdapter = true)
data class UltraSunUltraMoon(
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: String = "",
    @field:Json(name = "front_shiny")
    val frontShiny: String = "",
    @field:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class BrilliantDiamondShiningPearl(
    @field:Json(name = "front_default")
    val frontDefault: String = "",
    @field:Json(name = "front_female")
    val frontFemale: Any? = null
)

@JsonClass(generateAdapter = true)
data class TypeX(
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "url")
    val url: String = ""
)


