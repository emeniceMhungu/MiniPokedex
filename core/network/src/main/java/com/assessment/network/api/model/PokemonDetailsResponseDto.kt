package com.assessment.network.api.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class PokemonDetailsResponseDto(
    @param:Json(name = "abilities")
    val abilities: List<Ability> = listOf(),
    @param:Json(name = "base_experience")
    val baseExperience: Int = 0,
    @param:Json(name = "cries")
    val cries: Cries = Cries(),
    @param:Json(name = "forms")
    val forms: List<Form> = listOf(),
    @param:Json(name = "game_indices")
    val gameIndices: List<GameIndice> = listOf(),
    @param:Json(name = "height")
    val height: Int = 0,
    @param:Json(name = "held_items")
    val heldItems: List<HeldItem> = listOf(),
    @param:Json(name = "id")
    val id: Int = 0,
    @param:Json(name = "is_default")
    val isDefault: Boolean = false,
    @param:Json(name = "location_area_encounters")
    val locationAreaEncounters: String = "",
    @param:Json(name = "moves")
    val moves: List<Move> = listOf(),
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "order")
    val order: Int = 0,
    @param:Json(name = "past_abilities")
    val pastAbilities: List<PastAbility> = listOf(),
    @param:Json(name = "past_stats")
    val pastStats: List<PastStat> = listOf(),
    @param:Json(name = "past_types")
    val pastTypes: List<Any?> = listOf(),
    @param:Json(name = "species")
    val species: Species = Species(),
    @param:Json(name = "sprites")
    val sprites: Sprites = Sprites(),
    @param:Json(name = "stats")
    val stats: List<StatXX> = listOf(),
    @param:Json(name = "types")
    val types: List<Type> = listOf(),
    @param:Json(name = "weight")
    val weight: Int = 0
)

@JsonClass(generateAdapter = true)
data class Ability(
    @param:Json(name = "ability")
    val ability: AbilityX = AbilityX(),
    @param:Json(name = "is_hidden")
    val isHidden: Boolean = false,
    @param:Json(name = "slot")
    val slot: Int = 0
)

@JsonClass(generateAdapter = true)
data class Cries(
    @param:Json(name = "latest")
    val latest: String = "",
    @param:Json(name = "legacy")
    val legacy: String = ""
)

