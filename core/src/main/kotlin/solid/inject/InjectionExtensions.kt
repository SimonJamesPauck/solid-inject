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

inline fun <reified K> Injection.gimmeNow(noinline constr: () -> K): K = constr()

inline fun <reified K, reified A> Injection.gimmeNow(noinline constr: (A) -> K): K = constr(gimme())

inline fun <reified K, reified A, reified B> Injection.gimmeNow(noinline constr: (A, B) -> K): K =
  constr(gimme(), gimme())

inline fun <reified K, reified A, reified B, reified C> Injection.gimmeNow(
  noinline constr: (A, B, C) -> K): K = constr(gimme(), gimme(), gimme())

inline fun <reified K, reified A, reified B, reified C, reified D> Injection.gimmeNow(
  noinline
  constr: (A, B, C, D) -> K): K = constr(gimme(), gimme(), gimme(), gimme())

inline fun <reified K, reified A, reified B, reified C, reified D, reified E>
  Injection.gimmeNow(
  noinline constr: (A, B, C, D, E) -> K) = constr(gimme(), gimme(), gimme(), gimme(), gimme())

inline fun <reified K, reified A, reified B, reified C, reified D, reified E, reified F>
  Injection.gimmeNow(
  noinline constr: (A, B, C, D, E, F) -> K): K = constr(gimme(), gimme(), gimme(), gimme(), gimme(), gimme())

inline fun <reified K, reified A, reified B, reified C, reified D, reified E, reified F, reified G>
  Injection.gimmeNow(
  noinline constr: (A, B, C, D, E, F, G) -> K): K = constr(gimme(), gimme(), gimme(), gimme(), gimme(), gimme(), gimme())

inline fun <reified K, reified A, reified B, reified C, reified D, reified E, reified F, reified G,
  reified H> Injection.gimmeNow(
  noinline constr: (A, B, C, D, E, F, G, H) -> K): K = constr(gimme(), gimme(), gimme(), gimme(), gimme(), gimme(), gimme(), gimme())
