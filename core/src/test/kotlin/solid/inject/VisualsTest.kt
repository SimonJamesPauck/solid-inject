package solid.inject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import solid.inject.core.CoreProviderRegistry
import solid.inject.core.VisualProviderRegistry

class VisualsTest
{
  @Test
  fun `stnff`()
  {
    val inject = Injection(VisualProviderRegistry())
    inject.register(VisualsTest::A)
    inject.register(VisualsTest::B)
    inject.register(VisualsTest::C)
    inject.scope<A, B>()
    inject.scope<A, C>()

    val a = inject.gimme<A>()

    assertThat(a.c).isSameAs(a.b.c)
  }

  class A(val c: C, val b: B)

  class B(val c: C)

  class C
}