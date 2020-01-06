package solid.inject

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

class BasicUsage
{
  @Test
  fun `get an instance of a concrete class`()
  {
    val inject = Injection()
    inject.register(::Concrete)

    val instance = inject.gimme<Concrete>()

    assertThat(instance).isNotNull
  }

  @Test
  fun `every call to gimme can construct a new instance of a concrete class`()
  {
    val inject = Injection()
    inject.register(::Concrete)

    val instance1 = inject.gimme<Concrete>()
    val instance2 = inject.gimme<Concrete>()

    assertThat(instance1).isNotSameAs(instance2)
  }

  @Test
  fun `use a concrete class as an implementation for an abstract type`()
  {
    val inject = Injection()
    inject.bind<Abstract, Concrete>()
    inject.register(::Concrete)

    val instance = inject.gimme<Abstract>()

    assertThat(instance).isInstanceOf(Concrete::class.java)
  }

  @Test
  fun `can create an instance that needs parameters`()
  {
    val inject = Injection()
    inject.bind<Abstract, Concrete>()
    inject.register(::Nested)
    inject.register(::Concrete)

    val instance = inject.gimme<Nested>()

    assertThat(instance).isNotNull
    assertThat(instance.anAbstract).isNotNull
  }

  @Test
  fun `can use the injector inside a provider function`()
  {
    val inject = Injection()
    inject.bind<Abstract, Concrete>()
    inject.register(::Concrete)
    inject.provider { Nested(gimme()) }

    val instance = inject.gimme<Nested>()

    assertThat(instance).isNotNull
    assertThat(instance.anAbstract).isNotNull
  }

  @Test
  fun `generics are not supported`()
  {
    assertThatExceptionOfType(IllegalArgumentException::class.java)
      .isThrownBy { Injection().bind<List<String>, StringList>() }
    assertThatExceptionOfType(IllegalArgumentException::class.java)
      .isThrownBy { Injection().bind<List<String>, MutableList<String>>() }
    assertThatExceptionOfType(IllegalArgumentException::class.java)
      .isThrownBy { Injection().scope<List<String>, String>() }
  }

  @Test
  fun `use extension to instantiate a specified generic type (work-around)`()
  {
    val inject = Injection()

    inject.register(::StringList)
    val instance = inject.gimme<StringList>()

    assertThat(instance).isNotNull
  }

  class Nested(val anAbstract: Abstract)

  class Concrete : Abstract

  interface Abstract

  class StringList : ArrayList<String>()
}