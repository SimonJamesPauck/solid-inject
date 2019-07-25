package solid.inject

import solid.inject.core.CoreProviderRegistry
import solid.inject.core.ProviderRegistry

class Injection(
  @PublishedApi
  internal val registry: ProviderRegistry)
{
  /**
   * Suggested constructor for typical use.
   */
  constructor() : this(CoreProviderRegistry())

  inline fun <reified K, reified Y> bind() where Y : K
  {
    val abstract = K::class.qualifiedName!!
    val implementer = Y::class.qualifiedName!!
    registry.bind(abstract, implementer)
  }

  inline fun <reified K> provider(
    noinline constr: Injection.() -> K)
  {
    val key = K::class.qualifiedName!!
    registry.register(key) { core -> constr(Injection(core)) }
  }

  inline fun <reified K> gimme(): K
  {
    val key = K::class.qualifiedName!!
    // Potential for optional call here
    return registry.gimme(key)!!.invoke(registry) as K
  }

  inline fun <reified T, reified U> scope()
  {
    val scopeThis = U::class.qualifiedName!!
    val toThat = T::class.qualifiedName!!
    registry.scope(toThat, scopeThis)
  }

  fun fork(): Injection
  {
    return Injection(registry.fork())
  }
}
