package solid.inject

inline fun <reified K> Injection.register(noinline constr: () -> K)
{
  provider { constr() }
}

inline fun <reified K, reified A> Injection.register(noinline constr: (A) -> K)
{
  provider { constr(gimme()) }
}

inline fun <reified K, reified A, reified B> Injection.register(noinline constr: (A, B) -> K)
{
  provider { constr(gimme(), gimme()) }
}

inline fun <reified K, reified A, reified B, reified C> Injection.register(
  noinline constr: (A, B, C) -> K)
{
  provider { constr(gimme(), gimme(), gimme()) }
}

inline fun <reified K, reified A, reified B, reified C, reified D> Injection.register(
  noinline
  constr: (A, B, C, D) -> K)
{
  provider { constr(gimme(), gimme(), gimme(), gimme()) }
}

inline fun <reified K, reified A, reified B, reified C, reified D, reified E>
  Injection.register(
  noinline constr: (A, B, C, D, E) -> K)
{
  provider { constr(gimme(), gimme(), gimme(), gimme(), gimme()) }
}

inline fun <reified K, reified A, reified B, reified C, reified D, reified E, reified F>
  Injection.register(
  noinline constr: (A, B, C, D, E, F) -> K)
{
  provider { constr(gimme(), gimme(), gimme(), gimme(), gimme(), gimme()) }
}

inline fun <reified K, reified A, reified B, reified C, reified D, reified E, reified F, reified G>
  Injection.register(
  noinline constr: (A, B, C, D, E, F, G) -> K)
{
  provider { constr(gimme(), gimme(), gimme(), gimme(), gimme(), gimme(), gimme()) }
}

inline fun <reified K, reified A, reified B, reified C, reified D, reified E, reified F, reified G,
  reified H> Injection.register(
  noinline constr: (A, B, C, D, E, F, G, H) -> K)
{
  provider { constr(gimme(), gimme(), gimme(), gimme(), gimme(), gimme(), gimme(), gimme()) }
}