package solid.inject.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

internal class OnceOffTest
{
  @Test
  fun `invoke() forwards registry to provider`()
  {
    val stubRegistry = StubScopedRegistry()
    val onceOff = OnceOff { registry -> Capture(registry) }

    val instance = onceOff.invoke(stubRegistry) as Capture

    assertThat(instance.arg).isSameAs(stubRegistry)
  }

  @Test
  fun `invoke() called multiple times returns the same instance`()
  {
    val stubRegistry = StubScopedRegistry()
    val onceOff = OnceOff { Any() }

    val first = onceOff.invoke(stubRegistry)
    val second = onceOff.invoke(stubRegistry)

    assertThat(second).isSameAs(first)
  }

  @Test
  fun `invoke() called multiple times only calls provider once`()
  {
    val stubRegistry = StubScopedRegistry()
    val callCount = AtomicInteger(0)
    val onceOff = OnceOff { callCount.incrementAndGet() }

    onceOff.invoke(stubRegistry)
    onceOff.invoke(stubRegistry)

    assertThat(callCount.get()).isEqualTo(1)
  }

  private class Capture(val arg: Any)

  private class StubScopedRegistry : ProviderRegistry
  {
    override fun bind(abstractionId: String, implementerId: String) = throw NotImplementedError()

    override fun register(id: String, provider: Provider) = throw NotImplementedError()

    override fun gimme(id: String): Provider? = throw NotImplementedError()

    override fun scope(toThat: String, scopeThis: String) = throw NotImplementedError()

    override fun fork(): ProviderRegistry = throw NotImplementedError()
  }
}