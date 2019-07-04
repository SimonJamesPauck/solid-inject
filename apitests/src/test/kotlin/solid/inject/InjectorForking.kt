package solid.inject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InjectorForking
{
  @Test
  fun `forked injectors give correct instances`()
  {
    val instance1 = C()
    val instance2 = C()
    val facade1 = Injection()
    val facade2 = facade1.fork()
    facade1.register<C> { instance1 }
    facade2.register<C> { instance2 }

    assertThat(facade1.gimme<C>()).isSameAs(instance1)
    assertThat(facade2.gimme<C>()).isSameAs(instance2)
    assertThat(instance1).isNotSameAs(instance2)
  }

  @Test
  fun `child (forked) injector can use parent`()
  {
    val facade1 = Injection()
    val facade2 = facade1.fork()
    facade1.register(InjectorForking::B)
    facade2.register(InjectorForking::C)

    assertThat(facade2.gimme<B>()).isNotNull
  }

  class B(val c: C)

  class C
}