package solid.inject.debug

data class InjectionInfo(
  val type: String,
  val id: String,
  val dependencies: Set<InjectionInfo>)