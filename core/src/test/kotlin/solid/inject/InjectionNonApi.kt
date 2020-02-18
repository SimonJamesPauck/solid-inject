package solid.inject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InjectionNonApi
{
  @Test
  fun `typeKey returns the qualified name for a type`()
  {
    assertThat(typeKey<TestInterface>())
      .isEqualTo("solid.inject.InjectionNonApi\$TestInterface")
  }

  @Test
  fun `typeKey includes generic parameter types`()
  {
    assertThat(typeKey<TestGenerics<TestInterface>>())
      .isEqualTo("solid.inject.InjectionNonApi\$TestGenerics<solid.inject.InjectionNonApi\$TestInterface>")
  }

  private interface TestInterface

  @Suppress("unused")
  private class TestGenerics<T>
}
