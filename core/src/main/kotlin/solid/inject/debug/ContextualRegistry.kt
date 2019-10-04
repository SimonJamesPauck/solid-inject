package solid.inject.debug

import solid.inject.core.ProviderRegistry

internal class ContextualRegistry private constructor(
  private val registry: ProviderRegistry,
  private val dependents: MutableMap<String, InjectionInfo>,
  private val dependencies: LinkedHashSet<InjectionInfo> = LinkedHashSet())
  : ProviderRegistry by registry
{
  companion object
  {
    fun from(registry: ProviderRegistry): ContextualRegistry
    {
      return if (registry is ContextualRegistry) registry.newChild()
      else ContextualRegistry(registry, LinkedHashMap())
    }
  }

  override fun fork() = ContextualRegistry(
    registry.fork(),
    dependents,
    dependencies)

  fun newChild() = ContextualRegistry(
    this,
    dependents)

  fun compute(
    typeId: String,
    instanceId: String): InjectionInfo
  {
    val dependant = dependents.computeIfAbsent(instanceId) {
      InjectionInfo(typeId, instanceId, dependencies)
    }

    addToParent(dependant)

    return dependant
  }

  private fun addToParent(dependant: InjectionInfo)
  {
    (registry as ContextualRegistry).dependencies.add(dependant)
  }

  fun output(debug: InjectionDebug)
  {
    debug.onGimme(dependencies.first())
  }
}