![SOLID Inject](logo.svg)

# A Dependency Injector for SOLID Design

## Principles & Goals

The design and features of this library should support the following

- Encourage the use of the _dependency injection pattern_ and other SOLID principles.
- Make code simpler to understand and work with.
- Make it easy to visualize and debug the dependency graph.

These relative goals should be assessed against the benchmark of manual dependency management.

The main identified shortcomings of manual dependency management are

- A bulky composition root.
- Boilerplate for decoupling construction of new instances other than at the composition root.
- Instantiation scoping. i.e. Reusing instances across certain parts of a dependency tree.

## Getting Started

This library is currently published as a maven artifact through [Jitpack.io](https://jitpack.io/#spauck/solid-inject).
The recommended tag is `master-SNAPSHOT`.
Only the `core` module is required.


## Credits

The following resources were used for the logo.

- [Material Design Colours](https://material.io/resources/color/)
- [Josefin Sans Font](https://fonts.google.com/specimen/Josefin+Sans)
