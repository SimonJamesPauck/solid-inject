package solid.inject.debug

import solid.inject.core.ProviderRegistry

internal class ContextualRegistry(
  private val registry: ProviderRegistry,
  private val dependents: MutableMap<String, InjectionInfo>,
  internal val dependencies: LinkedHashSet<InjectionInfo> = LinkedHashSet())
  : ProviderRegistry by registry
{
  override fun fork() = ContextualRegistry(
    registry.fork(),
    dependents,
    dependencies)

  fun newChild() = ContextualRegistry(
    this,
    dependents)

  fun compute(
    typeId: String,
    instanceId: String) =
    dependents.computeIfAbsent(instanceId) {
      InjectionInfo(typeId, instanceId, dependencies)
    }
}
