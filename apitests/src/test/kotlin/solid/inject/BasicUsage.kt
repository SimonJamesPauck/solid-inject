package solid.inject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BasicUsage
{
  @Test
  fun gimmeConcrete()
  {
    val inject = Injection()
    inject.register(::Concrete)

    val instance = inject.gimme<Concrete>()

    assertThat(instance).isNotNull
  }

  @Test
  fun gimmeConcreteForAbstract()
  {
    val inject = Injection()
    inject.bind<Abstract, Concrete>()
    inject.register(::Concrete)

    val instance = inject.gimme<Abstract>()

    assertThat(instance).isNotNull
  }

  @Test
  fun gimmeNested()
  {
    val inject = Injection()
    inject.bind<Abstract, Concrete>()
    inject.register(::Nested)
    inject.register(::Concrete)

    val instance = inject.gimme<Nested>()

    assertThat(instance).isNotNull
  }

  class Nested(val anAbstract: Abstract)

  class Concrete : Abstract

  interface Abstract
}