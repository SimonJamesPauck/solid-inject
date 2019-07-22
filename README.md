# Solid Injector

## Principles & Goals

The design and features of this library should support the following

- Encourage the use of the _dependency injection pattern_ and other SOLID principles.
- Make code simpler to understand and work with.

These relative goals should be assessed against the benchmark of manual dependency management.

The main identified shortcomings of manual dependency management are

- A bulky composition root.
- Boilerplate for decoupling construction of new instances other than at the composition root.
- Instantiation scoping. i.e. Reusing instances across certain parts of a dependency tree.

Additional goals might include the following

- Make it easy to visualize and debug the dependency graph.
