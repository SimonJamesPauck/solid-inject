package solid.inject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Scoping
{
  @Test
  fun `scoped instances aren't shared between scopes`()
  {
    val inject = Injection()
    inject.register(Scoping::B)
    inject.register(Scoping::C)
    inject.scope<B, C>()

    val instance1 = inject.gimme<B>()
    val instance2 = inject.gimme<B>()

    assertThat(instance1.c).isNotSameAs(instance2.c)
  }

  @Test
  fun `scoped instance is the same throughout object graph`()
  {
    val inject = Injection()
    inject.register(Scoping::A)
    inject.register(Scoping::B)
    inject.register(Scoping::C)
    inject.scope<A, C>()

    val a = inject.gimme<A>()

    assertThat(a.c).isSameAs(a.b.c)
  }

  class A(val c: C, val b: B)

  class B(val c: C)

  class C
}