@JsonClass(generateAdapter = true)
data class Form(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class GameIndice(
    @param:Json(name = "game_index")
    val gameIndex: Int = 0,
    @param:Json(name = "version")
    val version: Version = Version()
)

@JsonClass(generateAdapter = true)
data class HeldItem(
    @param:Json(name = "item")
    val item: Item = Item(),
    @param:Json(name = "version_details")
    val versionDetails: List<VersionDetail> = listOf()
)

@JsonClass(generateAdapter = true)
data class Move(
    @param:Json(name = "move")
    val move: MoveX = MoveX(),
    @param:Json(name = "version_group_details")
    val versionGroupDetails: List<VersionGroupDetail> = listOf()
)

@JsonClass(generateAdapter = true)
data class PastAbility(
    @param:Json(name = "abilities")
    val abilities: List<AbilityXX> = listOf(),
    @param:Json(name = "generation")
    val generation: Generation = Generation()
)

@JsonClass(generateAdapter = true)
data class PastStat(
    @param:Json(name = "generation")
    val generation: Generation = Generation(),
    @param:Json(name = "stats")
    val stats: List<StatXX> = listOf()
)

@JsonClass(generateAdapter = true)
data class Species(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Sprites(
    @param:Json(name = "back_default")
    val backDefault: String = "",
    @param:Json(name = "back_female")
    val backFemale: String = "",
    @param:Json(name = "back_shiny")
    val backShiny: String = "",
    @param:Json(name = "back_shiny_female")
    val backShinyFemale: String = "",
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_female")
    val frontFemale: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = "",
    @param:Json(name = "front_shiny_female")
    val frontShinyFemale: String = "",
    @param:Json(name = "other")
    val other: Other = Other(),
    @param:Json(name = "versions")
    val versions: Versions = Versions()
)

@JsonClass(generateAdapter = true)
data class StatXX(
    @param:Json(name = "base_stat")
    val baseStat: Int = 0,
    @param:Json(name = "effort")
    val effort: Int = 0,
    @param:Json(name = "stat")
    val stat: StatX = StatX()
)

@JsonClass(generateAdapter = true)
data class Type(
    @param:Json(name = "slot")
    val slot: Int = 0,
    @param:Json(name = "type")
    val type: TypeX = TypeX()
)

@JsonClass(generateAdapter = true)
data class AbilityX(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Version(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Item(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class VersionDetail(
    @param:Json(name = "rarity")
    val rarity: Int = 0,
    @param:Json(name = "version")
    val version: Version = Version()
)

@JsonClass(generateAdapter = true)
data class MoveX(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class VersionGroupDetail(
    @param:Json(name = "level_learned_at")
    val levelLearnedAt: Int = 0,
    @param:Json(name = "move_learn_method")
    val moveLearnMethod: MoveLearnMethod = MoveLearnMethod(),
    @param:Json(name = "order")
    val order: Int? = null,
    @param:Json(name = "version_group")
    val versionGroup: VersionGroup = VersionGroup()
)

@JsonClass(generateAdapter = true)
data class MoveLearnMethod(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class VersionGroup(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class AbilityXX(
    @param:Json(name = "ability")
    val ability: Any? = null,
    @param:Json(name = "is_hidden")
    val isHidden: Boolean = false,
    @param:Json(name = "slot")
    val slot: Int = 0
)

@JsonClass(generateAdapter = true)
data class Generation(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class StatX(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Other(
    @param:Json(name = "dream_world")
    val dreamWorld: DreamWorld = DreamWorld(),
    @param:Json(name = "home")
    val home: Home = Home(),
    @param:Json(name = "official-artwork")
    val officialArtwork: OfficialArtwork = OfficialArtwork(),
    @param:Json(name = "showdown")
    val showdown: Showdown = Showdown()
)

@JsonClass(generateAdapter = true)
data class Versions(
    @param:Json(name = "generation-i")
    val generationI: GenerationI = GenerationI(),
    @param:Json(name = "generation-ii")
    val generationIi: GenerationIi = GenerationIi(),
    @param:Json(name = "generation-iii")
    val generationIii: GenerationIii = GenerationIii(),
    @param:Json(name = "generation-iv")
    val generationIv: GenerationIv = GenerationIv(),
    @param:Json(name = "generation-ix")
    val generationIx: GenerationIx = GenerationIx(),
    @param:Json(name = "generation-v")
    val generationV: GenerationV = GenerationV(),
    @param:Json(name = "generation-vi")
    val generationVi: GenerationVi = GenerationVi(),
    @param:Json(name = "generation-vii")
    val generationVii: GenerationVii = GenerationVii(),
    @param:Json(name = "generation-viii")
    val generationViii: GenerationViii = GenerationViii()
)

@JsonClass(generateAdapter = true)
data class DreamWorld(
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_female")
    val frontFemale: Any? = null
)

@JsonClass(generateAdapter = true)
data class Home(
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_female")
    val frontFemale: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = "",
    @param:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class OfficialArtwork(
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = ""
)

@JsonClass(generateAdapter = true)
data class Showdown(
    @param:Json(name = "back_default")
    val backDefault: String = "",
    @param:Json(name = "back_female")
    val backFemale: String = "",
    @param:Json(name = "back_shiny")
    val backShiny: String = "",
    @param:Json(name = "back_shiny_female")
    val backShinyFemale: Any? = null,
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_female")
    val frontFemale: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = "",
    @param:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class GenerationI(
    @param:Json(name = "red-blue")
    val redBlue: RedBlue = RedBlue(),
    @param:Json(name = "yellow")
    val yellow: Yellow = Yellow()
)

@JsonClass(generateAdapter = true)
data class GenerationIi(
    @param:Json(name = "crystal")
    val crystal: Crystal = Crystal(),
    @param:Json(name = "gold")
    val gold: Gold = Gold(),
    @param:Json(name = "silver")
    val silver: Silver = Silver()
)

@JsonClass(generateAdapter = true)
data class GenerationIii(
    @param:Json(name = "emerald")
    val emerald: Emerald = Emerald(),
    @param:Json(name = "firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen = FireredLeafgreen(),
    @param:Json(name = "ruby-sapphire")
    val rubySapphire: RubySapphire = RubySapphire()
)

@JsonClass(generateAdapter = true)
data class GenerationIv(
    @param:Json(name = "diamond-pearl")
    val diamondPearl: DiamondPearl = DiamondPearl(),
    @param:Json(name = "heartgold-soulsilver")
    val heartgoldSoulsilver: HeartgoldSoulsilver = HeartgoldSoulsilver(),
    @param:Json(name = "platinum")
    val platinum: Platinum = Platinum()
)

@JsonClass(generateAdapter = true)
data class GenerationIx(
    @param:Json(name = "scarlet-violet")
    val scarletViolet: ScarletViolet = ScarletViolet()
)

@JsonClass(generateAdapter = true)
data class GenerationV(
    @param:Json(name = "black-white")
    val blackWhite: BlackWhite = BlackWhite()
)

@JsonClass(generateAdapter = true)
data class GenerationVi(
    @param:Json(name = "omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphire = OmegarubyAlphasapphire(),
    @param:Json(name = "x-y")
    val xY: XY = XY()
)

@JsonClass(generateAdapter = true)
data class GenerationVii(
    @param:Json(name = "icons")
    val icons: Icons = Icons(),
    @param:Json(name = "ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoon = UltraSunUltraMoon()
)

@JsonClass(generateAdapter = true)
data class GenerationViii(
    @param:Json(name = "brilliant-diamond-shining-pearl")
    val brilliantDiamondShiningPearl: BrilliantDiamondShiningPearl = BrilliantDiamondShiningPearl(),
    @param:Json(name = "icons")
    val icons: Icons = Icons()
)

@JsonClass(generateAdapter = true)
data class RedBlue(
    @param:Json(name = "back_default")
    val backDefault: String = "",
    @param:Json(name = "back_gray")
    val backGray: String = "",
    @param:Json(name = "back_transparent")
    val backTransparent: String = "",
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_gray")
    val frontGray: String = "",
    @param:Json(name = "front_transparent")
    val frontTransparent: String = ""
)

@JsonClass(generateAdapter = true)
data class Yellow(
    @param:Json(name = "back_default")
    val backDefault: String = "",
    @param:Json(name = "back_gray")
    val backGray: String = "",
    @param:Json(name = "back_transparent")
    val backTransparent: String = "",
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_gray")
    val frontGray: String = "",
    @param:Json(name = "front_transparent")
    val frontTransparent: String = ""
)

@JsonClass(generateAdapter = true)
data class Crystal(
    @param:Json(name = "back_default")
    val backDefault: String = "",
    @param:Json(name = "back_shiny")
    val backShiny: String = "",
    @param:Json(name = "back_shiny_transparent")
    val backShinyTransparent: String = "",
    @param:Json(name = "back_transparent")
    val backTransparent: String = "",
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = "",
    @param:Json(name = "front_shiny_transparent")
    val frontShinyTransparent: String = "",
    @param:Json(name = "front_transparent")
    val frontTransparent: String = ""
)

@JsonClass(generateAdapter = true)
data class Gold(
    @param:Json(name = "back_default")
    val backDefault: String = "",
    @param:Json(name = "back_shiny")
    val backShiny: String = "",
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = "",
    @param:Json(name = "front_transparent")
    val frontTransparent: String = ""
)

@JsonClass(generateAdapter = true)
data class Silver(
    @param:Json(name = "back_default")
    val backDefault: String = "",
    @param:Json(name = "back_shiny")
    val backShiny: String = "",
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = "",
    @param:Json(name = "front_transparent")
    val frontTransparent: String = ""
)

@JsonClass(generateAdapter = true)
data class Emerald(
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = ""
)

@JsonClass(generateAdapter = true)
data class FireredLeafgreen(
    @param:Json(name = "back_default")
    val backDefault: String = "",
    @param:Json(name = "back_shiny")
    val backShiny: String = "",
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = ""
)

@JsonClass(generateAdapter = true)
data class RubySapphire(
    @param:Json(name = "back_default")
    val backDefault: String = "",
    @param:Json(name = "back_shiny")
    val backShiny: String = "",
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = ""
)

@JsonClass(generateAdapter = true)
data class DiamondPearl(
    @param:Json(name = "back_default")
    val backDefault: String = "",
    @param:Json(name = "back_female")
    val backFemale: String = "",
    @param:Json(name = "back_shiny")
    val backShiny: String = "",
    @param:Json(name = "back_shiny_female")
    val backShinyFemale: String = "",
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_female")
    val frontFemale: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = "",
    @param:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class HeartgoldSoulsilver(
    @param:Json(name = "back_default")
    val backDefault: String = "",
    @param:Json(name = "back_female")
    val backFemale: String = "",
    @param:Json(name = "back_shiny")
    val backShiny: String = "",
    @param:Json(name = "back_shiny_female")
    val backShinyFemale: String = "",
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_female")
    val frontFemale: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = "",
    @param:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class Platinum(
    @param:Json(name = "back_default")
    val backDefault: String = "",
    @param:Json(name = "back_female")
    val backFemale: String = "",
    @param:Json(name = "back_shiny")
    val backShiny: String = "",
    @param:Json(name = "back_shiny_female")
    val backShinyFemale: String = "",
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_female")
    val frontFemale: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = "",
    @param:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class ScarletViolet(
    @param:Json(name = "front_default")
    val frontDefault: Any? = null,
    @param:Json(name = "front_female")
    val frontFemale: Any? = null
)

@JsonClass(generateAdapter = true)
data class BlackWhite(
    @param:Json(name = "animated")
    val animated: Animated = Animated(),
    @param:Json(name = "back_default")
    val backDefault: String = "",
    @param:Json(name = "back_female")
    val backFemale: String = "",
    @param:Json(name = "back_shiny")
    val backShiny: String = "",
    @param:Json(name = "back_shiny_female")
    val backShinyFemale: String = "",
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_female")
    val frontFemale: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = "",
    @param:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class Animated(
    @param:Json(name = "back_default")
    val backDefault: String = "",
    @param:Json(name = "back_female")
    val backFemale: String = "",
    @param:Json(name = "back_shiny")
    val backShiny: String = "",
    @param:Json(name = "back_shiny_female")
    val backShinyFemale: String = "",
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_female")
    val frontFemale: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = "",
    @param:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class OmegarubyAlphasapphire(
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_female")
    val frontFemale: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = "",
    @param:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class XY(
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_female")
    val frontFemale: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = "",
    @param:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class Icons(
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_female")
    val frontFemale: Any? = null
)

@JsonClass(generateAdapter = true)
data class UltraSunUltraMoon(
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_female")
    val frontFemale: String = "",
    @param:Json(name = "front_shiny")
    val frontShiny: String = "",
    @param:Json(name = "front_shiny_female")
    val frontShinyFemale: String = ""
)

@JsonClass(generateAdapter = true)
data class BrilliantDiamondShiningPearl(
    @param:Json(name = "front_default")
    val frontDefault: String = "",
    @param:Json(name = "front_female")
    val frontFemale: Any? = null
)

@JsonClass(generateAdapter = true)
data class TypeX(
    @param:Json(name = "name")
    val name: String = "",
    @param:Json(name = "url")
    val url: String = ""
)

