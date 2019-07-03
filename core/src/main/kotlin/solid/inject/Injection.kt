package solid.inject

import solid.inject.core.CoreProviderRegistry
import solid.inject.core.ProviderRegistry

class Injection(
  @PublishedApi
  internal val providerRegistry: ProviderRegistry)
{
  /**
   * Suggested constructor for typical use.
   */
  constructor() : this(CoreProviderRegistry())

  inline fun <reified K, reified Y> bind() where Y : K
  {
    val abstract = K::class.qualifiedName!!
    val implementer = Y::class.qualifiedName!!
    providerRegistry.bind(abstract, implementer)
  }

  inline fun <reified K> provider(
    noinline constr: Injection.() -> K)
  {
    val key = K::class.qualifiedName!!
    providerRegistry.register(key) { constr(this) }
  }

  inline fun <reified K> register(
    noinline constr: () -> K)
  {
    provider { constr() }
  }

  inline fun <reified K, reified A> register(
    noinline constr: (A) -> K)
  {
    provider { constr(gimme()) }
  }

  inline fun <reified K, reified A, reified B> register(
    noinline constr: (A, B) -> K)
  {
    provider { constr(gimme(), gimme()) }
  }

  inline fun <reified K> gimme(): K
  {
    val key = K::class.qualifiedName!!
    // Potential for optional call here
    return providerRegistry.gimme(key)!!.invoke() as K
  }
}
