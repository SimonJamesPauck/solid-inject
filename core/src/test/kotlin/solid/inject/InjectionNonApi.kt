package solid.inject

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class InjectionNonApi
{
  @Test
  fun `typeKey is not supported for generic types`()
  {
    Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java)
      .isThrownBy { typeKey<List<String>>() }
  }
}
