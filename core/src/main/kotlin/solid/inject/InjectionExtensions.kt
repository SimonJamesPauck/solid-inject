package solid.inject

inline fun <reified K> Injection.register(
  noinline constr: () -> K)
{
  provider { constr() }
}

inline fun <reified K, reified A> Injection.register(
  noinline constr: (A) -> K)
{
  provider { constr(gimme()) }
}

inline fun <reified K, reified A, reified B> Injection.register(
  noinline constr: (A, B) -> K)
{
  provider { constr(gimme(), gimme()) }
}
